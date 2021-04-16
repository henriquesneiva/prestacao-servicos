package com.henrique.clientes.rest.exceptions;

public class UsuarioCadastradoException extends RuntimeException{

	
	private static final long serialVersionUID = 1L;

	public UsuarioCadastradoException(String login) {
		super("Usuario ja cadastrado para o login" + login);
	}

}
