package br.viniciusmatos.algalogapi.domain.service;

import br.viniciusmatos.algalogapi.domain.exception.NegocioException;
import br.viniciusmatos.algalogapi.domain.model.Entrega;
import br.viniciusmatos.algalogapi.domain.model.StatusEntega;
import br.viniciusmatos.algalogapi.domain.repository.ClienteRepository;
import br.viniciusmatos.algalogapi.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@AllArgsConstructor
@Service
public class SolicitacaoEntregaService {

    private EntregaRepository entregaRepository;
    private CatalogoClienteService catalogoClienteService;

    @Transactional
    public Entrega solicitar(Entrega entrega) {

        var cliente =catalogoClienteService.buscar(entrega.getCliente().getId());

        entrega.setCliente(cliente);
        entrega.setStatus(StatusEntega.PENDENTE);
        entrega.setDataPedido(OffsetDateTime.now());

        return entregaRepository.save(entrega);
    }
}
