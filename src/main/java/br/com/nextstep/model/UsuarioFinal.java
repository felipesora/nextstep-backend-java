package br.com.nextstep.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Immutable
@Table(name = "NS_USUARIO_FINAL")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioFinal {

    @Id
    @Column(name = "ID_USUARIO_FINAL")
    @JsonProperty("id_usuario_final")
    private Long id;

    @Column(name = "NOME", length = 150)
    private String nome;

    @Column(name = "EMAIL", length = 150)
    private String email;

    @Column(name = "SENHA", length = 150)
    private String senha;

    @Column(name = "DATA_CADASTRO")
    private LocalDateTime dataCadastro;

    @OneToMany(mappedBy = "usuarioFinal", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, orphanRemoval = true)
    @OrderBy("id ASC")
    private List<Nota> notas;
}
