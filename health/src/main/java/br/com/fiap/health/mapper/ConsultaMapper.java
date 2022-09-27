package br.com.fiap.health.mapper;

import br.com.fiap.health.dto.request.ConsultaRequest;
import br.com.fiap.health.dto.response.ConsultaResponse;
import br.com.fiap.health.models.Consulta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ConsultaMapper {


    ConsultaResponse toResponse(Consulta consulta);

    @Mapping(source = "consultaRequest.idUsuario", target = "usuario.id")
    @Mapping(source = "consultaRequest.idHospital", target = "hospital.id")
    Consulta toModel(ConsultaRequest consultaRequest);


}
