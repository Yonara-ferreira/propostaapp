package com.simulador.proposta.app.controller;

import com.simulador.proposta.app.dto.PropostaRequestDto;
import com.simulador.proposta.app.dto.PropostaResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import com.simulador.proposta.app.service.PropostaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value = "/proposta")
@AllArgsConstructor
public class PropostaController {

    private PropostaService propostaService;

    @PostMapping
    public ResponseEntity<PropostaResponseDto> criar (@RequestBody PropostaRequestDto requestDto) {
        PropostaResponseDto responseDto = propostaService.criarProposta(requestDto);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseDto.getId())
                .toUri()).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<PropostaResponseDto>> listarPropostas(){
        return ResponseEntity.ok(propostaService.listarPropostas());
    }



}
