package br.com.fiap.health.mapper;

import br.com.fiap.health.dto.request.UsuarioRequest;
import br.com.fiap.health.dto.response.UsuarioResponse;
import br.com.fiap.health.models.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioResponse toResponse(Usuario usuario);

    Usuario toModel(UsuarioRequest usuarioRequest);


}
