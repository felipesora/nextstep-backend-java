package br.com.nextstep.service;

import br.com.nextstep.dto.conteudo.ConteudoRequestDTO;
import br.com.nextstep.dto.conteudo.ConteudoResponseDTO;
import br.com.nextstep.mapper.ConteudoMapper;
import br.com.nextstep.model.Conteudo;
import br.com.nextstep.repository.ConteudoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ConteudoService {

    @Autowired
    private ConteudoRepository conteudoRepository;

    @Autowired
    private TrilhaService trilhaService;

    @Transactional(readOnly = true)
    public Page<ConteudoResponseDTO> listarTodos(Pageable pageable) {
        return conteudoRepository.findAllByOrderByIdAsc(pageable)
                .map(ConteudoMapper::toResponseDTO);
    }

    @Transactional(readOnly = true)
    public ConteudoResponseDTO buscarPorId(Long id) {
        var conteudo = buscarEntidadeConteudoPorId(id);
        return ConteudoMapper.toResponseDTO(conteudo);
    }

    @Transactional
    public ConteudoResponseDTO salvar(ConteudoRequestDTO conteudoRequestDTO) {
        var trilha = trilhaService.buscarEntidadeTrilhaPorId(conteudoRequestDTO.getIdTrilha());
        var conteudo = ConteudoMapper.toEntity(conteudoRequestDTO, trilha);
        return ConteudoMapper.toResponseDTO(conteudoRepository.save(conteudo));
    }

    @Transactional
    public ConteudoResponseDTO atualizar(Long id, ConteudoRequestDTO conteudoRequestDTO) {
        var conteudoAtual = buscarEntidadeConteudoPorId(id);
        var trilha = trilhaService.buscarEntidadeTrilhaPorId(conteudoRequestDTO.getIdTrilha());

        conteudoAtual.setTitulo(conteudoRequestDTO.getTitulo());
        conteudoAtual.setDescricao(conteudoRequestDTO.getDescricao());
        conteudoAtual.setTipo(conteudoRequestDTO.getTipo());
        conteudoAtual.setLink(conteudoRequestDTO.getLink());
        conteudoAtual.setTrilha(trilha);

        return ConteudoMapper.toResponseDTO(conteudoRepository.save(conteudoAtual));
    }

    @Transactional
    public void deletar(Long id) {
        var conteudo = buscarEntidadeConteudoPorId(id);
        conteudoRepository.delete(conteudo);
    }

    private Conteudo buscarEntidadeConteudoPorId(Long id) {
        return conteudoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Conteúdo com id: " + id + " não encontrado"));
    }
}
