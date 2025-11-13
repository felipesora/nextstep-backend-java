package br.com.nextstep.mapper;

import br.com.nextstep.dto.usuarioAdmin.UsuarioAdminRequestDTO;
import br.com.nextstep.dto.usuarioAdmin.UsuarioAdminResponseDTO;
import br.com.nextstep.model.UsuarioAdmin;

public class UsuarioAdminMapper {
    public static UsuarioAdminResponseDTO toResponseDTO(UsuarioAdmin usuarioAdmin) {
        if (usuarioAdmin == null) return null;

        return new UsuarioAdminResponseDTO(
                usuarioAdmin.getId(),
                usuarioAdmin.getNome(),
                usuarioAdmin.getEmail(),
                usuarioAdmin.getSenha(),
                usuarioAdmin.getDataCriacao()
        );
    }

    public static UsuarioAdmin toEntity(UsuarioAdminRequestDTO dto) {
        if (dto == null) return null;

        UsuarioAdmin usuario = new UsuarioAdmin();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        return usuario;
    }
}
