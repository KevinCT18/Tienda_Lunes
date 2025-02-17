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
        
        return lista;
    }
}
