package br.com.fiap.health.models;

import br.com.fiap.health.enums.TipoConsulta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Future;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Consulta {
    @Id
    @GeneratedValue
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @Future
    private LocalDateTime dataConsulta;

    private String especialidade;

    @Enumerated(EnumType.STRING)
    private TipoConsulta tipoConsulta;

    @ManyToOne
    @JoinColumn(name = "USUARIO_ID")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "HOSPITAL_ID")
    private Hospital hospital;

}
