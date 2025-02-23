package com.tienda.service;

import com.tienda.domain.Producto;
import java.util.List;
import com.tienda.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {
    
    @Autowired
    private ProductoRepository productoRepository;
    
    @Transactional(readOnly=true)
    public List<Producto> getProductos(boolean activos){
        var lista = productoRepository.findAll();
        if (activos) {
            //Si solo quiere activos
            lista.removeIf(e -> !e.isActivo());
        }
        return lista;
    }
    
    @Transactional(readOnly=true)
    public Producto getProducto(Producto producto){
        return productoRepository.findById(producto.getIdProducto()).orElse(null);
    }
    
    @Transactional
    public void save(Producto producto){
        productoRepository.save(producto);
    }
    
    @Transactional
    public void delete(Producto producto){
        productoRepository.delete(producto);
    }
    
    
}
