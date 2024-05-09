package com.api.Films.service;


import com.api.Films.entity.User;
import com.api.Films.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;


    // Método para obtener todos los usurios

    public ArrayList<User> getUser() {
        return (ArrayList<User>) userRepository.findAll();
    }

    // Método para guardar un nuevo user

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Método para buscar un user por su ID

    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.empty();
    }

    // Método para actualizar un film por su ID
    public User updateById(User request, Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        // Verificamos si el user fue encontrado
        if (optionalUser.isPresent()) {
            // Si el user fue encontrado, obtenemos el user y la asignamos a la variable user
            User user = optionalUser.get();
            // Actualizamos los campos de la persona con los valores proporcionados en la solicitud
            user.setName(request.getName()); // : Actualiza el user del objeto user (name)
            user.setPassword(request.getPassword()); // : Actualiza el password del objeto user (password)
            user.setImage(request.getImage()); // : Actualiza la imagen del objeto user (imagen)

            return userRepository.save(user);
        } else {
            // Si el user no fue encontrado, lanzamos una excepción o manejamos un error según sea necesario
            throw new RuntimeException("El user con el Id " + id + " no fue encontrado.");
        }
    }
    /* primero utilizamos findById(id) para buscar un user por su ID
     en el repositorio de user (userRepository). Luego, verificamos si el
      user fue encontrado utilizando isPresent(). Si el user está presente,
       utilizamos get() para obtener el user y la asignamos a la variable
        user. Finalmente, podemos hacer lo que necesitemos con el user
        encontrado. Si el user no está presente, imprimimos un mensaje indicando
        que no se encontró ningún user con el ID proporcionado.*/
}
