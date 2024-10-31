package com.github.kpossoli.projetopcp.mapper;

import com.github.kpossoli.projetopcp.dto.UsuarioSimplifiedDto;
import org.mapstruct.Mapper;

import com.github.kpossoli.projetopcp.dto.UsuarioDto;
import com.github.kpossoli.projetopcp.model.Usuario;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioDto toDto(Usuario usuario);

    Usuario toEntity(UsuarioDto usuarioDto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "papel", source = "papel.nome")
    UsuarioSimplifiedDto toSimpliedDto (Usuario usuario);

}

