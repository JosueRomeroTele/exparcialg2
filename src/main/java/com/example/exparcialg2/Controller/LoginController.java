package com.example.exparcialg2.Controller;

import com.example.exparcialg2.Entity.Usuario;
import com.example.exparcialg2.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @GetMapping("/loginForm")
    public String loginForm(){
        return "login/login";
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
        Usuario usuario = usuarioRepository.findByCorreo(username);
        session.setAttribute("usuario",usuario);

        if(rol.equals("administrador")){
            return "redirect:/admin/lista";
        }else {
            if (rol.equals("sede")){
                return "redirect:/artesano";
            }else{
                return "redirect:/categoria";
            }
        }
    }




}
