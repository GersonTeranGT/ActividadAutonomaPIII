package com.itsqmet.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    //un autor varios libros --- son nuevos atributos
    //lista donde se guarden los libros -- carga de datos perezosa: conocer la srelaciones entre las entidades
    @OneToMany(mappedBy = "autor", fetch = FetchType.LAZY)  //mapeado
    private List<Libro> libros = new ArrayList<>();
}
