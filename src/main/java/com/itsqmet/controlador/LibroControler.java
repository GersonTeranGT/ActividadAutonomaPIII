package com.itsqmet.controlador;

import com.itsqmet.entity.Autor;
import com.itsqmet.entity.Libro;
import com.itsqmet.service.AutorService;
import com.itsqmet.service.LibroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/libros")
public class LibroControler {

    @Autowired
    private LibroService libroService;

    @Autowired
    private AutorService autorService;

    //controlador de libros METODO LEER
    @GetMapping
    public String listaLibros(@RequestParam(name = "buscarLibro",
            required = false,
            defaultValue = "") String buscarLibro, Model model){
        List<Libro> libros = libroService.buscarLibroPorTitulo(buscarLibro);
        model.addAttribute("buscarLibro", buscarLibro);
        model.addAttribute("libros", libros);
        return "pages/listaLibros";
    }
    //Guardar
    //1. llamar al formulario

    @GetMapping("/formLibro")
   public String formularioLibro(Model model){
        Libro libro = new Libro();
        model.addAttribute("libro", libro);
        //cargar datods del autor en el formulari
        List<Autor> autores = autorService.mostarAutor();
        model.addAttribute("autores", autores);
        return "pages/libroForm";
    }
    //2. enviar los datos
    @PostMapping("/registrarLibro")
    public String guardarLibro(@Valid @ModelAttribute Libro libro){
        libroService.guardarLibro(libro);
        //os devuelve a la lista libros
        return "redirect:/libros";
    }


    //actualizar
    @GetMapping("/editarLibro/{id}")
    public String actualizarLibro(@PathVariable Long id, Model model){
        Optional<Libro> libro = libroService.buscarLibroById(id);
        model.addAttribute("libro", libro);
        //cargar datods del autor en el formulario
        List<Autor> autores = autorService.mostarAutor();
        model.addAttribute("autores", autores);
        return "pages/libroForm";
    }

    //eliminar
    @DeleteMapping("/eliminarLibro/{id}")
    public String eliminarLibro(@PathVariable Long id){
        libroService.eliminarLibro(id);
        return "redirect:/libros";
    }
}
