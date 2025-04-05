package com.tienda.service;

import com.tienda.domain.Item;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    //Session para guardar el ArrayList de Item del carrito de compra
    @Autowired
    private HttpSession session;

    //Este método suma 1 al item que queremos comprar, crea la lista si no exite
    public void save(Item item) {
        @SuppressWarnings("unchecked")
        List<Item> lista = (List) session.getAttribute("listaItems");
        if (lista == null) {
            //Si es la primera vez que queremos un producto, se crea el arreglo
            lista = new ArrayList<>();
        }
        var existe = false;
        for (Item i : lista) {
            if (Objects.equals(i.getIdProducto(), item.getIdProducto())) {
                existe = true;
                if (i.getExistencias() < i.getCantidad()) {
                    i.setCantidad(i.getCantidad() + 1);
                }
                break;
            }
        }
        if (!existe) {//Si no está previamente en la lista
            item.setCantidad(1);
            lista.add(item);
        }
        session.setAttribute("listaItems", lista);
    }

    //Este método suma solo busca en la lista el item con el idProducto buscado
    public Item getItem(Item item) {
        @SuppressWarnings("unchecked")
        List<Item> lista = (List) session.getAttribute("listaItems");
        if (lista == null) {
            //Si la variable de session no existe, tampoco hay items
            return null;
        }
        for (Item i : lista) {
            if (Objects.equals(i.getIdProducto(), item.getIdProducto())) {
                return i;
            }
        }
        //Si recorre toda la lista y no lo encuentra, se retorna null
        return null;
    }

    //Este método retorna la lista de items que están en la session
    public List<Item> getItems() {
        @SuppressWarnings("unchecked")
        List<Item> lista = (List) session.getAttribute("listaItems");
        return lista;
    }

    //Este método retorna el total de venta que está en el carrito
    public double getTotal() {
        @SuppressWarnings("unchecked")
        List<Item> lista = (List) session.getAttribute("listaItems");
        double total = 0;
        if (lista != null) {
            for (Item i : lista) {
                total += i.getCantidad() * i.getPrecio();
            }
        }
        return total;
    }
}
