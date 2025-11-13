package br.com.nextstep.mapper;

import br.com.nextstep.dto.conteudo.ConteudoResponseDTO;
import br.com.nextstep.dto.nota.NotaResponseDTO;
import br.com.nextstep.dto.trilha.TrilhaRequestDTO;
import br.com.nextstep.dto.trilha.TrilhaResponseDTO;
import br.com.nextstep.model.Trilha;

import java.util.List;

public class TrilhaMapper {
    public static TrilhaResponseDTO toResponseDTO(Trilha trilha) {
        if (trilha == null) return null;

        List<ConteudoResponseDTO> conteudosDTO = trilha.getConteudos() != null ?
                trilha.getConteudos().stream()
                        .map(ConteudoMapper::toResponseDTO)
                        .toList()
                : null;

        List<NotaResponseDTO> notasDTO = trilha.getNotas() != null ?
                trilha.getNotas().stream()
                        .map(NotaMapper::toResponseDTO)
                        .toList()
                : null;

        return new TrilhaResponseDTO(
                trilha.getId(),
                trilha.getNome(),
                trilha.getDescricao(),
                trilha.getArea(),
                trilha.getNivel(),
                trilha.getStatus(),
                conteudosDTO,
                notasDTO,
                trilha.getDataCriacao()
        );
    }

    public static Trilha toEntity(TrilhaRequestDTO dto) {
        if (dto == null) return null;

        Trilha trilha = new Trilha();
        trilha.setNome(dto.getNome());
        trilha.setDescricao(dto.getDescricao());
        trilha.setArea(dto.getArea());
        trilha.setNivel(dto.getNivel());
        trilha.setStatus(dto.getStatus());
        return trilha;
    }
}
