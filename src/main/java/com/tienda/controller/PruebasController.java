package com.tienda.controller;

import com.tienda.domain.Categoria;
import com.tienda.service.CategoriaService;
import com.tienda.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/pruebas")
public class PruebasController {

    @Autowired
    private CategoriaService categoriaService;
    
    @Autowired
    private ProductoService productoService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var categorias = categoriaService.getCategorias(false);
        model.addAttribute("categorias", categorias);
        var productos = productoService.getProductos(false);
        model.addAttribute("productos", productos);
        return "/pruebas/listado";
    }
    
    @GetMapping("/listado/{idCategoria}")
    public String listado(Model model, Categoria categoria) {
        var categorias = categoriaService.getCategorias(false);
        model.addAttribute("categorias", categorias);
        
        categoria=categoriaService.getCategoria(categoria);
        
        var productos = categoria.getProductos();
        model.addAttribute("productos", productos);
        return "/pruebas/listado";
    }
    
    @GetMapping("/listado2")
    public String listado2(Model model) {
        var productos = productoService.getProductos(false);
        model.addAttribute("productos", productos);
        return "/pruebas/listado2";
    }
    
    @PostMapping("/query1")
    public String consulta1(
            @RequestParam("precioInf") double precioInf, 
            @RequestParam("precioSup") double precioSup,
            Model model) {
        var productos = productoService.consultaAmpliada(precioInf, precioSup);
        model.addAttribute("productos", productos);
        model.addAttribute("precioInf", precioInf);
        model.addAttribute("precioSup", precioSup);
        return "/pruebas/listado2";
    }
    
    @PostMapping("/query2")
    public String consulta2(
            @RequestParam("precioInf") double precioInf, 
            @RequestParam("precioSup") double precioSup,
            Model model) {
        var productos = productoService.consultaJPQL(precioInf, precioSup);
        model.addAttribute("productos", productos);
        model.addAttribute("precioInf", precioInf);
        model.addAttribute("precioSup", precioSup);
        return "/pruebas/listado2";
    }
    
    @PostMapping("/query3")
    public String consulta3(
            @RequestParam("precioInf") double precioInf, 
            @RequestParam("precioSup") double precioSup,
            Model model) {
        var productos = productoService.consultaSQL(precioInf, precioSup);
        model.addAttribute("productos", productos);
        model.addAttribute("precioInf", precioInf);
        model.addAttribute("precioSup", precioSup);
        return "/pruebas/listado2";
    }
    
    @PostMapping("/query4")
    public String consulta4(
            @RequestParam("descripcion") String descripcion,
            Model model) {
        var productos = productoService.consultaAmpliada(descripcion);
        model.addAttribute("productos", productos);
        model.addAttribute("descripcion", descripcion);
        return "/pruebas/listado2";
    }
}
