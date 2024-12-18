package com.simulador.proposta.app.service;

import com.simulador.proposta.app.dto.PropostaRequestDto;
import com.simulador.proposta.app.dto.PropostaResponseDto;
import com.simulador.proposta.app.entity.Proposta;
import com.simulador.proposta.app.mapper.PropostaMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.simulador.proposta.app.repository.PropostaRepository;

@Service
@Slf4j
public class PropostaService {

    @Autowired
    private PropostaRepository repository;

    public PropostaResponseDto criarProposta (PropostaRequestDto requestDto) {
        Proposta proposta = PropostaMapper.INSTANCE.convertDtoToProposta(requestDto);
        repository.save(proposta);

        return PropostaMapper.INSTANCE.convertEntityToDto(proposta);

    }


}
