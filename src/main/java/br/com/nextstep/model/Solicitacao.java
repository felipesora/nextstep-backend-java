package br.com.nextstep.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import java.time.LocalDateTime;

@Entity
@Table(name = "NS_SOLICITACAO_CONTA", uniqueConstraints = {
        @UniqueConstraint(
                name = "UK_NS_SOLICITACAO_EMAIL",
                columnNames = "email"
        )}
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Solicitacao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ns_solicitacao_seq")
    @SequenceGenerator(name = "ns_solicitacao_seq", sequenceName = "SQ_SOLICITACAO", allocationSize = 1)
    @Column(name = "ID_SOLICITACAO")
    @JsonProperty("id_solicitacao")
    private Long id;

    @Column(name = "NOME", length = 150)
    private String nome;

    @Column(name = "EMAIL", length = 150)
    private String email;

    @Column(name = "SENHA", length = 150)
    private String senha;

    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    @PrePersist
    protected void onCreate() {
        this.dataCriacao = LocalDateTime.now();
    }
}
