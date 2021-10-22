package com.algaworks.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.api.assembler.ClienteAssembler;
import com.algaworks.algalog.api.model.ClienteModel;
import com.algaworks.algalog.api.model.input.ClienteInput;
import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;
import com.algaworks.algalog.domain.service.CatalogoClienteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
@Api(tags = "Clientes")
public class ClienteController {
	
	private ClienteAssembler clienteAssembler;
	
	private ClienteRepository clienteRepository;
	
	private CatalogoClienteService catalogoClienteService;

	@GetMapping
	@ApiOperation("Listar clientes")
	public List<ClienteModel> listar() {
		return clienteAssembler.toCollectionModel(clienteRepository.findAll());
	}
	
	@GetMapping("/{idCliente}")
	@ApiOperation("Buscar um cliente")
	public ResponseEntity<ClienteModel> buscar(@PathVariable Long idCliente) {
		return clienteRepository.findById(idCliente)
				.map(cliente -> ResponseEntity.ok(clienteAssembler.toModel(cliente)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	@ApiOperation("Cadastrar um cliente")
	public ClienteModel adicionar(@RequestBody @Valid ClienteInput clienteInput) {
		Cliente cliente = clienteAssembler.toEntity(clienteInput);
		cliente = catalogoClienteService.salvar(cliente);
		return clienteAssembler.toModel(cliente);
	}
	
	@PutMapping("/{idCliente}")
	@ApiOperation("Editar um cliente")
	public ResponseEntity<ClienteModel> atualizar(@PathVariable Long idCliente, 
			@RequestBody @Valid ClienteInput clienteInput) {
		if (!clienteRepository.existsById(idCliente)) {
			return ResponseEntity.notFound().build();
		}
		Cliente cliente = clienteAssembler.toEntity(clienteInput);
		cliente.setId(idCliente);
		cliente = catalogoClienteService.salvar(cliente);
		ClienteModel clienteModel = clienteAssembler.toModel(cliente);
		
		return ResponseEntity.ok(clienteModel);
	}
	
	@DeleteMapping("/{idCliente}")
	@ApiOperation("Excluir um cliente")
	public ResponseEntity<Void> excluir(@PathVariable Long idCliente) {
		if (!clienteRepository.existsById(idCliente)) {
			return ResponseEntity.notFound().build();
		}
		
		catalogoClienteService.excluir(idCliente);
		
		return ResponseEntity.noContent().build();
	}
	
}
