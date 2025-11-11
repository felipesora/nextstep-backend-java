package br.com.nextstep.model;

import br.com.nextstep.model.enums.TipoConteudo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "NS_CONTEUDO_TRILHA")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Conteudo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ns_conteudo_trilha_seq")
    @SequenceGenerator(name = "ns_conteudo_trilha_seq", sequenceName = "SQ_CONTEUDO_TRILHA", allocationSize = 1)
    @Column(name = "id_conteudo")
    @JsonProperty("id_conteudo")
    private Long id;

    @Column(nullable = false, length = 150)
    private String titulo;

    @Column(nullable = false, length = 400)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 30)
    private TipoConteudo tipo;

    @Column(nullable = true, length = 150)
    private String link;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_trilha")
    @JsonIgnore
    private Trilha trilha;

    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    @PrePersist
    protected void onCreate() {
        this.dataCriacao = LocalDateTime.now();
    }
}
