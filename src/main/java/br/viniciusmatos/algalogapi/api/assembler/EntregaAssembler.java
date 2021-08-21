package br.viniciusmatos.algalogapi.api.assembler;

import br.viniciusmatos.algalogapi.api.model.EntregaDto;
import br.viniciusmatos.algalogapi.api.model.input.EntregaInput;
import br.viniciusmatos.algalogapi.domain.model.Entrega;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class EntregaAssembler {

    private ModelMapper modelMapper;

    public EntregaDto toDto(Entrega entrega){
        return modelMapper.map(entrega, EntregaDto.class);
    }

    public List<EntregaDto> toCollectionDto(List<Entrega> entregas){
        return entregas.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Entrega toEntity(@Valid EntregaInput entregaDto){
        return modelMapper.map(entregaDto, Entrega.class);
    }
}
