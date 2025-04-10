package com.simulador.proposta.app.service;

import com.simulador.proposta.app.dto.PropostaRequestDto;
import com.simulador.proposta.app.dto.PropostaResponseDto;
import com.simulador.proposta.app.entity.Proposta;
import com.simulador.proposta.app.mapper.PropostaMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.simulador.proposta.app.repository.PropostaRepository;

import java.util.List;

@Service
@Slf4j
public class PropostaService {

    @Autowired
    private PropostaRepository repository;

    @Autowired
    private NotificacaoService notificacaoService;

    public PropostaResponseDto criarProposta (PropostaRequestDto requestDto) {
        Proposta proposta = PropostaMapper.INSTANCE.convertDtoToProposta(requestDto);
        //cadastro feito com sucesso!
        repository.save(proposta);

        PropostaResponseDto response = PropostaMapper.INSTANCE.convertEntityToDto(proposta);
        // Envia notificaçao para exchange
        notificacaoService.notificar(response,"proposta-pendente.ex");

        return response;

    }


    public List<PropostaResponseDto> listarPropostas() {
       return  PropostaMapper.INSTANCE.convertListEntityDto(repository.findAll());
    }
}
