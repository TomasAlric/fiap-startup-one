package br.com.fiap.health.controllers;

import br.com.fiap.health.dto.request.HospitalRequest;
import br.com.fiap.health.dto.response.HospitalResponse;
import br.com.fiap.health.services.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hospital")
public class HospitalController {
    
    private final HospitalService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<HospitalResponse> cadastrar(@Valid @RequestBody HospitalRequest hospitalRequest, UriComponentsBuilder uriComponentsBuilder) {
        HospitalResponse hospitalResponse = service.adicionar(hospitalRequest);
        URI uri = uriComponentsBuilder.path("/hospital/{id}").buildAndExpand(hospitalResponse.getId()).toUri();
        return ResponseEntity.created(uri).body(hospitalResponse);
    }

    @GetMapping
    public ResponseEntity<Page<HospitalResponse>> listar(@PageableDefault(size = 5, direction = Sort.Direction.ASC) Pageable pageable) {
        Page<HospitalResponse> cursos = service.listarPaginado(pageable);
        return ResponseEntity.ok(cursos);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> remover(@PathVariable UUID id) {
        service.remover(id);
        return ResponseEntity.ok().build();
    }
}
