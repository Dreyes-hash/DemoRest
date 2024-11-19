package com.example.demorest.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demorest.entidades.Usuario;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

import com.example.demorest.daos.CompraDAO;
import com.example.demorest.daos.UsuarioDAO;
import com.example.demorest.dtos.UsuarioDTO;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



@RestController
public class UsuarioRest {

    @Autowired
    UsuarioDAO UsuarioDAO;
    CompraDAO compraDAO;

    Log log = LogFactory.getLog(UsuarioRest.class);

    @GetMapping("/usuarios/{id}")
    /*public ResponseEntity<Usuario> obtenUsuario (@PathVariable("id") Integer idUsuario){
        
        log.info("Buscando usuario con id:" + idUsuario);
        Optional <Usuario> usuario = UsuarioDAO.findById(idUsuario);

        //return ResponseEntity.ok(usuario);
        return ResponseEntity.of(usuario);
    }*/

    public ResponseEntity<Usuario> obtenUsuario (@PathVariable("id") Integer idUsuario, String cadena, Integer numero){
        
        log.info("Buscando usuario con id:" + idUsuario);
        log.info("Cadena" + cadena + ", numero" + numero);
        //Optional <Usuario> usuario = UsuarioDAO.findById(idUsuario);
        Usuario usuario = UsuarioDAO.findById(idUsuario).get();

        return ResponseEntity.ok(usuario);
        //return ResponseEntity.of(usuario);
    }

    @PostMapping("/usuarios")
    public ResponseEntity<Usuario> crearUsuario (@RequestBody UsuarioDTO usuario){

        Usuario usuarioACrear = new Usuario();
        usuarioACrear.setNombre(usuario.getNombre());
        usuarioACrear.setEmail(usuario.getEmail());
        usuarioACrear.setPassword(usuario.getPassword());
        Usuario usuarioNuevo = UsuarioDAO.save(usuarioACrear);

        log.info("Guardar usuario con la info:" + usuario);
        return ResponseEntity.ok(usuarioNuevo);

    }
    

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> actualizarUsuario (@PathVariable ("id") Integer id, UsuarioDTO infoUsuario){
        Optional <Usuario> usuario = UsuarioDAO.findById(id);
        if(usuario.isPresent()){
            Usuario usuarioAActualizar = usuario.get();
            usuarioAActualizar.setNombre(infoUsuario.getNombre());
            usuarioAActualizar.setEmail(infoUsuario.getEmail());
            usuarioAActualizar.setPassword(infoUsuario.getPassword());
            UsuarioDAO.save(usuarioAActualizar);
            return ResponseEntity.ok(usuarioAActualizar);

        }else{
            return ResponseEntity.of(usuario);
        }
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Map <String,String>> borrarUsuari (@PathVariable ("id") Integer id){
        compraDAO.borrarPorIdUsuario(id);

        UsuarioDAO.deleteById(id);

        Map <String,String> respuesta = Collections.singletonMap("respuesta", "Usuario eliminado");

        return ResponseEntity.ok(respuesta);
    }
}

