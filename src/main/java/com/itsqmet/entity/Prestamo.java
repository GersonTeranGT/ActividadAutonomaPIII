package com.itsqmet.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "prestamos")
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //la propietaria de la relacion tiene el campo foraneo
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private User usuario;   //atribut wue representa al usuario dentro del

    @ManyToOne
    @JoinColumn(name = "id_libro")
    private Libro libro;

    @NotNull
    private LocalDate fechaPrestamo;

    private LocalDate fechaDevolucion;





}
