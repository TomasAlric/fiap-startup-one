package br.com.fiap.health.services;

import br.com.fiap.health.dto.request.ConsultaRequest;
import br.com.fiap.health.dto.response.ConsultaResponse;
import br.com.fiap.health.mapper.ConsultaMapper;
import br.com.fiap.health.models.Consulta;
import br.com.fiap.health.repositories.ConsultaRepository;
import br.com.fiap.health.services.exceptions.RecursoNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConsultaService {

    private final ConsultaMapper mapper;
    private final ConsultaRepository repository;

    public ConsultaResponse adicionar(ConsultaRequest consultaRequest) {

        Consulta consulta = mapper.toModel(consultaRequest);

        return mapper.toResponse(repository.save(consulta));
    }

    public Consulta buscarModelPorId(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Consulta não encontrada"));
    }

    public ConsultaResponse buscarPorId(UUID id) {
        return mapper.toResponse(buscarModelPorId(id));
    }

    public ConsultaResponse alterarHorario(UUID id, ConsultaRequest consultaRequest){
        Consulta consulta = buscarModelPorId(id);
        consulta.setDataConsulta(consultaRequest.getDataConsulta());
        return mapper.toResponse(repository.save(consulta));
    }

    public void remover(UUID id) {
        this.buscarPorId(id);
        repository.deleteById(id);
    }
}
