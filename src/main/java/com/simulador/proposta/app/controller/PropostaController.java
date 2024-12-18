package com.simulador.proposta.app.controller;

import com.simulador.proposta.app.dto.PropostaRequestDto;
import com.simulador.proposta.app.dto.PropostaResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.simulador.proposta.app.service.PropostaService;

@RestController
@RequestMapping(value = "/proposta")
@AllArgsConstructor
public class PropostaController {

    private PropostaService propostaService;

    @PostMapping
    public ResponseEntity<PropostaResponseDto> criar (@RequestBody PropostaRequestDto requestDto) {
        PropostaResponseDto responseDto = propostaService.criarProposta(requestDto);

        return ResponseEntity.ok(responseDto);
    }


}
