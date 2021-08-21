package br.viniciusmatos.algalogapi.domain.model;

import br.viniciusmatos.algalogapi.domain.exception.NegocioException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Entrega {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cliente cliente;

    @Embedded
    private Destinatario destinatario;

    private BigDecimal taxa;

    @Enumerated(EnumType.STRING)
    private StatusEntega status;

    private OffsetDateTime dataPedido;

    private OffsetDateTime dataFinalizacao;

    @OneToMany(mappedBy = "entrega",cascade = CascadeType.ALL)
    private List<Ocorrencia> ocorrencias = new ArrayList<>();


    public Ocorrencia adicionarOcorrencia(String descricao) {
        Ocorrencia ocorrencia = new Ocorrencia();

        ocorrencia.setDescricao(descricao);
        ocorrencia.setDataRegistro(OffsetDateTime.now());
        ocorrencia.setEntrega(this);

        this.getOcorrencias().add(ocorrencia);

        return ocorrencia;
    }

    public void finalizar() {
        if(!podeSerFinalizada()) {
            throw new NegocioException("Entrega não pode ser finalizada");
        }
        setStatus(StatusEntega.FINALIZADA);
        setDataFinalizacao(OffsetDateTime.now());
    }

    public boolean podeSerFinalizada(){
        return StatusEntega.PENDENTE.equals(getStatus());
    }
}
