package com.tienda.controller;

import com.tienda.domain.Categoria;
import com.tienda.service.CategoriaService;
import com.tienda.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    
    @GetMapping("/listado/idCategoria")
    public String listado(Model model, Categoria categoria) {
        var categorias = categoriaService.getCategorias(false);
        model.addAttribute("categorias", categorias);
        
        categoria=categoriaService.getCategoria(categoria);
        
        var productos = categoria.getProductos();
        model.addAttribute("productos", productos);
        return "/pruebas/listado";
    }

}
