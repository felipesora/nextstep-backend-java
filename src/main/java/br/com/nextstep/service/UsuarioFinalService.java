package br.com.nextstep.service;

import br.com.nextstep.dto.usuarioFinal.UsuarioFinalResponseDTO;
import br.com.nextstep.mapper.UsuarioFinalMapper;
import br.com.nextstep.repository.UsuarioFinalRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioFinalService {

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
}
