package br.com.nextstep.service;

import br.com.nextstep.dto.trilha.TrilhaRequestDTO;
import br.com.nextstep.dto.trilha.TrilhaResponseDTO;
import br.com.nextstep.mapper.TrilhaMapper;
import br.com.nextstep.messaging.trilha.TrilhaProducer;
import br.com.nextstep.model.Trilha;
import br.com.nextstep.model.enums.AreaTrilha;
import br.com.nextstep.model.enums.NivelTrilha;
import br.com.nextstep.model.enums.StatusTrilha;
import br.com.nextstep.repository.TrilhaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class TrilhaService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private TrilhaRepository trilhaRepository;

    @Autowired
    private TrilhaProducer trilhaProducer;

    @Transactional(readOnly = true)
    public Page<TrilhaResponseDTO> listarTodos(Pageable pageable) {
        return trilhaRepository.findAllByOrderByIdAsc(pageable)
                .map(TrilhaMapper::toResponseDTO);
    }

    @Transactional(readOnly = true)
    public Page<TrilhaResponseDTO> listarAtivas(Pageable pageable) {
        return trilhaRepository.listarTrilhasAtivas(pageable)
                .map(TrilhaMapper::toResponseDTO);
    }

    @Transactional(readOnly = true)
    public TrilhaResponseDTO buscarPorId(Long id) {
        var trilha = buscarEntidadeTrilhaPorId(id);
        return TrilhaMapper.toResponseDTO(trilha);
    }

    @Transactional
    public TrilhaResponseDTO salvar(TrilhaRequestDTO trilhaRequestDTO) {
        var trilha = TrilhaMapper.toEntity(trilhaRequestDTO);
        trilha.setStatus(StatusTrilha.ATIVA);
        var trilhaSalva = trilhaRepository.save(trilha);

        trilhaProducer.enviarTrilhaCriada(trilhaSalva);
        return TrilhaMapper.toResponseDTO(trilhaSalva);
    }

    @Transactional
    public TrilhaResponseDTO atualizar(Long id, TrilhaRequestDTO trilhaRequestDTO) {
        var trilhaAtual = buscarEntidadeTrilhaPorId(id);

        trilhaAtual.setNome(trilhaRequestDTO.getNome());
        trilhaAtual.setDescricao(trilhaRequestDTO.getDescricao());
        trilhaAtual.setArea(trilhaRequestDTO.getArea());
        trilhaAtual.setNivel(trilhaRequestDTO.getNivel());
        trilhaAtual.setStatus(trilhaRequestDTO.getStatus());

        return TrilhaMapper.toResponseDTO(trilhaRepository.save(trilhaAtual));
    }

    @Transactional
    public void deletar(Long id) {
        var trilha = buscarEntidadeTrilhaPorId(id);
        trilhaRepository.delete(trilha);
    }

    public Trilha buscarEntidadeTrilhaPorId(Long id) {
        return trilhaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Trilha com id: " + id + " não encontrada"));
    }

    @Transactional(readOnly = true)
    public List<TrilhaResponseDTO> listarTrilhasPorProcedure() {
        // Cria a procedure
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("PR_LIST_TRILHAS");

        // Registra o parâmetro de saída (SYS_REFCURSOR)
        query.registerStoredProcedureParameter("p_cursor", void.class, jakarta.persistence.ParameterMode.REF_CURSOR);

        // Executa a procedure
        query.execute();

        // Recebe o resultado
        List<Object[]> resultados = query.getResultList();
        List<TrilhaResponseDTO> trilhas = new ArrayList<>();

        for (Object[] row : resultados) {
            TrilhaResponseDTO dto = new TrilhaResponseDTO();
            dto.setId(((Long) row[0]).longValue());
            dto.setNome((String) row[1]);
            dto.setDescricao((String) row[2]);
            dto.setArea(AreaTrilha.valueOf(((String) row[3]).toUpperCase()));
            dto.setNivel(NivelTrilha.valueOf(((String) row[4]).toUpperCase()));
            dto.setStatus(StatusTrilha.valueOf(((String) row[5]).toUpperCase()));
            trilhas.add(dto);
        }

        return trilhas;
    }
}
