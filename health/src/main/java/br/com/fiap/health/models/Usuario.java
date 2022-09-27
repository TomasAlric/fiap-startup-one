package br.com.fiap.health.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID id;

    @Column(nullable = false)
    @Size(min = 3, max = 15)
    private String nome;

    private String sobrenome;

    @Email
    private String email;

    private String telefone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ENDERECO_ID")
    private Endereco endereco;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @Past
    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "usuario")
    private List<Consulta> consultas = new ArrayList<>();
}
