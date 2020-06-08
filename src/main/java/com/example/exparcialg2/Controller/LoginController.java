package com.example.exparcialg2.Controller;


import com.example.exparcialg2.Entity.Usuario;
import com.example.exparcialg2.Repository.UsuarioRepository;
import com.example.exparcialg2.Service.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.security.SecureRandom;

@Controller
public class LoginController {

    @GetMapping("/loginForm")
    public String loginForm(){
        return "login/loginFormulario1";
    }


    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private SendMailService sendMailService;


    @GetMapping("/redirectByRol")
    public String redirectByRol(Authentication authentication, HttpSession session){
        String rol = "";
        for(GrantedAuthority role : authentication.getAuthorities()){
            rol = role.getAuthority();
            break;
        }
        String username = authentication.getName();
        Usuario user = usuarioRepository.findByCorreo(username);

        session.setAttribute("usuario",user);

        if(rol.equals("administrador")){
            return "redirect:/usuario/listargestores";
        }else {
            if (rol.equals("registrado")){
                return "redirect:/producto";
            }else{
                return "redirect:/producto";
            }
        }
    }


    @GetMapping("/resetPassword")
    public String paginaResetPas(){
        return "login/loginContrasena";
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


    @PostMapping("/resetContra")
    public String newContra(@RequestParam("correo") String correo, RedirectAttributes attributes){

        Usuario usuarioPass = usuarioRepository.findByCorreo(correo);

        if (usuarioPass.getCorreo().equals(correo)){
            String passNewUser = gencontrasena();
            //ACA SE ENVIA EL CORREO
            String asunto = "CONTRASEÑA NUEVA--BODEGA CHABELITA";
            String mensaje = "contraseñaa nueva: " + passNewUser;

            sendMailService.sendMail("josueromero1901@gmail.com",correo,asunto,mensaje);

            String passEncriptado = encriptar(passNewUser);

            usuarioRepository.actualizarContrasenaUsuario(passEncriptado, usuarioPass.getIdusuario());

            attributes.addFlashAttribute("msg","Se le ha enviado a su correo su nueva contraseña");
        }else{
            attributes.addFlashAttribute("msg", "el correo que registra no es logueado");
        }

        return "redirect:/resetPassword";
    }



}
