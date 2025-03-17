package com.tienda.repository;

import com.tienda.domain.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductoRepository extends JpaRepository<Producto,Long>{
    
    //Se crea una consulta ampliada que filtra los productos entre un rango de 
    //precios y los ordena por precio 
    public List<Producto> findByPrecioBetweenOrderByPrecio(
            double precioInf, double precioSup);
        
    //Se crea una consulta JPQL que filtra los productos entre un rango de 
    //precios y los ordena por precio 
    @Query(value="SELECT a FROM Producto a where a.precio "
            + "+BETWEEN :precioInf and :precioSup "
            + "ORDER BY a.precio")
    public List<Producto> consultaJPQL(
            @Param("precioInf") double precioInf, 
            @Param("precioSup") double precioSup);
    
    //Se crea una consulta SQL que filtra los productos entre un rango de 
    //precios y los ordena por precio 
    @Query(nativeQuery = true, 
            value="SELECT * FROM producto a where a.precio "
            + "+BETWEEN :precioInf and :precioSup "
            + "ORDER BY a.precio")
    public List<Producto> consultaSQL(
            @Param("precioInf") double precioInf, 
            @Param("precioSup") double precioSup);
}
