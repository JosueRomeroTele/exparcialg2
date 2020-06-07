package com.example.exparcialg2.Controller;


import com.example.exparcialg2.Entity.Usuario;
import com.example.exparcialg2.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.security.SecureRandom;

@Controller
public class LoginController {

    @GetMapping("/loginForm")
    public String loginForm(){
        return "login/loginFormulario";
    }


    @Autowired
    UsuarioRepository usuarioRepository;

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
            return "redirect:/producto";
        }else {
            if (rol.equals("gestor")){
                return "redirect:/gestor";
            }else{
                return "redirect:/invitado";
            }
        }
    }


    @GetMapping("/resetPassword")
    public String paginaResetPas(){
        return "login/loginContrasena";
    }



    @PostMapping("/resetContra")
    public String newContra(@RequestParam("correo") String correo){

        SecureRandom secureRandom = new SecureRandom();
        String contra = new BigInteger(40, secureRandom).toString(32);

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        contra = bCryptPasswordEncoder.encode(contra);


        return "";
    }


}
