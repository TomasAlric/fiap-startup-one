package br.com.fiap.health.dto.response;

import lombok.Data;

import java.util.UUID;

@Data
public class HospitalResponse {
    private UUID id;

    private String nome;

    private Integer medicos;

    private Double valorConsulta;
}
