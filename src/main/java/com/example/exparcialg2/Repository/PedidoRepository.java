package com.example.exparcialg2.Repository;

import com.example.exparcialg2.Entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Integer> {
    @Query(value = "select * from pedido where pedido.Usuario_idUsuario = ?1",
            nativeQuery = true)
    List<Pedido> listaPedidos(int id);
}
