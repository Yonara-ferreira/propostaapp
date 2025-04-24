package com.simulador.proposta.app.repository;

import com.simulador.proposta.app.entity.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {
    // query derivadas, onde é implementada de forma declarativa no metodo.
    List<Proposta> findAllByIntegradaIsFalse();

}
