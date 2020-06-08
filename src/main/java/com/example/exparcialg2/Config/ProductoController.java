package com.example.exparcialg2.Config;

import com.example.exparcialg2.Class.InvertirArray;
import com.example.exparcialg2.Dtos.StorageService;
import com.example.exparcialg2.Entity.Pedido;
import com.example.exparcialg2.Entity.Producto;
import com.example.exparcialg2.Entity.Usuario;
import com.example.exparcialg2.Repository.PedidoRepository;
import com.example.exparcialg2.Repository.ProductoRepository;
import com.example.exparcialg2.Repository.UsuarioRepository;
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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.*;

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
    @Autowired
    UsuarioRepository usuarioRepository;


    @GetMapping(value = {"", "/"})
    public String listaProductos(Model model, @RequestParam Map<String, Object> params) {

        int currentPage = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;

        Page<Producto> page = productoService.listAll(currentPage);
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();

        if (totalPages > 0) {
            List<Integer> pages = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pages", pages);
        }

        List<Producto> listaProductos = page.getContent();

        model.addAttribute("totalItems", totalItems);
        model.addAttribute("listaProductos", listaProductos);
        model.addAttribute("current", currentPage + 1);
        model.addAttribute("next", currentPage + 2);
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
    public String guardarProducto(@RequestParam("archivo") MultipartFile file, @ModelAttribute("producto") @Valid Producto producto, BindingResult bindingResult,
                                  RedirectAttributes attr, Model model) {

        HashMap<String, String> map = storageService.store(file);
        if (map.get("estado").equals("exito")) {
            producto.setFoto(map.get("fileName"));
            if (bindingResult.hasErrors()) {
                return "producto/form";
            } else {
                if (producto.getIdproducto() == 0) {
                    attr.addFlashAttribute("msg", "Producto creado exitosamente");
                } else {
                    attr.addFlashAttribute("msg", "Producto actualizado exitosamente");
                }
                productoRepository.save(producto);
                return "redirect:/producto";
            }
        } else {
            model.addAttribute("msgg", map.get("msg"));
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
        Boolean borrar = true;
        for (Pedido pedido : pedidoRepository.findAll()) {
            for (Producto producto : pedido.getListaProductos()) {
                if (producto.getIdproducto() == id) {
                    borrar = false;
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
        Page<Producto> pageProducto = productoService.listSearch(busqueda, page);
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
        Optional<Producto> p = productoRepository.findById(id);
        Producto produc = p.get();
        model.addAttribute("produc", produc);
        return "producto/detalle";
    }

    @GetMapping("/datos")
    public String datosProductos(Model model) {
        return "producto/datos";
    }


    @GetMapping("/comprar")
    public String precompra(HttpSession session, Model model) {
        ArrayList<Producto> productoCarrito = (ArrayList<Producto>) session.getAttribute("CarritoDeCompras");
        BigDecimal subtotal = new BigDecimal(0);
        BigDecimal total = new BigDecimal(0);
        for (Producto p : productoCarrito) {

            subtotal = BigDecimal.valueOf(1).multiply(p.getPrecio());
            total = total.add(subtotal);

        }
        model.addAttribute("total", total);
        return "/pedidos/comprar";
    }

    @PostMapping("/comprar")
    public String compra(HttpSession session, @RequestParam("tarjeta") String tarjeta) {
        Pedido pedido = new Pedido();
        ArrayList<Producto> productoCarrito = (ArrayList<Producto>) session.getAttribute("CarritoDeCompras");
        Usuario usuariolog = (Usuario) session.getAttribute("usuario");
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(usuariolog.getIdusuario());
        Usuario usuarioconped = optionalUsuario.get();
        List<Pedido> pedidosusuario = pedidoRepository.listaPedidos(usuarioconped.getIdusuario());
        //guardado de pedido
        //guardar  precio
        BigDecimal subtotal = new BigDecimal(0);
        BigDecimal total = new BigDecimal(0);
        for (Producto p : productoCarrito) {

            subtotal = BigDecimal.valueOf(1).multiply(p.getPrecio());
            total = total.add(subtotal);

        }
        pedido.setTotalpago(total);
        pedido.setListaProductos(productoCarrito);
        Date fecha = new Date();
        pedido.setFechacompra(fecha);
        String codigoped = "PE" + fecha.getDay() + fecha.getMonth() + fecha.getYear() + productoCarrito.size() + 1;
        pedido.setCodigo(codigoped);
        //validacion de tarjeta de credito
        if (verificarTarjeta(tarjeta)) {

            pedidoRepository.save(pedido);
            return "redirect:/pedidos/";

        }
        return "redirect:/pedido/carrito";
    }


    @GetMapping("/carrito")
    public String anadirProductoCarrito(@RequestParam("id") int id, HttpSession session){
        ArrayList<Producto> productoCarrito = (ArrayList<Producto>) session.getAttribute("CarritoDeCompras");
        Optional<Producto> optproducto = productoRepository.findById(id);
        Producto productoaux=optproducto.get();
        productoCarrito.add(productoaux);
        /*int b=0;
        int a=0;
        for(Producto p : productoCarrito){
            if(p.getCantidad()==4){
                b=1;
                break;
            }
            if(productoaux.getNombre().equals(p.getNombre()) && p.getCantidad()<=3){
                a=id;
                p.setCantidad(p.getCantidad()+1);
                break;
            }
        }
        if(a==0 && b==0){
            productoaux.setCantidad(1);
            productoCarrito.add(productoaux);
        }*/
        session.setAttribute("CarritoDeCompras",productoCarrito);
        return "redirect:/producto";
    }
    @GetMapping("/misproductos")
    public String miListaProductos(Model model,HttpSession session){
        ArrayList<Producto> productoCarrito = (ArrayList<Producto>) session.getAttribute("CarritoDeCompras");
        model.addAttribute("milista",productoCarrito);
        return "producto/milista";
    }


    public boolean verificarTarjeta(String tarjeta) {
        InvertirArray invertirArray = new InvertirArray();
        ;
        //pasar tarjeta aqui


        //char[] acaracteres;
        char[] acaracteres = tarjeta.toCharArray();

        String digitoverificador;

        digitoverificador = String.valueOf(acaracteres[15]);

        char[] nuevatarjeta = new char[acaracteres.length - 1];
        System.arraycopy(acaracteres, 0, nuevatarjeta, 0, nuevatarjeta.length);
        //String nuevatarjetastr = nuevatarjeta.toString();

        try {
            invertirArray.invertir(nuevatarjeta);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        //numeros impares
        //convertir char array to int array
        int[] tarjetamulti = new int[nuevatarjeta.length];
        for (int i = 0; i < tarjetamulti.length; i++) {
            tarjetamulti[i] = (int) nuevatarjeta[i] - 48;
        }
        int[] tarjetamulti2 = new int[tarjetamulti.length];
        for (int i = 0; i < tarjetamulti2.length; i++) {
            if ((i + 1) % 2 != 0) {
                tarjetamulti2[i] = (2 * tarjetamulti[i]);
            } else {
                tarjetamulti2[i] = tarjetamulti[i];
            }
        }
        //convertir int array to charr array

//        char[] tarjetamultistr = new char[tarjetamulti2.length];
//        for(int i = 0; i<tarjetamulti2.length; i++){
//
//            tarjetamultistr[i] =  (char) ( tarjetamulti2[i] +48);
//
//        }


        //restar 9 a los mayores a 9 xd
        int[] tarjetaresta = new int[tarjetamulti2.length];
        for (int i = 0; i < tarjetaresta.length; i++) {
            if (tarjetamulti2[i] > 9) {
                tarjetaresta[i] = tarjetamulti2[i] - 9;
            } else {
                tarjetaresta[i] = tarjetamulti2[i];
            }
        }

        //sumar todo
        int total = 0;
        for (int i = 0; i < tarjetaresta.length; i++) {
            total += tarjetaresta[i];
        }

        //operacion final
        int verificacion = 0;
        verificacion = (10 - (total % 10)) % 10;
        return String.valueOf(verificacion).equals(digitoverificador);
    }
    //calcular total facturado de la bodega xd
    public BigDecimal calcularfacturado(){
        BigDecimal totalfac = new BigDecimal(0);
        for (Pedido p: pedidoRepository.listapreciototales()){
            totalfac = totalfac.add(p.getTotalpago());
        }
        return totalfac;
    }
    //calcular gasto maximo
    public BigDecimal gastomax(){
        BigDecimal gastmax = new BigDecimal(0);

        return gastmax;
    }


}
