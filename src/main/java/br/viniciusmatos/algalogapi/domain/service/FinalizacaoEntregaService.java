package br.viniciusmatos.algalogapi.domain.service;

import br.viniciusmatos.algalogapi.domain.model.Entrega;
import br.viniciusmatos.algalogapi.domain.model.StatusEntega;
import br.viniciusmatos.algalogapi.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
public class FinalizacaoEntregaService {

    EntregaRepository entregaRepository;
    BuscaEntregaService buscaEntregaService;

    @Transactional
    public void finalizar(Long entregaId){
        Entrega entrega = buscaEntregaService.buscar(entregaId);

        entrega.finalizar();

        entregaRepository.save(entrega);

    }
}
