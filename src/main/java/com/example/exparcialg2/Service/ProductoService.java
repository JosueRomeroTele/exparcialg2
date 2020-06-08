package com.example.exparcialg2.Service;

import com.example.exparcialg2.Entity.Producto;
import com.example.exparcialg2.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    @Autowired
    ProductoRepository productoRepository;

    public Page<Producto> listAll(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber, 7);
        return productoRepository.findAll(pageable);
    }

    public Page<Producto> listSearch(String search, int page){
        Pageable pageRequest = PageRequest.of(page,7);
        return productoRepository.buscarProductos(search,pageRequest);
    }


}
