package br.viniciusmatos.algalogapi.api.controller;

import br.viniciusmatos.algalogapi.api.assembler.OcorrenciaAssembler;
import br.viniciusmatos.algalogapi.api.model.OcorrenciaDto;
import br.viniciusmatos.algalogapi.api.model.input.OcorrenciaInput;
import br.viniciusmatos.algalogapi.domain.model.Entrega;
import br.viniciusmatos.algalogapi.domain.model.Ocorrencia;
import br.viniciusmatos.algalogapi.domain.service.BuscaEntregaService;
import br.viniciusmatos.algalogapi.domain.service.RegistroOcorrenciaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas/{entregaId}/ocorrencias")
public class OcorrenciaController {

    private BuscaEntregaService buscaEntregaService;
    private RegistroOcorrenciaService registroOcorrenciaService;
    private OcorrenciaAssembler ocorrenciaAssembler;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OcorrenciaDto registrar(@PathVariable Long entregaId, @Valid @RequestBody OcorrenciaInput ocorrenciaInput) {
        Ocorrencia ocorrenciaRegistrada = registroOcorrenciaService.registrar(entregaId, ocorrenciaInput.getDescricao());

        return ocorrenciaAssembler.toModel(ocorrenciaRegistrada);
    }

    @GetMapping
    public List<OcorrenciaDto> listar(@PathVariable Long entregaId){
        Entrega entrega = buscaEntregaService.buscar(entregaId);

        return ocorrenciaAssembler.toCollectionModel(entrega.getOcorrencias());
    }
}
