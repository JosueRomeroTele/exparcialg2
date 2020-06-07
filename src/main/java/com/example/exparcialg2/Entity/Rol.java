package com.example.exparcialg2.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Rol {
    @Id
    @Column(name = "idRol")
    private int idrol;
    private String nombre;
}
