package com.example.exparcialg2.Repository;

import com.example.exparcialg2.Entity.Pedido;
import com.example.exparcialg2.Entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Integer> {

    //@Query(value = "SELECT * FROM cadena where cadena.razon_social  like '%?1%' or  cadena.nombre_comercial like '%?1%';",
      //      nativeQuery = true)
    //List<Producto> buscarProductos(String nombre);

}
