package br.com.nextstep.repository;

import br.com.nextstep.model.Solicitacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long> {

    Page<Solicitacao> findAllByOrderByIdAsc(Pageable pageable);

    Optional<Solicitacao> findSolicitacaoByEmail(String email);
}
