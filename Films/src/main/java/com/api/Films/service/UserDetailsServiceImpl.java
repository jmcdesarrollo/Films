package com.api.Films.service;

import com.api.Films.entity.User;
import com.api.Films.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


// Implementación de UserDetailsService para cargar los detalles de un usuario
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository; // Repositorio para acceder a los datos de usuario

    // Constructor que inyecta el repositorio UserRepository
    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Método para cargar los detalles del usuario por nombre de usuario
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            // Busca el usuario en la base de datos por nombre de usuario
            User user = (User) userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

            // Construye un UserDetailsImpl a partir del usuario encontrado y lo devuelve
            return UserDetailsImpl.build(user);
        } catch (DataAccessException ex) {
            // Maneja las excepciones de acceso a datos
            throw new UsernameNotFoundException("Error while fetching user from database", ex);
        }
    }
}

/* Este código define la clase UserDetailsServiceImpl, que implementa la interfaz UserDetailsService de Spring Security
para cargar los detalles de un usuario por nombre de usuario. Utiliza un repositorio UserRepository para acceder a los
datos de usuario. El método loadUserByUsername busca el usuario en la base de datos por nombre de usuario y, si se
encuentra, construye un objeto UserDetailsImpl a partir de él y lo devuelve. Se manejan las excepciones de acceso a
datos y se lanza una UsernameNotFoundException si no se encuentra el usuario*/

