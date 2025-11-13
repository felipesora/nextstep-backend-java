package br.com.nextstep.model;

import br.com.nextstep.model.enums.AreaTrilha;
import br.com.nextstep.model.enums.NivelTrilha;
import br.com.nextstep.model.enums.StatusTrilha;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "NS_TRILHA")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Trilha {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ns_trilha_seq")
    @SequenceGenerator(name = "ns_trilha_seq", sequenceName = "SQ_TRILHA", allocationSize = 1)
    @Column(name = "id_trilha")
    @JsonProperty("id_trilha")
    private Long id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false, length = 400)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "area", nullable = false, length = 30)
    private AreaTrilha area;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel", nullable = false, length = 30)
    private NivelTrilha nivel;

    @OneToMany(mappedBy = "trilha", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, orphanRemoval = true)
    @OrderBy("id ASC")
    private List<Conteudo> conteudos;

    @OneToMany(mappedBy = "trilha", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, orphanRemoval = true)
    @OrderBy("id ASC")
    private List<Nota> notas;

    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 30)
    private StatusTrilha status;

    @PrePersist
    protected void onCreate() {
        this.dataCriacao = LocalDateTime.now();
    }
}
