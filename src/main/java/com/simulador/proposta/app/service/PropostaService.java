package com.simulador.proposta.app.service;

import com.simulador.proposta.app.dto.PropostaRequestDto;
import com.simulador.proposta.app.dto.PropostaResponseDto;
import com.simulador.proposta.app.entity.Proposta;
import com.simulador.proposta.app.mapper.PropostaMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.simulador.proposta.app.repository.PropostaRepository;

import java.util.List;

@Service
@Slf4j
public class PropostaService {

    private PropostaRepository repository;

    private NotificacaoRabbitService notificacaoService;

    private String exchange;

    public PropostaService(PropostaRepository repository,
                           NotificacaoRabbitService notificacaoService,
                           @Value("${rabbitmq.propostapendente.exchange}") String exchange) {
        this.repository = repository;
        this.notificacaoService = notificacaoService;
        this.exchange = exchange;
    }

    public PropostaResponseDto criarProposta (PropostaRequestDto requestDto) {
        Proposta proposta = PropostaMapper.INSTANCE.convertDtoToProposta(requestDto);
        //cadastro feito com sucesso!
        repository.save(proposta);
        // Envia notificaçao para exchange
        notificaRabbitMq(proposta);

        return PropostaMapper.INSTANCE.convertEntityToDto(proposta);
    }

    public void notificaRabbitMq(Proposta proposta) {
        try {
            notificacaoService.notificar(proposta,exchange);
        }catch (RuntimeException e){
           proposta.setIntegrada(false);
           repository.save(proposta);
        }
    }


    public List<PropostaResponseDto> listarPropostas() {
       return  PropostaMapper.INSTANCE.convertListEntityDto(repository.findAll());
    }


}
