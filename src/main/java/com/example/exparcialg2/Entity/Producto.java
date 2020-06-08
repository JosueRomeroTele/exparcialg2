package com.example.exparcialg2.Entity;


import javax.persistence.*;
import javax.validation.constraints.Digits;
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
    private String codigoproducto;
    @Size(max = 40)
    private String nombre;
    @Digits(integer = 4, fraction = 2)
    private BigDecimal precio;
    @Positive
    @Digits(integer = 10, fraction = 0)
    private int stock;

    @Column(name = "drescripcion")
    @Size(max=255)
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
