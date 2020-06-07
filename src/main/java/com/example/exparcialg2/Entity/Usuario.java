package com.example.exparcialg2.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
public class Usuario {
    @Id
    @Column(name = "idUsuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idusuario;
    @NotBlank
    private String nombre;
    private String apellido;
    private String dni;
    private String correo;
    private String contrasena;
    private boolean enable;
    @ManyToOne
    @Column(name = "Rol_idRol")
    private Rol idRol;


}
