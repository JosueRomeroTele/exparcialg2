package com.example.exparcialg2.Entity;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "producto")
public class Producto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idproducto;
    @NotBlank(message = "El texto no puede estar vacio")
    @Size(max = 40, message = "el nombre NO puede poseer más de 05 caracteres")
    private String codigoproducto;
    @NotBlank(message = "El texto no puede estar vacio")
    @Size(max = 40, message = "el nombre NO puede poseer más de 05 caracteres")
    @Pattern(regexp = "[a-zA-Z]{1,40}",message = "solo se debe ingresar letras")
    private String nombre;
    @NotBlank(message = "El texto no puede estar vacio")
    @Positive
    private BigDecimal precio;
    @NotBlank(message = "El texto no puede estar vacio")
    @Positive
    private int stock;
    @NotBlank(message = "El texto no puede estar vacio")
    @Size(max = 255, message = "el texto posee una cantidad máxima de 255")
    @Column(name = "drescripcion")
    private String descripcion;
    private String foto;
    @ManyToMany(mappedBy = "listaProductos")
    private List<Pedido> listapedidos;


    public List<Pedido> getListapedidos() {
        return listapedidos;
    }

    public void setListapedidos(List<Pedido> listapedidos) {
        this.listapedidos = listapedidos;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public String getCodigoproducto() {
        return codigoproducto;
    }

    public void setCodigoproducto(String codigoproducto) {
        this.codigoproducto = codigoproducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }


    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

}
