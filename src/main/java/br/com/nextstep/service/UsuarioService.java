package br.com.nextstep.service;

import br.com.nextstep.dto.usuario.UsuarioRequestDTO;
import br.com.nextstep.dto.usuario.UsuarioResponseDTO;
import br.com.nextstep.exception.RegraNegocioException;
import br.com.nextstep.mapper.UsuarioMapper;
import br.com.nextstep.model.Usuario;
import br.com.nextstep.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public Page<UsuarioResponseDTO> listarTodos(Pageable pageable) {
        return usuarioRepository.findAllByOrderByIdAsc(pageable)
                .map(UsuarioMapper::toResponseDTO);
    }

    @Transactional(readOnly = true)
    public UsuarioResponseDTO buscarPorId(Long id) {
        var usuario = buscarEntidadeUsuarioPorId(id);
        return UsuarioMapper.toResponseDTO(usuario);
    }

    @Transactional
    public UsuarioResponseDTO salvar(UsuarioRequestDTO usuarioRequestDTO) {

        if (usuarioRepository.findUsuarioByEmail(usuarioRequestDTO.getEmail()).isPresent()) {
            throw new RegraNegocioException("Já existe um usuário cadastrado com este e-mail.");
        }

        var usuario = UsuarioMapper.toEntity(usuarioRequestDTO);
        usuario.setSenha(passwordEncoder.encode(usuarioRequestDTO.getSenha()));

        var usuarioSalvo = usuarioRepository.save(usuario);
        return UsuarioMapper.toResponseDTO(usuarioSalvo);
    }

    @Transactional
    public UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO usuarioRequestDTO) {
        var usuarioAtual = buscarEntidadeUsuarioPorId(id);

        var usuarioComMesmoEmail = usuarioRepository.findUsuarioByEmail(usuarioRequestDTO.getEmail());
        if (usuarioComMesmoEmail.isPresent() && !usuarioComMesmoEmail.get().getId().equals(id)) {
            throw new RegraNegocioException("Já existe um usuário cadastrado com este e-mail.");
        }

        usuarioAtual.setNome(usuarioRequestDTO.getNome());
        usuarioAtual.setEmail(usuarioRequestDTO.getEmail());
        if (usuarioRequestDTO.getSenha() != null && !usuarioRequestDTO.getSenha().isBlank()) {
            usuarioAtual.setSenha(passwordEncoder.encode(usuarioRequestDTO.getSenha()));
        }

        var usuarioAtualizado = usuarioRepository.save(usuarioAtual);
        return UsuarioMapper.toResponseDTO(usuarioAtualizado);
    }

    @Transactional
    public void deletar(Long id) {
        var usuario = buscarEntidadeUsuarioPorId(id);
        usuarioRepository.delete(usuario);
    }

    private Usuario buscarEntidadeUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário com id: " + id + " não encontrado"));
    }
}
