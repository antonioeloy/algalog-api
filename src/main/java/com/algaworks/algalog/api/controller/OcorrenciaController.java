package com.algaworks.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.api.assembler.OcorrenciaAssembler;
import com.algaworks.algalog.api.model.OcorrenciaModel;
import com.algaworks.algalog.api.model.input.OcorrenciaInput;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.model.Ocorrencia;
import com.algaworks.algalog.domain.service.BuscaEntregaService;
import com.algaworks.algalog.domain.service.RegistroOcorrenciaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas/{idEntrega}/ocorrencias")
@Api(tags = "Ocorrências")
public class OcorrenciaController {
	
	private RegistroOcorrenciaService registroOcorrenciaService;
	
	private OcorrenciaAssembler ocorrenciaAssembler;
	
	private BuscaEntregaService buscaEntregaService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation("Registrar uma ocorrência para uma entrega")
	public OcorrenciaModel registrar(@PathVariable Long idEntrega,
			@RequestBody @Valid OcorrenciaInput ocorrenciaInput) {
		Ocorrencia ocorrenciaRegistrada = registroOcorrenciaService
				.registrar(idEntrega, ocorrenciaInput.getDescricao());
		
		return ocorrenciaAssembler.toModel(ocorrenciaRegistrada);	
	}
	
	@GetMapping
	@ApiOperation("Listar ocorrências de uma entrega")
	public List<OcorrenciaModel> listar(@PathVariable Long idEntrega) {
		Entrega entrega = buscaEntregaService.buscar(idEntrega);
		
		return ocorrenciaAssembler.toCollectionModel(entrega.getOcorrencias());
	}

}
