package com.henrique.clientes.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.henrique.clientes.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

}
