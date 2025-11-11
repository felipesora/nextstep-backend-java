package br.com.nextstep.repository;

import br.com.nextstep.model.Conteudo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConteudoRepository extends JpaRepository<Conteudo, Long> {

    Page<Conteudo> findAllByOrderByIdAsc(Pageable pageable);
}
