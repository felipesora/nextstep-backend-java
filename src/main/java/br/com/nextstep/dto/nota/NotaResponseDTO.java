package br.com.nextstep.dto.nota;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"id_nota", "valor_nota", "observacao", "id_trilha"})
public class NotaResponseDTO {

    @JsonProperty("id_nota")
    private Long id;

    @JsonProperty("valor_nota")
    private int valorNota;

    private String observacao;

    @JsonProperty("id_trilha")
    private Long idTrilha;

    @JsonProperty("id_usuario_final")
    private Long idUsuarioFinal;
}
