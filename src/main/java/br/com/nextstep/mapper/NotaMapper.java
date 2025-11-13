package br.com.nextstep.mapper;

import br.com.nextstep.dto.nota.NotaResponseDTO;
import br.com.nextstep.model.Nota;

public class NotaMapper {
    public static NotaResponseDTO toResponseDTO(Nota nota) {
        if (nota == null) return null;

        Long trilhaId = nota.getTrilha() != null ? nota.getTrilha().getId() : null;

        Long usuarioFinalId = nota.getUsuarioFinal() != null? nota.getUsuarioFinal().getId() : null;

        return new NotaResponseDTO(
                nota.getId(),
                nota.getValorNota(),
                nota.getObservacao(),
                trilhaId,
                usuarioFinalId
        );
    }
}
