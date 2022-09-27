package br.com.fiap.health.controllers;

import br.com.fiap.health.dto.request.UsuarioRequest;
import br.com.fiap.health.dto.response.UsuarioResponse;
import br.com.fiap.health.services.UsuarioService;
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
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<UsuarioResponse> cadastrar(@Valid @RequestBody UsuarioRequest usuarioRequest, UriComponentsBuilder uriComponentsBuilder) {
        UsuarioResponse usuarioResponse = service.adicionar(usuarioRequest);
        URI uri = uriComponentsBuilder.path("/usuario/{id}").buildAndExpand(usuarioResponse.getId()).toUri();
        return ResponseEntity.created(uri).body(usuarioResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> buscarPorId(@PathVariable UUID id) {
        UsuarioResponse usuarioResponse = service.buscarPorId(id);
        return ResponseEntity.ok(usuarioResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> atualizar(@PathVariable UUID id, @RequestBody UsuarioRequest usuarioRequest){
        UsuarioResponse usuarioResponse = service.atualizar(id, usuarioRequest);
        return ResponseEntity.ok(usuarioResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> remover(@PathVariable UUID id) {
        service.remover(id);
        return ResponseEntity.ok().build();
    }

}
