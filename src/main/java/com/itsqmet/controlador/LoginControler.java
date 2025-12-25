package com.itsqmet.controlador;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginControler {

    @GetMapping
    public String login(){
        return "pages/login";
    }
    
    //redirigir por roles
    @GetMapping("/postLogin")
    public String dirigirPorRol(Authentication authentication){
        //obtener el objeto usuario que acaba de iniciar sesion
        User usuario = (User) authentication.getPrincipal();
        //procesa la lista de roles o permisos que tiene el usuario
        String role = usuario.getAuthorities().stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
                .findFirst()
                .orElse("");
        if (role.equals("ROLE_ADMIN")){
            return "redirect:/admin";
        } else if (role.equals("ROLE_BIBLIOTECARIO")){
            return "redirect:/libros";
        }else if (role.equals("ROLE_ESTUDIANTE")){
            return "redirect:/libros";
        }
        //parametro error por default
        return "redirect:/login?error";
    }
}
