package br.com.fiap.health.dto.response;

import br.com.fiap.health.enums.TipoConsulta;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ConsultaResponse {

    private UUID id;

    private LocalDateTime dataConsulta;

    private String especialidade;

    @Enumerated(EnumType.STRING)
    private TipoConsulta tipoConsulta;
}
