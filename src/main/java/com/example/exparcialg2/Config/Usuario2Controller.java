package com.example.exparcialg2.Config;

import com.example.exparcialg2.Entity.Pedido;
import com.example.exparcialg2.Entity.Usuario;
import com.example.exparcialg2.Repository.PedidoRepository;
import com.example.exparcialg2.Repository.ProductoRepository;
import com.example.exparcialg2.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
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

        Optional<Usuario> usuarioConPedidos = usuarioRepository.findById(usuarioLogueado.getIdusuario());


        return "pedidos/lista";
    }


}
