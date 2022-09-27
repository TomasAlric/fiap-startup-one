package br.com.fiap.health.services;

import br.com.fiap.health.dto.request.UsuarioRequest;
import br.com.fiap.health.dto.response.UsuarioResponse;
import br.com.fiap.health.mapper.UsuarioMapper;
import br.com.fiap.health.models.Usuario;
import br.com.fiap.health.repositories.UsuarioRepository;
import br.com.fiap.health.services.exceptions.RecursoNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioMapper mapper;
    private final UsuarioRepository repository;

    public UsuarioResponse adicionar(UsuarioRequest usuarioRequest) {
        Usuario usuario = mapper.toModel(usuarioRequest);
        return mapper.toResponse(repository.save(usuario));
    }

    public UsuarioResponse buscarPorId(UUID id) {
        Usuario usuario = buscarModelPorId(id);
        return mapper.toResponse(usuario);
    }

    public UsuarioResponse atualizar(UUID id, UsuarioRequest usuarioRequest){
        Usuario usuario = buscarModelPorId(id);
        usuario.setNome(usuarioRequest.getNome());
        usuario.setSobrenome(usuarioRequest.getSobrenome());
        usuario.setCpf(usuarioRequest.getCpf());
        usuario.setEmail(usuarioRequest.getEmail());
        usuario.setTelefone(usuarioRequest.getTelefone());
        usuario.setDataNascimento(usuarioRequest.getDataNascimento());
        usuario.setEndereco(usuarioRequest.getEndereco());
        return mapper.toResponse(repository.save(usuario));
    }

    public void remover(UUID id) {
        this.buscarPorId(id);
        repository.deleteById(id);
    }

    public Usuario buscarModelPorId(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado"));
    }
}
