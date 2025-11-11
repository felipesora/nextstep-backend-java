package br.com.nextstep.mapper;

import br.com.nextstep.dto.conteudo.ConteudoRequestDTO;
import br.com.nextstep.dto.conteudo.ConteudoResponseDTO;
import br.com.nextstep.model.Conteudo;
import br.com.nextstep.model.Trilha;

public class ConteudoMapper {

    public static ConteudoResponseDTO toResponseDTO(Conteudo conteudo) {
        if (conteudo == null) return null;

        Long trilhaId = conteudo.getTrilha() != null ? conteudo.getTrilha().getId() : null;

        return new ConteudoResponseDTO(
                conteudo.getId(),
                conteudo.getTitulo(),
                conteudo.getDescricao(),
                conteudo.getTipo(),
                conteudo.getLink(),
                trilhaId,
                conteudo.getDataCriacao()
        );
    }

    public static Conteudo toEntity(ConteudoRequestDTO dto, Trilha trilha) {
        if (dto == null) return null;

        Conteudo conteudo = new Conteudo();
        conteudo.setTitulo(dto.getTitulo());
        conteudo.setDescricao(dto.getDescricao());
        conteudo.setTipo(dto.getTipo());
        conteudo.setLink(dto.getLink());
        conteudo.setTrilha(trilha);
        return conteudo;
    }
}
