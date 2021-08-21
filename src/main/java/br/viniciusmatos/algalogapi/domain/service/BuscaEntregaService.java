package br.viniciusmatos.algalogapi.domain.service;

import br.viniciusmatos.algalogapi.domain.exception.EntidadeNaoEncontradaException;
import br.viniciusmatos.algalogapi.domain.exception.NegocioException;
import br.viniciusmatos.algalogapi.domain.model.Entrega;
import br.viniciusmatos.algalogapi.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BuscaEntregaService {

    private EntregaRepository entregaRepository;

    public Entrega buscar(Long entregaId){
        return entregaRepository.findById(entregaId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada"));
    }
}
