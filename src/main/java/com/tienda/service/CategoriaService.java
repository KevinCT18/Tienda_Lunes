package com.tienda.service;

import com.tienda.domain.Categoria;
import java.util.List;
import com.tienda.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
    
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    @Transactional(readOnly=true)
    public List<Categoria> getCategorias(boolean activos){
        var lista = categoriaRepository.findAll();
        if (activos) {
            //Si solo quiere activos
            lista.removeIf(e -> !e.isActivo());
        }
        return lista;
    }
    
    @Transactional(readOnly=true)
    public Categoria getCategoria(Categoria categoria){
        return categoriaRepository.findById(categoria.getIdCategoria()).orElse(null);
    }
    
    @Transactional
    public void save(Categoria categoria){
        categoriaRepository.save(categoria);
    }
    
    @Transactional
    public void delete(Categoria categoria){
        categoriaRepository.delete(categoria);
    }
    
    
}
