package com.example.exparcialg2.Config;

import com.example.exparcialg2.Repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pedido")
public class PedidoController {
    @Autowired
    PedidoRepository pedidoRepository;
    @GetMapping("/listapedidos")
    public String listar(){
        return "pedidos/listar";
    }


}
