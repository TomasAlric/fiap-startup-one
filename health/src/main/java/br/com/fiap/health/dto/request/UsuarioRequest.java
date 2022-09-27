package br.com.fiap.health.dto.request;

import br.com.fiap.health.config.Telefone;
import br.com.fiap.health.models.Endereco;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;
import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class UsuarioRequest {

    @NotBlank
    @Column(nullable = false)
    @Size(min = 3, max = 15)
    private String nome;

    @NotNull(message = "O sobrenome não pode ser nulo")
    private String sobrenome;

    @Email
    private String email;

    @Telefone
    private String telefone;

    @NotNull(message = "É necessário informar um endereço")
    private Endereco endereco;

    @CPF
    private String cpf;

    @JsonFormat(pattern="dd-MM-yyyy")
    @NotNull(message = "A data de nascimento não pode ser nula")
    private LocalDate dataNascimento;
}
