package com.henrique.clientes.rest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.henrique.clientes.model.entity.Cliente;
import com.henrique.clientes.model.entity.ServicoPrestado;
import com.henrique.clientes.model.repository.ClienteRepository;
import com.henrique.clientes.model.repository.ServicoPrestadoRepository;
import com.henrique.clientes.rest.dto.ServicoPrestadoDTO;
import com.henrique.clientes.util.BigDecimalConverter;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/servicos-prestados")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ServicoPrestadoController {

	private final ClienteRepository clienteRepository;
	private final ServicoPrestadoRepository repository;
	private final BigDecimalConverter bigDecimalConverter;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ServicoPrestado created(@RequestBody ServicoPrestadoDTO dto) {
		LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		Integer idCliente = dto.getIdCliente();

		Cliente cliente = clienteRepository.findById(idCliente)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "cliente nao existe"));

		ServicoPrestado servicoPrestado = new ServicoPrestado();
		servicoPrestado.setDescricao(dto.getDescricao());
		servicoPrestado.setData(data);
		servicoPrestado.setCliente(cliente);
		servicoPrestado.setValor(bigDecimalConverter.converter(dto.getPreco()));

		return repository.save(servicoPrestado);
	}
	
	@GetMapping
	public List<ServicoPrestado> search(@RequestParam(value = "nome",defaultValue = "", required = false) String nome,
			@RequestParam( value = "mes", required = false) Integer mes) {
		
		return repository.findByNomeClienteAndMes("%" + nome + "%",mes);
		
	}

}
