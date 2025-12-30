package com.itsqmet.entity;

import com.itsqmet.roles.Rol;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min = 2, max = 20)
    private String nombre;

    @NotNull
    @Size(min = 5, max = 10)
    @Column(unique = true)
    private String username;

    @NotNull
    private String password;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    @OneToOne
    @JoinColumn(name = "tajeta_suscripcion_id")
    private TarjetaSuscripcion tarjetaSuscripcion;

    @OneToMany(mappedBy = "usuario")
    private List<Prestamo> prestamos;

//    //relvcion varios a varios
//    @ManyToMany
//    @JoinTable(name = "prestamos", joinColumns = @JoinColumn(name = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "id_libro"))
//    private List<Libro> libros;


}
