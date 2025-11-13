package br.com.nextstep.repository;

import br.com.nextstep.model.UsuarioAdmin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioAdminRepository extends JpaRepository<UsuarioAdmin, Long> {
    Page<UsuarioAdmin> findAllByOrderByIdAsc(Pageable pageable);

    UserDetails findByEmail(String email);

    Optional<UsuarioAdmin> findUsuarioByEmail(String email);
}
