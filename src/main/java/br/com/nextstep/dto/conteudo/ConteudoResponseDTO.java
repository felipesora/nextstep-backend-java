package br.com.nextstep.dto.conteudo;

import br.com.nextstep.model.enums.TipoConteudo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"id_conteudo", "titulo", "descricao", "tipo", "link", "id_trilha", "data_criacao"})
public class ConteudoResponseDTO {

    @JsonProperty("id_conteudo")
    private Long id;

    private String titulo;

    private String descricao;

    private TipoConteudo tipo;

    private String link;

    @JsonProperty("id_trilha")
    private Long idTrilha;

    @JsonProperty("data_criacao")
    private LocalDateTime dataCriacao;
}
