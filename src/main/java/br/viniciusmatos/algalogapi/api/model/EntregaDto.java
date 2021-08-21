package br.viniciusmatos.algalogapi.api.model;

import br.viniciusmatos.algalogapi.domain.model.StatusEntega;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class EntregaDto {

    private Long id;
    private ClienteResumoDto cliente;
    private DestinatarioDto destinatario;
    private BigDecimal taxa;
    private StatusEntega status;
    private OffsetDateTime datapedido;
    private OffsetDateTime dataFinalizacao;

}
