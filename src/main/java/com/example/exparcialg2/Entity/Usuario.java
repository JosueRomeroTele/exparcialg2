package com.example.exparcialg2.Entity;

import javax.persistence.*;
import javax.validation.constraints.*;


@Entity
public class Usuario {
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


}
