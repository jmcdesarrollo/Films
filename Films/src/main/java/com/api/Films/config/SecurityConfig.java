package com.api.Films.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@PropertySource("classpath:application.properties")
public class SecurityConfig {

    // Configuración del codificador de contraseñas
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Configuración del filtro de seguridad para las solicitudes HTTP
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                // Autorización de las solicitudes HTTP
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/index").permitAll(); // Permitir acceso a la ruta "/index" sin autenticación
                    auth.anyRequest().authenticated(); // Requerir autenticación para todas las demás rutas
                })
                // Configuración del formulario de inicio de sesión
                .formLogin(formLogin -> formLogin
                        .successHandler(successHandler()) // Manejador de éxito de autenticación
                        .permitAll() // Permitir acceso al formulario de inicio de sesión sin autenticación
                )
                // Configuración de la gestión de sesiones
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // Política de creación de sesiones
                        .invalidSessionUrl("/login") // Redirigir al usuario si se detecta una sesión inválida
                        .maximumSessions(1) // Establecer el número máximo de sesiones permitidas para un usuario
                        .expiredUrl("/login") // Redirigir al usuario si su sesión ha expirado debido a la inactividad
                        .sessionRegistry(sessionRegistry()) // Registro de sesiones
                )
                .build();
    }

    // Bean para el registro de sesiones
    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    // Manejador de éxito de autenticación
    public AuthenticationSuccessHandler successHandler(){
        return ((request, response, authentication) -> {
            response.sendRedirect("/user/dashboard"); // Redirigir al usuario al dashboard después de iniciar sesión correctamente
        });
    }
}
/*Se configura un codificador de contraseñas BCryptPasswordEncoder.
  Se define un filtro de seguridad para las solicitudes HTTP, especificando la autorización de rutas y la configuración del formulario de inicio de sesión.
  Se establece la política de gestión de sesiones, incluyendo la creación de sesiones según sea necesario, la URL de sesión inválida, el número máximo de sesiones permitidas y la URL de sesión expirada.
  Se registra un bean para el registro de sesiones.
  Se define un manejador de éxito de autenticación para redirigir al usuario al dashboard después de iniciar sesión correctamente. */
