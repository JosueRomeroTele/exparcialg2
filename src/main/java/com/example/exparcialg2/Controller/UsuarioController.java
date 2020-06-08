package com.example.exparcialg2.Controller;

import com.example.exparcialg2.Entity.Rol;
import com.example.exparcialg2.Entity.Usuario;
import com.example.exparcialg2.Repository.UsuarioRepository;
import com.example.exparcialg2.Service.SendMailService;
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
import java.util.Optional;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    SendMailService sendMailService;

    @GetMapping("/listargestores")
    public String listarUsuarios(Model model) {
        model.addAttribute("listagestores", usuarioRepository.listargestores());
        return "usuario/lista";
    }

    @GetMapping("/nuevousuario")
    public String nuevo(@ModelAttribute("usuario") Usuario usuario) {

        return "usuario/formusuario";
    }

    @PostMapping("/registrarusuario")
    public String guardar(Model model, @ModelAttribute("usuario") @Valid Usuario usuario, BindingResult bindingResult, RedirectAttributes redirectAttributes,
                          @RequestParam("nombre") String nombre, @RequestParam("apellido") String apellido, @RequestParam("dni") int dni, @RequestParam("correo") String correo,
                          @RequestParam("contrasena") String contrasena) {
        if (bindingResult.hasErrors()) {
            return "usuario/formusuario";
        }
        //store procedure
        int rolguardado = 5;
        boolean ena = true;
        String contrahash = encriptar(contrasena);
        /*
        crearempleado(nombre, apellido, dni, correo, contrahash, rolguardado, ena);
        return "listaproductoss/modificar";

         */

        usuarioRepository.crearusuariosp(nombre, apellido, dni, correo, contrahash, rolguardado, ena);

            return "login/loginFormulario1";
    }
/*
    public void crearempleado(String nombre, String apellido, String dni, String correo, String contrasena, int rol, boolean ena) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.update("call crearusuario(?, ?, ?, ?, ?, ?, ?)", nombre, apellido, dni, correo, contrasena, rol, ena);
    }

 */

    @GetMapping("/nuevogestor")
    public String nuevogestor(@ModelAttribute("usuario") Usuario usuario) {
        return "usuario/formgestor";
    }

    @PostMapping("/registrargestor")
    public String guardargestor(@ModelAttribute("usuario") @Valid Usuario usuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "usuario/formgestor";
        }
        if(usuario.getIdusuario()==0) {
            String contraStr = gencontrasena();
            usuario.setContrasena(encriptar(contraStr));
        }else{
            Optional<Usuario> usuario1 = usuarioRepository.findById(usuario.getIdusuario());
            usuario.setContrasena(usuario1.get().getContrasena());

        }
        Rol rol = new Rol();
        rol.setIdrol(6);
        usuario.setIdRol(rol);
        usuario.setEnable(true);
        //falta enviar contraseña al correo
        usuarioRepository.save(usuario);
        return "redirect:/usuario/listargestores";
    }
    @GetMapping("/editargestor")
    public String editargestor(@ModelAttribute("usuario") Usuario usuario, Model model, @RequestParam("id") int id) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isPresent()) {
            usuario = optionalUsuario.get();
            model.addAttribute("usuario", usuario);
            return "usuario/formgestor";
        } else {
            return "redirect:/usuario/listargestores";
        }
    }
    @GetMapping("/borrargestor")
    public String borrargestor(Model model, @RequestParam("id") int id){
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if(optionalUsuario.isPresent()){
            usuarioRepository.deleteById(id);
        }
        return "redirect:/usuario/listargestores";
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
