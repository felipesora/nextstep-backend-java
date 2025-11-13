package br.com.nextstep.dto.usuarioFinal;

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
@JsonPropertyOrder({"id_usuario_final", "nome", "email", "senha", "data_cadastro"})
public class UsuarioFinalResponseDTO {

    @JsonProperty("id_usuario_final")
    private Long id;

    private String nome;

    private String email;

    private String senha;

    @JsonProperty("data_cadastro")
    private LocalDateTime dataCadastro;
}
