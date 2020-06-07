package com.example.exparcialg2.Controller;

import com.example.exparcialg2.Entity.Usuario;
import com.example.exparcialg2.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.naming.Binding;
import javax.validation.Valid;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    UsuarioRepository usuarioRepository;
    @GetMapping("/listar")
    public String listarUsuarios(Model model){
        model.addAttribute("listausuarios", usuarioRepository.findAll());
        return "usuario/lista";
    }
    @GetMapping("/nuevo")
    public String nuevo(Model model){
        return "usuario/form";
    }
    @PostMapping("/guardar")
    public String guardar(Model model , @ModelAttribute("usuario") @Valid Usuario usuario, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            return "usuario/form";
        }
        
        return "listaproductoss/modificar";
    }
}
