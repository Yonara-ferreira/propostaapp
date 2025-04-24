package com.simulador.proposta.app.agendador;

import com.simulador.proposta.app.entity.Proposta;
import com.simulador.proposta.app.repository.PropostaRepository;
import com.simulador.proposta.app.service.NotificacaoRabbitService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static java.rmi.server.LogStream.log;

@Component
@Log4j2
public class PropostaSemIntegracao {

    @Autowired
    private PropostaRepository repository;

    @Autowired
    private NotificacaoRabbitService service;

    private String exchange;

    public PropostaSemIntegracao(PropostaRepository repository,
                                 NotificacaoRabbitService service,
                                 @Value("${rabbitmq.propostapendente.exchange}") String exchange) {
        this.repository = repository;
        this.service = service;
        this.exchange = exchange;
    }

    public void buscarPropostaSemIntegracao() {
        repository.findAllByIntegradaIsFalse().forEach(proposta -> {
            try {
                service.notificar(proposta, exchange);
                atualizaProposta(proposta);
            } catch (RuntimeException ex){
                log(ex.getMessage());
            }

        });
    }

    private void atualizaProposta(Proposta proposta){
        proposta.setIntegrada(true);
        repository.save(proposta);
    }
}
