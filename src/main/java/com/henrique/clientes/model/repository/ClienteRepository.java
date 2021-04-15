package com.henrique.clientes.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.henrique.clientes.model.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
