package br.com.nextstep.dto.trilha;

import br.com.nextstep.model.enums.AreaTrilha;
import br.com.nextstep.model.enums.NivelTrilha;
import br.com.nextstep.model.enums.StatusTrilha;
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
public class TrilhaRequestDTO {

    @NotBlank(message = "O nome da trilha é obrigatório")
    @Size(min = 3, max = 150, message = "O nome deve ter entre 3 e 150 caracteres")
    private String nome;

    @NotBlank(message = "A descrição é obrigatória")
    @Size(min = 10, max = 400, message = "A descrição deve ter entre 10 e 400 caracteres")
    private String descricao;

    @NotNull(message = "A área da trilha é obrigatório")
    private AreaTrilha area;

    @NotNull(message = "O nível da trilha é obrigatório")
    private NivelTrilha nivel;

    @NotNull(message = "O status da trilha é obrigatório")
    private StatusTrilha status;
}
