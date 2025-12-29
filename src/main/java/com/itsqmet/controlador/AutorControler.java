package com.itsqmet.controlador;

import com.itsqmet.entity.Autor;
import com.itsqmet.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/autores")
public class AutorControler {

    @Autowired
    private AutorService autorService;

    //leer
    @GetMapping
    public String listaAutores(Model model){
        List<Autor> autores = autorService.mostarAutor();
        model.addAttribute("autores", autores);
        return "pages/listaAutores";
    }
    //guardar
    //1. llamar al formulario
    @GetMapping("/formAutor")
    public String guardarAutor(Model model){
        Autor autor = new Autor();
        model.addAttribute("autor", autor);
        return "pages/autorForm";
    }

    //2. enviar los datos
    @PostMapping("/registrarAutor")
    public String guardarAutor(Autor autor){
        autorService.guardarAutor(autor);
        return "redirect:/autores";
    }

    //actualizar
    @GetMapping("/editarAutor/{id}")
    public String atualizarAutor(@PathVariable Long id, Model model){
        Optional<Autor> autor = autorService.buscarAutorById(id);
        model.addAttribute("autor", autor);
        return "pages/autorForm";
    }

    //eliminar
    @DeleteMapping("/eliminarAutor/{id}")
    public String eliminarAutor(@PathVariable Long id){
        autorService.eliminarAutor(id);
        return "redirect:/autores";
    }
}
