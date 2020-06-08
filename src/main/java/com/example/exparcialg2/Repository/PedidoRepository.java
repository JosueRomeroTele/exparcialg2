package com.example.exparcialg2.Repository;

import com.example.exparcialg2.Entity.Pedido;
import com.example.exparcialg2.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Integer> {
    @Query(value = "select * from pedido where pedido.Usuario_idUsuario = ?1",
            nativeQuery = true)
    List<Pedido> listaPedidos(int id);


    @Query(value = "SELECT * FROM pedido where codigopedido like %?1% ",
            nativeQuery = true)
    List<Pedido> listaPedidosBuscar(String nombre);


    @Query(value ="SELECT totalpago FROM pedido", nativeQuery = true)
    List<Pedido> listapreciototales();
    //para calcular usuario que gasto mas plata
    @Query(value = "select * from usuario u inner join pedido p on p.Usuario_idUsuario= u.idUsuario", nativeQuery = true)
    List<Usuario> listausuarioconpedidos();

}
