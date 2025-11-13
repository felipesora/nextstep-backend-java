package br.com.nextstep.service;

import br.com.nextstep.dto.nota.NotaResponseDTO;
import br.com.nextstep.mapper.NotaMapper;
import br.com.nextstep.repository.NotaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NotaService {

    @Autowired
    private NotaRepository notaRepository;

    @Transactional(readOnly = true)
    public Page<NotaResponseDTO> listarTodos(Pageable pageable) {
        return notaRepository.findAllByOrderByIdAsc(pageable)
                .map(NotaMapper::toResponseDTO);
    }

    @Transactional(readOnly = true)
    public Page<NotaResponseDTO> listarNotasPorIdTrilha(Long idTrilha, Pageable pageable) {
        return notaRepository.findByTrilhaIdOrderByIdAsc(idTrilha, pageable)
                .map(NotaMapper::toResponseDTO);
    }

    @Transactional(readOnly = true)
    public NotaResponseDTO buscarPorId(Long id) {
        var nota = notaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Nota com id: " + id + " não encontrada"));

        return NotaMapper.toResponseDTO(nota);
    }
}
