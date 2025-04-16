package com.simulador.proposta.app.service;

import com.simulador.proposta.app.dto.PropostaResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificacaoService {

    private RabbitTemplate rabbitTemplate;

    public void notificar(PropostaResponseDto proposta, String exchange){
        rabbitTemplate.convertAndSend(exchange, "", proposta);
    }

}
