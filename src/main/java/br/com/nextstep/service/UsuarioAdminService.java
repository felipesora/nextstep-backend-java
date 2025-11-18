package br.com.nextstep.service;

import br.com.nextstep.dto.usuarioAdmin.UsuarioAdminRequestDTO;
import br.com.nextstep.dto.usuarioAdmin.UsuarioAdminResponseDTO;
import br.com.nextstep.exception.RegraNegocioException;
import br.com.nextstep.mapper.UsuarioAdminMapper;
import br.com.nextstep.model.UsuarioAdmin;
import br.com.nextstep.repository.UsuarioAdminRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class UsuarioAdminService {

    @Autowired
    private UsuarioAdminRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public Page<UsuarioAdminResponseDTO> listarTodos(Pageable pageable) {
        return usuarioRepository.findAllByOrderByIdAsc(pageable)
                .map(UsuarioAdminMapper::toResponseDTO);
    }

    @Transactional(readOnly = true)
    public UsuarioAdminResponseDTO buscarPorId(Long id) {
        var usuario = buscarEntidadeUsuarioPorId(id);
        return UsuarioAdminMapper.toResponseDTO(usuario);
    }

    @Transactional
    public UsuarioAdminResponseDTO salvar(UsuarioAdminRequestDTO usuarioRequestDTO) {

        if (usuarioRepository.findUsuarioByEmail(usuarioRequestDTO.getEmail()).isPresent()) {
            throw new RegraNegocioException("Já existe um usuário cadastrado com este e-mail.");
        }

        var usuario = UsuarioAdminMapper.toEntity(usuarioRequestDTO);
        usuario.setSenha(passwordEncoder.encode(usuarioRequestDTO.getSenha()));

        var usuarioSalvo = usuarioRepository.save(usuario);
        return UsuarioAdminMapper.toResponseDTO(usuarioSalvo);
    }

    @Transactional
    public UsuarioAdminResponseDTO atualizar(Long id, UsuarioAdminRequestDTO usuarioRequestDTO) {
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
        return UsuarioAdminMapper.toResponseDTO(usuarioAtualizado);
    }

    @Transactional
    public void deletar(Long id) {
        var usuario = buscarEntidadeUsuarioPorId(id);
        usuarioRepository.delete(usuario);
    }

    private UsuarioAdmin buscarEntidadeUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário Administrado com id: " + id + " não encontrado"));
    }

    @Transactional
    public  UsuarioAdmin salvarAdminInicial() {
        String emailAdmin = "admin@email.com";

        if (usuarioRepository.findUsuarioByEmail(emailAdmin).isPresent()) {
            return usuarioRepository.findUsuarioByEmail(emailAdmin).get();
        }

        UsuarioAdmin admin = new UsuarioAdmin();
        admin.setNome("Administrador");
        admin.setEmail(emailAdmin);
        admin.setSenha(passwordEncoder.encode("admin123"));

        return usuarioRepository.save(admin);
    }
}
