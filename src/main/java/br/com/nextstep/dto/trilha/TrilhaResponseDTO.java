package br.com.nextstep.dto.trilha;

import br.com.nextstep.model.enums.AreaTrilha;
import br.com.nextstep.model.enums.NivelTrilha;
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
@JsonPropertyOrder({"id_trilha", "nome", "descricao", "area", "nivel", "data_criacao"})
public class TrilhaResponseDTO {

    @JsonProperty("id_trilha")
    private Long id;

    private String nome;

    private String descricao;

    private AreaTrilha area;

    private NivelTrilha nivel;

    @JsonProperty("data_criacao")
    private LocalDateTime dataCriacao;
}
