package com.itsqmet.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "libros")
public class Libro {
    //clave primaria @ID no hace falta el no acepta nulos
    @Id
    //auto incrementable
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Size(min = 5, max = 30)
    @Column(unique = true)
    private String titulo;

    //validacion en el backend
    @NotNull
    @Pattern(regexp = "^(?=(?:[^0-9]*[0-9]){10}(?:(?:[^0-9]*[0-9]){3})?$)[\\d-]+$")
    //reglaas de identidad de bases de dats
    @Column(unique = true, nullable = false)
    private String isbn;

    @Size(min = 10, max = 50)
    private String descripcion;

    @Min(value = 1)
    @Max(value = 400)
    private int stock;

//    @AssertTrue
//    private boolean condiciones;
//
//    @DateTimeFormat(pattern = "yyyy-mm-dd")
//    private Date fechaPublicacion;
    //cstructor con y sin args
    //get y set

}
