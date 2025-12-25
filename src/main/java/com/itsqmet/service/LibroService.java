package com.itsqmet.service;


import com.itsqmet.entity.Libro;
import com.itsqmet.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {
    //inyecion de depedencia del repository contructor por campo
    @Autowired
    //instancia del libroRepository
    private LibroRepository libroRepository;

    //leer
//    @PreAuthorize("hasAnyRole('ADMIN','BIBLIOTECARIO','ESTUDIANTE')")
    public List<Libro> mostrarLibros() {
        //findAll: "select*from productos" son metodos de JPA
        return libroRepository.findAll();
    }

//    @PreAuthorize("hasAnyRole('ADMIN','BIBLIOTECARIO','ESTUDIANTE')")
    //buscar por ID
    //optional: EVITA QUE SE FORME UN BUCLE
    public Optional<Libro> buscarLibroById(Long id) {
        return libroRepository.findById(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN','BIBLIOTECARIO', 'ESTUDIANTE')")
    //Buscar por titulo
    public List<Libro> buscarLibroPorTitulo(String buscarLibro){
        if (buscarLibro == null || buscarLibro.isEmpty()){
            return libroRepository.findAll();
        }else{
            return libroRepository.findByTituloContainingIgnoreCase(buscarLibro);
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN','BIBLIOTECARIO')")
    //guardar libro
    public Libro guardarLibro(Libro libro) {
        libroRepository.save(libro);
        return libro;
    }

    //ACTUALIZAR PRODUCTO
    @PreAuthorize("hasAnyRole('ADMIN','BIBLIOTECARIO')")
    public Libro actualizarLibro(Long id, Libro libro) {
        Libro libroExistente = buscarLibroById(id)
                //manejo de escepciones
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
        libroExistente.setTitulo(libro.getTitulo());
        libroExistente.setIsbn(libro.getIsbn());
        libroExistente.setDescripcion(libro.getDescripcion());
        libroExistente.setStock(libro.getStock());
        //metodo del JPA
        return libroRepository.save(libroExistente);
    }

    //ELIMINAR PRODUCTO
    @PreAuthorize("hasRole('ADMIN')")
    public void eliminarLibro(Long id){
        Libro libro = buscarLibroById(id)
                .orElseThrow(()-> new ResponseStatusException(
                        //personalizar el error de no encontrar un producto
                        HttpStatus.NOT_FOUND, "Libro no existe"
                ));
        libroRepository.delete(libro);
    }
}
