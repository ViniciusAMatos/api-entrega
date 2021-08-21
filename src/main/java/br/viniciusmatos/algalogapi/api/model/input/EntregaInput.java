package br.viniciusmatos.algalogapi.api.model.input;

import br.viniciusmatos.algalogapi.api.model.DestinatarioDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class EntregaInput {

    @Valid
    @NotNull
    private ClienteIdInput cliente;

    @Valid
    @NotNull
    private DestinatarioInput destinatario;


    private BigDecimal taxa;
}
