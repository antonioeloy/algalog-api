package com.algaworks.algalog.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algalog.api.exception.NegocioException;
import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CatalogoClienteService {

	private ClienteRepository clienteRepository;
	
	public Cliente buscar (Long idCliente) {
		return clienteRepository.findById(idCliente)
				.orElseThrow(() -> new NegocioException("Cliente não encontrado"));
	}
	
	@Transactional
	public Cliente salvar(Cliente cliente) {
		boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
				.stream()
				.anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
		
		if (emailEmUso) {
			throw new NegocioException("Já existe um cliente cadastrado com este e-mail.");
		}
		
		return clienteRepository.save(cliente);
	}
	
	@Transactional
	public void excluir(Long idCliente) {
		clienteRepository.deleteById(idCliente);
	}
	
}
