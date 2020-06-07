package com.example.exparcialg2.Controller;

import com.example.exparcialg2.Entity.Usuario;
import com.example.exparcialg2.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.naming.Binding;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.StoredProcedureQuery;
import javax.validation.Valid;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/listargestores")
    public String listarUsuarios(Model model) {
        model.addAttribute("listausuarios", usuarioRepository.findByIdRol(2));
        return "usuario/lista";
    }

    @GetMapping("/nuevousuario")
    public String nuevo(Model model) {
        return "usuario/form";
    }

    @PostMapping("/registrarusuario")
    public String guardar(Model model, @ModelAttribute("usuario") @Valid Usuario usuario, BindingResult bindingResult, RedirectAttributes redirectAttributes,
                          @RequestParam("nombre") String nombre, @RequestParam("apellido") String apellido, @RequestParam("dni") String dni, @RequestParam("correo") String correo,
                          @RequestParam("contraseña")String contrasena, @RequestParam("rol") Integer rol, @RequestParam("ena") boolean ena) {
        if (bindingResult.hasErrors()) {
            return "usuario/form";
        }
        //store procedure
        crearempleado(nombre, apellido, dni, correo, contrasena, rol, ena);
        return "listaproductoss/modificar";
    }

    public void crearempleado(String nombre, String apellido, String dni, String correo, String contrasena, int rol, boolean ena) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.update("call crearusuario(?, ?, ?, ?, ?, ?, ?)", nombre, apellido, dni, correo, contrasena, rol, ena);
    }
    @GetMapping("/nuevogestor")
    public String nuevogestor(){
        return "usuario/form";
    }
    @PostMapping("/guardargestor")
    public String guardargestor(@ModelAttribute("usuario") @Valid Usuario usuario, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "usuario/form";
        }

        usuarioRepository.save(usuario);
        return "usuario/lista";
    }
}
