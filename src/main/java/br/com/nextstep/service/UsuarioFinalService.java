package br.com.nextstep.service;

import br.com.nextstep.dto.usuarioFinal.UsuarioFinalResponseDTO;
import br.com.nextstep.mapper.UsuarioFinalMapper;
import br.com.nextstep.repository.UsuarioFinalRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioFinalService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UsuarioFinalRepository usuarioRepository;

    @Transactional(readOnly = true)
    public Page<UsuarioFinalResponseDTO> listarTodos(Pageable pageable) {
        return usuarioRepository.findAllByOrderByIdAsc(pageable)
                .map(UsuarioFinalMapper::toResponseDTO);
    }

    @Transactional(readOnly = true)
    public UsuarioFinalResponseDTO buscarPorId(Long id) {
        var usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário Final com id: " + id + " não encontrado"));

        return UsuarioFinalMapper.toResponseDTO(usuario);
    }

    @Transactional(readOnly = true)
    public List<UsuarioFinalResponseDTO> listarUsuariosFinaisPorProcedure() {
        // Cria a procedure
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("PR_LIST_USUARIOS");

        // Registra o parâmetro de saída (SYS_REFCURSOR)
        query.registerStoredProcedureParameter("p_cursor", void.class, jakarta.persistence.ParameterMode.REF_CURSOR);

        // Executa a procedure
        query.execute();

        // Recebe o resultado
        List<Object[]> resultados = query.getResultList();
        List<UsuarioFinalResponseDTO> usuarios = new ArrayList<>();

        for (Object[] row : resultados) {
            UsuarioFinalResponseDTO dto = new UsuarioFinalResponseDTO();
            dto.setId(((Long) row[0]).longValue());
            dto.setNome((String) row[1]);
            dto.setEmail((String) row[2]);
            dto.setSenha((String) row[3]);

            Timestamp ts = (Timestamp) row[4];
            dto.setDataCadastro(ts != null ? ts.toLocalDateTime() : null);
            usuarios.add(dto);
        }

        return usuarios;
    }
}
