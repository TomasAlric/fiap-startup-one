package br.com.fiap.health.dto.request;

import lombok.Data;

@Data
public class EnderecoRequest {
    private String logradouro;
    private Long numero;
    private String cep;
    private String complemento;
    private String cidade;
    private String estado;
}
