package com.itsqmet.service;

import com.itsqmet.entity.Autor;
import com.itsqmet.entity.Libro;
import com.itsqmet.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    //leer
    public List<Autor> mostarAutor(){
        return  autorRepository.findAll();
    }

    //buscar por ID
    //optional: EVITA QUE SE FORME UN BUCLE
    public Optional<Autor> buscarAutorById(Long id) {
        return autorRepository.findById(id);
    }

    //guardar libro
    public Autor guardarAutor(Autor autor) {
        autorRepository.save(autor);
        return autor;
    }

    //ACTUALIZAR autor
    public Autor actualizarAutor(Long id, Autor autor) {
        Autor autorExistente = buscarAutorById(id)
                //manejo de escepciones
                .orElseThrow(() -> new RuntimeException("Autor no encontrado"));
        autorExistente.setNombre(autor.getNombre());
        //metodo del JPA
        return autorRepository.save(autorExistente);
    }

    //ELIMINAR autor
    public void eliminarAutor(Long id){
        Autor autor = buscarAutorById(id)
                .orElseThrow(()-> new RuntimeException("Autor no existe"));
        autorRepository.delete(autor);
    }
}
