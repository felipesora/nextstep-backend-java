package br.com.nextstep.repository;

import br.com.nextstep.model.Trilha;
import br.com.nextstep.model.enums.StatusTrilha;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TrilhaRepository extends JpaRepository<Trilha, Long> {
    Page<Trilha> findAllByOrderByIdAsc(Pageable pageable);

    @Query("SELECT t FROM Trilha t WHERE t.status = 'ATIVA' ORDER BY t.id ASC")
    Page<Trilha> listarTrilhasAtivas(Pageable pageable);
}
