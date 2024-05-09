package com.api.Films.service;

import com.api.Films.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;


// Implementación de UserDetails para representar detalles de usuario en Spring Security
public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    @Getter
    private Long id; // Identificador único del usuario

    private String username; // Nombre de usuario

    private String email; // Correo electrónico del usuario

    @JsonIgnore
    private String password; // Contraseña del usuario

    private Collection<? extends GrantedAuthority> authorities; // Colección de autoridades asociadas al usuario

    // Constructor para inicializar los campos de UserDetailsImpl
    public UserDetailsImpl(Long id, String username, String email, String password,
                           Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    // Método estático para construir un UserDetailsImpl a partir de un objeto User
    public static UserDetailsImpl build(User user) {
        return new UserDetailsImpl(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getRoles()
        );
    }

    // Métodos de la interfaz UserDetails para obtener detalles del usuario
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    // Métodos para verificar si la cuenta del usuario está activa y si las credenciales no han expirado
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // Método para verificar si la cuenta del usuario está habilitada
    @Override
    public boolean isEnabled() {
        return true;
    }

    // Métodos equals y hashCode para comparar objetos UserDetailsImpl
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

/*Este código define la clase UserDetailsImpl, que implementa la interfaz UserDetails de Spring Security para
representar los detalles de un usuario en el sistema. Tiene campos para el identificador del usuario, el nombre
de usuario, el correo electrónico, la contraseña y las autoridades asociadas al usuario. También incluye métodos
para construir un UserDetailsImpl a partir de un objeto User, así como métodos para proporcionar detalles de
usuario requeridos por Spring Security. Las implementaciones de los métodos de la interfaz UserDetails indican
que la cuenta del usuario siempre está activa y que las credenciales nunca expiran. */

