package com.example.exparcialg2.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Rol implements Serializable {
    @Id
    @Column(name = "idRol")
    private int idrol;
    private String nombre;
}
