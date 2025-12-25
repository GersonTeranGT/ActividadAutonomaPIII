package com.itsqmet.service;

import com.itsqmet.entity.Libro;
import com.itsqmet.entity.User;

import com.itsqmet.repository.UserRepository;
import com.itsqmet.roles.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    //leer
    public List<User> mostrarUsuarios() {
        //findAll: "select*from productos" son metodos de JPA
        return userRepository.findAll();
    }

    //buscar por ID
    //optional: EVITA QUE SE FORME UN BUCLE
    public Optional<User> buscarUserById(Long id) {
        return userRepository.findById(id);
    }


    //guardar usuario
    public User guardarUsuario(User user) {
        //encriptar la contraseña antes de guardar
        String passwordEncriptada = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncriptada);
        user.setRol(Rol.ROLE_ESTUDIANTE);
        return userRepository.save(user);

    }

    //ACTUALIZAR usuario
    public User actualizarUsuario(Long id, User user) {
        User usuarioExistente = buscarUserById(id)
                //manejo de escepciones
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuarioExistente.setNombre(user.getNombre());
        usuarioExistente.setUsername(user.getUsername());

        //actuaizar el pasword solo si el usuariol la cambia
        if (user.getPassword()!= null && !user.getPassword().isBlank()){
            usuarioExistente.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        //metodo del JPA
        return userRepository.save(usuarioExistente);
    }

    //ELIMINAR PRODUCTO
    public void eliminarUser(Long id){
        User user = buscarUserById(id)
                .orElseThrow(()-> new RuntimeException("Usuario no existe"));
        userRepository.delete(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //buscar el usuario quye coincida y si n lo encuentra lanza una excepcion
        User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("Usuario no existe: " + username));
        //usar builder para construir unn objeto al que se conoce como objeto autenicado
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRol().name())
                .build();
    }
}
