package br.viniciusmatos.algalogapi.api.controller;

import br.viniciusmatos.algalogapi.api.assembler.EntregaAssembler;
import br.viniciusmatos.algalogapi.api.model.DestinatarioDto;
import br.viniciusmatos.algalogapi.api.model.EntregaDto;
import br.viniciusmatos.algalogapi.api.model.input.EntregaInput;
import br.viniciusmatos.algalogapi.domain.model.Entrega;
import br.viniciusmatos.algalogapi.domain.repository.EntregaRepository;
import br.viniciusmatos.algalogapi.domain.service.FinalizacaoEntregaService;
import br.viniciusmatos.algalogapi.domain.service.SolicitacaoEntregaService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {

    private EntregaRepository entregaRepository;
    private SolicitacaoEntregaService solicitacaoEntregaService;
    private FinalizacaoEntregaService finalizacaoEntregaService;
    private EntregaAssembler entregaAssembler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaDto solicitar(@Valid @RequestBody EntregaInput entregaInput) {

        var novaEntrega = entregaAssembler.toEntity(entregaInput);

        var entregaSolicitada = solicitacaoEntregaService.solicitar(novaEntrega);

        return entregaAssembler.toDto(entregaSolicitada);
    }

    @GetMapping
    public List<EntregaDto> listar() {
        return entregaAssembler.toCollectionDto(entregaRepository.findAll());
    }

    @GetMapping("/{entregaId}")
    public ResponseEntity<EntregaDto> buscar(@PathVariable Long entregaId) {
        return entregaRepository.findById(entregaId)
                .map(entrega -> ResponseEntity.ok(entregaAssembler.toDto(entrega)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{entregaId}/finalizacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finalizar(@PathVariable Long entregaId){
        finalizacaoEntregaService.finalizar(entregaId);
    }

}
