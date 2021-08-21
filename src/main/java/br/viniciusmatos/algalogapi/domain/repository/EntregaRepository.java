package br.viniciusmatos.algalogapi.domain.repository;

import br.viniciusmatos.algalogapi.domain.model.Entrega;
import lombok.extern.java.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Long> {
}
