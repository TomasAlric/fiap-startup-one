package br.com.fiap.health.dto.response;

import br.com.fiap.health.models.Endereco;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class UsuarioResponse {

    private UUID id;

    private String nome;

    private String sobrenome;

    private String email;

    private String telefone;

    private Endereco endereco;

    private String cpf;

    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate dataNascimento;
}
