package com.github.kpossoli.projetopcp.mapper;

import org.mapstruct.Mapper;

import com.github.kpossoli.projetopcp.dto.UsuarioDto;
import com.github.kpossoli.projetopcp.model.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioDto toDto(Usuario usuario);

    Usuario toEntity(UsuarioDto usuarioDto);

}

