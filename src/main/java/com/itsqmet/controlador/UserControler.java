package com.itsqmet.controlador;

import com.itsqmet.entity.Libro;
import com.itsqmet.entity.User;
import com.itsqmet.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserControler {

    @Autowired
    private UserService userService;

    @GetMapping
    public String listaUsuarios(Model model){
        List<User> users = userService.mostrarUsuarios();
        model.addAttribute("users", users);
        return "pages/listaUsuarios";
    }
    //Guardar
    //1. llamar al formulario
    @GetMapping("/formUsuario")
    public String formularioLibro(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "pages/userForm";
    }
    //2. enviar los datos
    @PostMapping("/registrarUsuario")
    public String guardarUsuario(User user){
        if (user.getId() != null){
            userService.actualizarUsuario(user.getId(), user);
        }else{
            userService.guardarUsuario(user);
        }
        //os devuelve a la lista libros
        return "redirect:/users";
    }


    //actualizar
    @GetMapping("/editarUsuario/{id}")
    public String actualizarUsuario(@PathVariable Long id, Model model){
        Optional<User> user = userService.buscarUserById(id);
        model.addAttribute("user", user);
        return "pages/userForm";
    }

    //eliminar
    @DeleteMapping("/eliminarUsuario/{id}")
    public String eliminarUsuario(@PathVariable Long id){
        userService.eliminarUser(id);
        return "redirect:/users";
    }
}
