package br.com.nextstep.repository;

import br.com.nextstep.model.Nota;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Long> {
    Page<Nota> findAllByOrderByIdAsc(Pageable pageable);

    Page<Nota> findByTrilhaIdOrderByIdAsc(Long idTrilha, Pageable pageable);
}
