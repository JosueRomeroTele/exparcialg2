package com.example.exparcialg2.Controller;

import com.example.exparcialg2.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PruebaController {

    @Autowired
    ProductoRepository productoRepository;

    @GetMapping(value = {"","/"})
    public String entrarPagina(Model model){
        return "redirect:/producto";
    }


}
