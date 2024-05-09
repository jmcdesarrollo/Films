package com.api.Films.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDate;
import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name ="User")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identificador único del usuario

    private String username; // Nombre de usuario

    private String password; // Contraseña del usuario

    private String name; // Nombre del usuario

    private String surname; // Apellido del usuario

    private String email; // Correo electrónico del usuario

    private String image; // URL de la imagen de perfil del usuario

    private Date birthDate; // Fecha de nacimiento del usuario

    @CreationTimestamp
    private LocalDate creationDate; // Fecha de creación del usuario

    private LocalDate lastLogin; // Última fecha de inicio de sesión del usuario

    private boolean active; // Estado de activación del usuario

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_Id"),
            inverseJoinColumns = @JoinColumn(name = "role_Id")
    )
    private Set<Role> roles = new HashSet<>(); // Roles asociados al usuario

    // Constructor que inicializa el nombre de usuario
    public User(String username) {
        this.username = username;
    }

    // Método para obtener los roles del usuario
    public Collection<? extends GrantedAuthority> getRoles() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }
}

/*Este código define la entidad User, que representa a los usuarios en el sistema. Cada usuario tiene una serie de
atributos, como su nombre de usuario, contraseña, nombre completo, correo electrónico, fecha de nacimiento, etc.
También tiene una relación Many-to-Many con la entidad Role, que representa los roles asociados al usuario.
El método getRoles() devuelve una colección de autoridades que representan los roles del usuario, lo que facilita
la integración con Spring Security. */
