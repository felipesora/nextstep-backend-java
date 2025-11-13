package br.com.nextstep.mapper;

import br.com.nextstep.dto.usuarioFinal.UsuarioFinalResponseDTO;
import br.com.nextstep.model.UsuarioFinal;

public class UsuarioFinalMapper {

    public static UsuarioFinalResponseDTO toResponseDTO(UsuarioFinal usuarioFinal) {
        if (usuarioFinal == null) return null;

        return new UsuarioFinalResponseDTO(
                usuarioFinal.getId(),
                usuarioFinal.getNome(),
                usuarioFinal.getEmail(),
                usuarioFinal.getSenha(),
                usuarioFinal.getDataCadastro()
        );
    }
}
