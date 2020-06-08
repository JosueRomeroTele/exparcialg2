package com.example.exparcialg2.Repository;

import com.example.exparcialg2.Entity.Pedido;
import com.example.exparcialg2.Entity.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Integer> {

    @Query(value = "SELECT * FROM producto where producto.nombre like %?1% " +
            "or  producto.codigoproducto like %?1%",
           countQuery = "sELECT count(*) FROM producto where producto.nombre like %?1%" +
                   " or producto.codigoproducto like %?1%",nativeQuery = true)
    Page<Producto> buscarProductos(String nombre, Pageable pageable);

}
