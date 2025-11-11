package br.com.nextstep.repository;

import br.com.nextstep.model.Trilha;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrilhaRepository extends JpaRepository<Trilha, Long> {
    Page<Trilha> findAllByOrderByIdAsc(Pageable pageable);
}
