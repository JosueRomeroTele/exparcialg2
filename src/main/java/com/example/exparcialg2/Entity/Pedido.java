package com.example.exparcialg2.Entity;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pedido")
public class Pedido implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idpedido;
    @Column(nullable = false)
    private Date fechacompra;
    @Column(name="codigopedido" ,nullable = false)
    private String codigo;
    private BigDecimal totalpago;
    @ManyToOne
    @JoinColumn(name = "usuario_idusuario")
    private Usuario usuario;

    @ManyToMany
    @JoinTable(
            name = "producto_has_pedido",
            joinColumns = @JoinColumn(name = "idpedido"),
            inverseJoinColumns = @JoinColumn(name = "idproducto"))
    private List<Producto> listaProductos;

    public Date getFechacompra() {
        return fechacompra;
    }

    public void setFechacompra(Date fechacompra) {
        this.fechacompra = fechacompra;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(int idpedido) {
        this.idpedido = idpedido;
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
