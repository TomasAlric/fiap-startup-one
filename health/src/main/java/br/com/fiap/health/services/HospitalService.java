package br.com.fiap.health.services;

import br.com.fiap.health.dto.request.HospitalRequest;
import br.com.fiap.health.dto.response.HospitalResponse;
import br.com.fiap.health.mapper.HospitalMapper;
import br.com.fiap.health.models.Hospital;
import br.com.fiap.health.repositories.HospitalRepository;
import br.com.fiap.health.services.exceptions.RecursoNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class HospitalService {

    private final HospitalMapper mapper;
    private final HospitalRepository repository;

    public HospitalResponse adicionar(HospitalRequest hospitalRequest) {
        Hospital hospital = mapper.toModel(hospitalRequest);
        return mapper.toResponse(repository.save(hospital));
    }


    public Page<HospitalResponse> listarPaginado(Pageable pageable) {
        Page<Hospital> hospitais = repository.findAll(pageable);
        List<HospitalResponse> hospitalResponses = mapper.toResponseList(hospitais.getContent());
        return new PageImpl<>(hospitalResponses, pageable, hospitalResponses.size());
    }

    public void remover(UUID id) {
        this.buscarPorId(id);
        repository.deleteById(id);
    }

    public HospitalResponse buscarPorId(UUID id) {
        Hospital hospital = buscarModelPorId(id);
        return mapper.toResponse(hospital);
    }

    public Hospital buscarModelPorId(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Hospital não encontrado"));
    }
}
