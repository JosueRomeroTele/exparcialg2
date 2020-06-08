package com.example.exparcialg2.Config;

import com.example.exparcialg2.Dtos.StorageService;
import com.example.exparcialg2.Entity.Pedido;
import com.example.exparcialg2.Entity.Producto;
import com.example.exparcialg2.Repository.PedidoRepository;
import com.example.exparcialg2.Repository.ProductoRepository;
import com.example.exparcialg2.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/producto")
public class ProductoController {
    @Autowired
    ProductoRepository productoRepository;
    @Autowired
    ProductoService productoService;

    @Autowired
    private StorageService storageService;

    @Autowired
    PedidoRepository pedidoRepository;


    @GetMapping(value = {"", "/"})
    public String listaProductos(Model model,@RequestParam Map<String, Object> params) {

        int currentPage = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;

        Page<Producto> page = productoService.listAll(currentPage);
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();

        if(totalPages>0){
            List<Integer> pages = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pages", pages);
        }

        List<Producto> listaProductos = page.getContent();

        model.addAttribute("totalItems", totalItems);
        model.addAttribute("listaProductos", listaProductos);
        model.addAttribute("current", currentPage +1);
        model.addAttribute("next", currentPage +2);
        model.addAttribute("prev", currentPage);
        model.addAttribute("last", totalPages);
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
                    break;
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
    public String buscar(@RequestParam Map<String, Object> params, Model model) {

        String busqueda = (String) params.get("searchField");

        int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
        Page<Producto> pageProducto = productoService.listSearch(busqueda,page);
        int totalPage = pageProducto.getTotalPages();
        long totalItems = pageProducto.getTotalElements();

        if (totalPage > 0) {
            List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
            model.addAttribute("pages", pages);
        }

        model.addAttribute("totalItems", totalItems);
        model.addAttribute("busqueda", busqueda);
        model.addAttribute("listaProductos", pageProducto.getContent());
        model.addAttribute("current", page + 1);
        model.addAttribute("next", page + 2);
        model.addAttribute("prev", page);
        model.addAttribute("last", totalPage);

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

    public String precompra(HttpSession session){
        return "";
    }

}
