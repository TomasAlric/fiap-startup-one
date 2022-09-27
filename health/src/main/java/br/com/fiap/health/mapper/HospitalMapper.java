package br.com.fiap.health.mapper;

import br.com.fiap.health.dto.request.HospitalRequest;
import br.com.fiap.health.dto.response.HospitalResponse;
import br.com.fiap.health.models.Hospital;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HospitalMapper {

    HospitalResponse toResponse(Hospital hospital);

    Hospital toModel(HospitalRequest hospitalRequest);

    List<HospitalResponse> toResponseList(List<Hospital> hospitais);
}
