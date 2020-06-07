package com.example.exparcialg2.Repository;

import com.example.exparcialg2.Entity.Pedido;
import com.example.exparcialg2.Entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Integer> {
}
