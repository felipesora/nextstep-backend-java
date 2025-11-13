package br.com.nextstep.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "NS_NOTA_TRILHA")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Nota {

    @Id
    @Column(name = "ID_NOTA")
    @JsonProperty("id_nota")
    private Long id;

    @Column(name = "VALOR_NOTA")
    @JsonProperty("valor_nota")
    private int valorNota;

    @Column(name = "OBSERVACAO", length = 400)
    private String observacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_trilha")
    @JsonIgnore
    private Trilha trilha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario_final")
    @JsonIgnore
    private UsuarioFinal usuarioFinal;
}
