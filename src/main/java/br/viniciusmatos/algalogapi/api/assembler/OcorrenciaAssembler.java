package br.viniciusmatos.algalogapi.api.assembler;

import br.viniciusmatos.algalogapi.api.model.OcorrenciaDto;
import br.viniciusmatos.algalogapi.domain.model.Ocorrencia;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class OcorrenciaAssembler {

    private ModelMapper modelMapper;

    public OcorrenciaDto toModel(Ocorrencia ocorrencia){
        return modelMapper.map(ocorrencia, OcorrenciaDto.class);
    }

    public List<OcorrenciaDto> toCollectionModel(List<Ocorrencia> ocorrencias){
        return ocorrencias.stream().map(this::toModel).collect(Collectors.toList());
    }
}
