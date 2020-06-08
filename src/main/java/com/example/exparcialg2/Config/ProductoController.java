package com.example.exparcialg2.Config;

import com.example.exparcialg2.Dtos.StorageService;
import com.example.exparcialg2.Entity.Pedido;
import com.example.exparcialg2.Entity.Producto;
import com.example.exparcialg2.Repository.PedidoRepository;
import com.example.exparcialg2.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/producto")
public class ProductoController {
    @Autowired
    ProductoRepository productoRepository;
    @Autowired
    PedidoRepository pedidoRepository;

    StorageService storageService;

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
    public String guardarProducto(@RequestParam("archivo")MultipartFile file, @ModelAttribute("producto") @Valid Producto producto, BindingResult bindingResult,
                                  RedirectAttributes attr, Model model) {

        HashMap<String,String> map = storageService.store(file);
        if(map.get("estado").equals("exito")){
            producto.setFoto(map.get("fileName"));
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
        else {
            model.addAttribute("msgg",map.get("msg"));
            return "producto/form";
        }


    }

    @GetMapping("/editar")
    public String editarProducto(@ModelAttribute("producto") Producto producto,
                                      Model model, @RequestParam("id") int id) {
        Optional<Producto> optProducto = productoRepository.findById(id);
        if (optProducto.isPresent()) {
            producto = optProducto.get();
            model.addAttribute("producto", producto);
            return "producto/formEditar";
        } else {
            return "redirect:/producto";
        }
    }

    @GetMapping("/borrar")
    public String borrarProducto(Model model,
                                      @RequestParam("id") int id,
                                      RedirectAttributes attr) {
        Boolean borrar =true;
        for(Pedido pedido: pedidoRepository.findAll()){
            for(Producto producto:pedido.getListaProductos()){
                if(producto.getIdproducto()==id){
                    borrar=false;
                }
            }
        }
        Optional<Producto> optProducto = productoRepository.findById(id);
        if (optProducto.isPresent() && borrar) {
            productoRepository.deleteById(id);
            attr.addFlashAttribute("msg", "Producto borrado exitosamente");
        }
        return "redirect:/producto";
    }
    @PostMapping("/buscar")
    public String buscar(@RequestParam("searchField") String searchField,
                                      Model model) {
        List<Producto> lista = productoRepository.findAll();
        List<Producto> lista2= new ArrayList<>();
        for(Producto p : lista){
            if(p.getNombre().contains(searchField) || p.getCodigoproducto().contains(searchField)){
                lista2.add(p);
            }
        }
        model.addAttribute("listaProductos", lista2);
        return "producto/lista";
    }
    @GetMapping("/detalle")
    public String detalleProducto(@RequestParam("id") int id,
                                     Model model) {
        Optional<Producto> p =productoRepository.findById(id);
        Producto produc=p.get();
        model.addAttribute("produc",produc);
        return "producto/detalle";
    }

    @GetMapping("/datos")
    public String datosProductos(Model model){
        return "producto/datos";
    }

}
