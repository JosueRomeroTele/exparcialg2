package com.example.exparcialg2.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/lista")
    public String entropagina(){

        return "prueba/logueado";
    }
    
    @GetMapping("/jaja")
    public String haha(){
    return "";
    }
}
