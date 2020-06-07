package com.example.exparcialg2.Entity;

import javax.persistence.*;
import java.io.Serializable;

import javax.validation.constraints.*;


@Entity
public class Usuario implements Serializable {
    @Id
    @Column(name = "idUsuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idusuario;
    @NotBlank(message = "El nombre no puede ser vacio")
    @Size(min=2, max = 40, message = "El nombre debe ser de 2 caracteres como minimo y de 40 como maximo")
    private String nombre;
    @NotBlank(message = "El nombre no puede ser vacio")
    @Size(min=2, max = 40, message = "El nombre debe ser de 2 caracteres como minimo y de 40 como maximo")
    private String apellido;
    @NotBlank(message = "El dni no debe ser vacio")
    @Size(min =8, max =8, message = "Debe ser de 8 digitos")
    //hacer validacion de tipo de dato en el controlador
    private int dni;
    @Email(message = "debe ser un email valido")
    private String correo;
    @NotBlank(message = "La contraseña no debe ser vacio")

    @Pattern(regexp = "[a-zA-Z0-9]{8,10}", message = "La contraseña debe ser de 8 caracteres como minimo y de 10 como maximo y debe de contener al menos un numero")
    private String contrasena;
    private boolean enable;
    @ManyToOne
    @JoinColumn(name = "Rol_idRol")
    private Rol idRol;

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Rol getIdRol() {
        return idRol;
    }

    public void setIdRol(Rol idRol) {
        this.idRol = idRol;
    }
}
