package com.example.exparcialg2.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PruebaController {

    @GetMapping(value = {"","/"})
    public String entrarPagina(){
        return "prueba/login";
    }

}
