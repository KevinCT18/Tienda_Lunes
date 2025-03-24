package com.tienda.service;

import com.tienda.domain.Rol;
import com.tienda.domain.Usuario;
import com.tienda.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
public class UsuarioDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private HttpSession httpSession;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Buscar usuario en la tabla
        Usuario usuario = usuarioRepository.findByUsername(username);

        //Se valida si se encontró un usuario con ese username
        if (usuario == null) {
            //No se encontró 
            throw new UsernameNotFoundException(username);
        }

        //Si se encontró el usuario, guardamos en una sesión
        httpSession.removeAttribute("usuarioImagen");
        httpSession.setAttribute("usuarioImagen", usuario.getRutaImagen());

        //Creamos un arreglo de roles de seguridad
        var roles = new ArrayList<GrantedAuthority>();

        //Recuperamos los roles de usuario
        //Estos Strings se convierten en Roles de Seguridad
        for (Rol rol : usuario.getRoles()) {
            roles.add(new SimpleGrantedAuthority("ROLE_" + rol.getNombre()));
        }
        
        //Se crea el usuario en el ambiente de ejecución
        return new User(usuario.getUsername(), usuario.getPassword(), roles);
    }

}
