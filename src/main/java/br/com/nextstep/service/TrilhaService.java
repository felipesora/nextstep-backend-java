package br.com.nextstep.service;

import br.com.nextstep.dto.trilha.TrilhaRequestDTO;
import br.com.nextstep.dto.trilha.TrilhaResponseDTO;
import br.com.nextstep.mapper.TrilhaMapper;
import br.com.nextstep.messaging.trilha.TrilhaProducer;
import br.com.nextstep.model.Trilha;
import br.com.nextstep.model.enums.StatusTrilha;
import br.com.nextstep.repository.TrilhaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TrilhaService {

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
}
