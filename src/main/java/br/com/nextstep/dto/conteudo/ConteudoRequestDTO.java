package br.com.nextstep.dto.conteudo;

import br.com.nextstep.model.enums.TipoConteudo;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConteudoRequestDTO {

    @NotBlank(message = "O título do conteúdo é obrigatório")
    @Size(min = 3, max = 150, message = "O título deve ter entre 3 e 150 caracteres")
    private String titulo;

    @NotBlank(message = "A descrição é obrigatória")
    @Size(min = 10, max = 400, message = "A descrição deve ter entre 10 e 400 caracteres")
    private String descricao;

    @NotNull(message = "O tipo do conteúdo é obrigatório")
    private TipoConteudo tipo;

    private String link;

    @NotNull(message = "O id da trilha é obrigatório")
    @JsonProperty("id_trilha")
    private Long idTrilha;
}
