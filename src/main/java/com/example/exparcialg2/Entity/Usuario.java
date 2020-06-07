package com.example.exparcialg2.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Usuario {
    @Id
    @Column(name = "idUsuario")
    private int idusuario;
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
