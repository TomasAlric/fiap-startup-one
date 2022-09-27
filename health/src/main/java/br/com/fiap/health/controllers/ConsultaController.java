package br.com.fiap.health.controllers;

import br.com.fiap.health.dto.request.ConsultaRequest;
import br.com.fiap.health.dto.response.ConsultaResponse;
import br.com.fiap.health.services.ConsultaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/consulta")
public class ConsultaController {

    private final ConsultaService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<ConsultaResponse> cadastrar(@Valid @RequestBody ConsultaRequest consultaRequest, UriComponentsBuilder uriComponentsBuilder) {
        ConsultaResponse consultaResponse = service.adicionar(consultaRequest);
        URI uri = uriComponentsBuilder.path("/consulta/{id}").buildAndExpand(consultaResponse.getId()).toUri();
        return ResponseEntity.created(uri).body(consultaResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaResponse> buscarPorId(@PathVariable UUID id) {
        ConsultaResponse consultaResponse = service.buscarPorId(id);
        return ResponseEntity.ok(consultaResponse);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/horario/{id}")
    public ResponseEntity<ConsultaResponse> mudarHorario(@PathVariable UUID id, @RequestBody ConsultaRequest consultaRequest){
        ConsultaResponse consultaResponse = service.alterarHorario(id, consultaRequest);
        return ResponseEntity.ok(consultaResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> remover(@PathVariable UUID id) {
        service.remover(id);
        return ResponseEntity.ok().build();
    }

}
