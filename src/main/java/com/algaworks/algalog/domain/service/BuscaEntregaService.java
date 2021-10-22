package com.algaworks.algalog.domain.service;

import org.springframework.stereotype.Service;

import com.algaworks.algalog.api.exception.EntidadeNaoEncontradaException;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BuscaEntregaService {
	
	private EntregaRepository entregaRepository;
	
	public Entrega buscar(Long idEntrega) {
		return entregaRepository.findById(idEntrega)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada"));
	}

}
