package br.com.fiap.health.dto.request;

import br.com.fiap.health.enums.TipoConsulta;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Future;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ConsultaRequest {

    @Future(message = "É necessário que seja uma data futura")
    @JsonFormat(pattern="dd-MM-yyyy HH:mm")
    private LocalDateTime dataConsulta;

    private String especialidade;

    @Enumerated(EnumType.STRING)
    private TipoConsulta tipoConsulta;

    private UUID idUsuario;

    private UUID idHospital;
}
