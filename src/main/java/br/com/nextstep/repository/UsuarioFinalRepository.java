package br.com.nextstep.repository;

import br.com.nextstep.model.UsuarioFinal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioFinalRepository extends JpaRepository<UsuarioFinal, Long> {

    Page<UsuarioFinal> findAllByOrderByIdAsc(Pageable pageable);
}
