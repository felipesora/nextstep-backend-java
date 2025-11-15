package br.com.nextstep.mapper;

import br.com.nextstep.dto.solicitacao.SolicitacaoRequestDTO;
import br.com.nextstep.dto.solicitacao.SolicitacaoResponseDTO;
import br.com.nextstep.model.Solicitacao;

public class SolicitacaoMapper {
    public static SolicitacaoResponseDTO toResponseDTO(Solicitacao solicitacao) {
        if (solicitacao == null) return null;

        return new SolicitacaoResponseDTO(
                solicitacao.getId(),
                solicitacao.getNome(),
                solicitacao.getEmail(),
                solicitacao.getSenha(),
                solicitacao.getDataCriacao()
        );
    }

    public static Solicitacao toEntity(SolicitacaoRequestDTO dto) {
        if (dto == null) return null;

        Solicitacao solicitacao = new Solicitacao();
        solicitacao.setNome(dto.getNome());
        solicitacao.setEmail(dto.getEmail());
        solicitacao.setSenha(dto.getSenha());
        return solicitacao;
    }
}
