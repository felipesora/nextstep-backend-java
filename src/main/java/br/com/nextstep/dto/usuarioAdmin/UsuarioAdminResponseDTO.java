package br.com.nextstep.dto.usuarioAdmin;

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
@JsonPropertyOrder({"id_usuario", "nome", "email", "senha", "data_criacao"})
public class UsuarioAdminResponseDTO {

    @JsonProperty("id_usuario")
    private Long id;

    private String nome;

    private String email;

    private String senha;

    @JsonProperty("data_criacao")
    private LocalDateTime dataCriacao;

}


