package com.example.demorest.daos;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.demorest.entidades.Compra;

public interface CompraDAO extends CrudRepository <Compra, Integer>{
    @Modifying
    @Query("DELETE FROM compras WHERE id_usuario=:idUsuario")
    @Transactional
    public void borrarPorIdUsuario(@Param("idUsuario") Integer idUsuario);

}
