package com.example.exparcialg2.Config;

import com.example.exparcialg2.Entity.Producto;
import com.example.exparcialg2.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/producto")
public class ProductoController {
    @Autowired
    ProductoRepository productoRepository;
    @GetMapping(value = {"", "/"})
    public String listaProductos(Model model) {
        model.addAttribute("listaProductos", productoRepository.findAll());
        return "producto/lista";
    }

    @GetMapping("/nuevo")
    public String nuevoProductoForm(@ModelAttribute("producto") Producto producto,
                                   Model model) {
        return "producto/form";
    }
    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute("producto") @Valid Producto producto, BindingResult bindingResult,
                                  RedirectAttributes attr, Model model) {
        if (bindingResult.hasErrors()){
            return "producto/form";
        }
        else{
            if (producto.getIdproducto() == 0) {
                attr.addFlashAttribute("msg", "Producto creado exitosamente");
            } else {
                attr.addFlashAttribute("msg", "Producto actualizado exitosamente");
            }
            productoRepository.save(producto);
            return "redirect:/producto";
        }
    }

    @GetMapping("/editar")
    public String editarProducto(@ModelAttribute("producto") Producto producto,
                                      Model model, @RequestParam("id") int id) {
        Optional<Producto> optProducto = productoRepository.findById(id);
        if (optProducto.isPresent()) {
            producto = optProducto.get();
            model.addAttribute("producto", producto);
            return "producto/form";
        } else {
            return "redirect:/producto";
        }
    }

    @GetMapping("/borrar")
    public String borrarProducto(Model model,
                                      @RequestParam("id") int id,
                                      RedirectAttributes attr) {
        Optional<Producto> optProducto = productoRepository.findById(id);
        if (optProducto.isPresent()) {
            productoRepository.deleteById(id);
            attr.addFlashAttribute("msg", "Producto borrado exitosamente");
        }
        return "redirect:/producto";
    }
}
