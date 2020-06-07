package com.example.exparcialg2.Config;

import com.example.exparcialg2.Entity.Pedido;
import com.example.exparcialg2.Entity.Producto;
import com.example.exparcialg2.Entity.Usuario;
import com.example.exparcialg2.Repository.PedidoRepository;
import com.example.exparcialg2.Repository.ProductoRepository;
import com.example.exparcialg2.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pedidos")
public class Usuario2Controller {
    @Autowired
    ProductoRepository productoRepository;
    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @GetMapping(value = {"","/"})
    public String listaMisPedidos(Model model, HttpSession session){

        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuario");
        List<Pedido> listaPedidos= pedidoRepository.listaPedidos(usuarioLogueado.getIdusuario());

        model.addAttribute("listaPedidos",listaPedidos);

        return "pedidos/lista";
    }
    @PostMapping("/buscar")
    public String listabuscada(){

        return "pedidos/lista";
    }




}
