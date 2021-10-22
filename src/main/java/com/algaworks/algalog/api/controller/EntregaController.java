package com.algaworks.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.api.assembler.EntregaAssembler;
import com.algaworks.algalog.api.model.EntregaModel;
import com.algaworks.algalog.api.model.input.EntregaInput;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.repository.EntregaRepository;
import com.algaworks.algalog.domain.service.FinalizacaoEntregaService;
import com.algaworks.algalog.domain.service.SolicitacaoEntregaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
@Api(tags = "Entregas")
public class EntregaController {
	
	private EntregaRepository entregaRepository;
	
	private FinalizacaoEntregaService finalizacaoEntregaService;

	private SolicitacaoEntregaService solicitacaoEntregaService;
	
	private EntregaAssembler entregaAssembler;
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	@ApiOperation("Solicitar uma entrega")
	public EntregaModel solicitar(@RequestBody @Valid EntregaInput entregaInput) {
		Entrega novaEntrega = entregaAssembler.toEntity(entregaInput);	
		Entrega entregaSolicitada = solicitacaoEntregaService.solicitar(novaEntrega);
		
		return entregaAssembler.toModel(entregaSolicitada);
	}
	
	@PutMapping("/{idEntrega}/finalizacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation("Finalizar uma entrega")
	public void finalizar(@PathVariable Long idEntrega) {
		finalizacaoEntregaService.finalizar(idEntrega);
	}
	
	@GetMapping
	@ApiOperation("Listar entregas")
	public List<EntregaModel> listar() {
		return entregaAssembler.toCollectionModel(entregaRepository.findAll());
	}
	
	@GetMapping("/{idEntrega}")
	@ApiOperation("Buscar uma entrega")
	public ResponseEntity<EntregaModel> buscar(@PathVariable Long idEntrega) {
		return entregaRepository.findById(idEntrega)
				.map(entrega -> ResponseEntity.ok().body(entregaAssembler.toModel(entrega)))
				.orElse(ResponseEntity.notFound().build());
	}
	
}
