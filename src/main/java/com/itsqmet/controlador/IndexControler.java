package com.itsqmet.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexControler {
    //GET: visualizar informacion - read
    //POST: enviar o guardar infromacion - create - insert
    //PUT: modificar o actualizar la infromacion - update
    //DELETE: eliminar informacion - delete

    @GetMapping("/inicio")
    public String home(){
        return "index";
    }

    //fucnion saludo
//    @GetMapping("/saludo")
//    public String saludar(@RequestParam String nombre, Model model){
//        model.addAttribute("nombre", nombre);
//        return "index";
//    }
}
