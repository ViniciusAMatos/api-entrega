package br.viniciusmatos.algalogapi.domain.service;

import br.viniciusmatos.algalogapi.domain.exception.NegocioException;
import br.viniciusmatos.algalogapi.domain.model.Entrega;
import br.viniciusmatos.algalogapi.domain.model.Ocorrencia;
import br.viniciusmatos.algalogapi.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
public class RegistroOcorrenciaService {

  private BuscaEntregaService buscaEntregaService;


   @Transactional
    public Ocorrencia registrar(Long entregaId, String descricao){
        Entrega entrega = buscaEntregaService.buscar(entregaId);

        return entrega.adicionarOcorrencia(descricao);
    }
}
