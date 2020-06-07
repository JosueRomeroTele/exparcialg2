package com.example.exparcialg2.Entity;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idpedido;
    @Column(nullable = false)
    private String fechacompra;
    @Column(nullable = false)
    private String codigo;
    private BigDecimal totalpago;
    @ManyToMany
    @JoinTable(
            name = "productoHasPedido",
            joinColumns = @JoinColumn(name = "idpedido"),
            inverseJoinColumns = @JoinColumn(name = "idproducto"))
    private List<Producto> listaProductos;


    public int getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(int idpedido) {
        this.idpedido = idpedido;
    }

    public String getFechacompra() {
        return fechacompra;
    }

    public void setFechacompra(String fechacompra) {
        this.fechacompra = fechacompra;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public BigDecimal getTotalpago() {
        return totalpago;
    }

    public void setTotalpago(BigDecimal totalpago) {
        this.totalpago = totalpago;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }
}
