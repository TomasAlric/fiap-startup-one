package br.com.fiap.health.dto.request;

import lombok.Data;

@Data
public class HospitalRequest {
    private String nome;
    private Integer medicos;
    private Double valorConsulta;
}
