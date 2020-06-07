package com.example.exparcialg2.Controller;

import com.example.exparcialg2.Entity.Usuario;
import com.example.exparcialg2.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.math.BigInteger;
import java.security.SecureRandom;

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
        return "formgestor";
    }

    @PostMapping("/registrarusuario")
    public String guardar(Model model, @ModelAttribute("usuario") @Valid Usuario usuario, BindingResult bindingResult, RedirectAttributes redirectAttributes,
                          @RequestParam("nombre") String nombre, @RequestParam("apellido") String apellido, @RequestParam("dni") String dni, @RequestParam("correo") String correo,
                          @RequestParam("contraseña") String contrasena, @RequestParam("ena") boolean ena) {
        if (bindingResult.hasErrors()) {
            return "usuario/formusuario";
        }
        //store procedure
        int rolguardado = 2;

        String contrahash = encriptar(contrasena);
        crearempleado(nombre, apellido, dni, correo, contrahash, rolguardado, ena);
        return "listaproductoss/modificar";
    }

    public void crearempleado(String nombre, String apellido, String dni, String correo, String contrasena, int rol, boolean ena) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.update("call crearusuario(?, ?, ?, ?, ?, ?, ?)", nombre, apellido, dni, correo, contrasena, rol, ena);
    }

    @GetMapping("/nuevogestor")
    public String nuevogestor() {
        return "formgestor";
    }

    @PostMapping("/registrargestor")
    public String guardargestor(@ModelAttribute("usuario") @Valid Usuario usuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "usuario/formgestor";
        }
        usuario.setContrasena(gencontrasena());

        //falta enviar contraseña al correo
        usuarioRepository.save(usuario);
        return "usuario/lista";
    }

    public String editargestor(@ModelAttribute("usuario") Usuario usuario, Model model, @RequestParam("id") int id){
        return "usuario/form";
    }

    //funcion random
    public static String gencontrasena() {
        SecureRandom secureRandom = new SecureRandom();
        String contra = new BigInteger(40, secureRandom).toString(32);
        return contra;
    }

    public String encriptar(String pww) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        pww = bCryptPasswordEncoder.encode(pww);
        return pww;
    }
}
