package br.com.nextstep.repository;

import br.com.nextstep.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.awt.print.Pageable;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Page<Usuario> findAllByOrderByIdAsc(Pageable pageable);

    UserDetails findByEmail(String email);
}
