package br.com.nextstep.service;

import br.com.nextstep.dto.solicitacao.SolicitacaoRequestDTO;
import br.com.nextstep.dto.solicitacao.SolicitacaoResponseDTO;
import br.com.nextstep.exception.RegraNegocioException;
import br.com.nextstep.mapper.SolicitacaoMapper;
import br.com.nextstep.messaging.solicitacao.SolicitacaoProducer;
import br.com.nextstep.model.Solicitacao;
import br.com.nextstep.repository.SolicitacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SolicitacaoService {

    @Autowired
    private SolicitacaoRepository solicitacaoRepository;

    @Autowired
    private SolicitacaoProducer solicitacaoProducer;

    @Transactional(readOnly = true)
    public Page<SolicitacaoResponseDTO> listarTodos(Pageable pageable) {
        return solicitacaoRepository.findAllByOrderByIdAsc(pageable)
                .map(SolicitacaoMapper::toResponseDTO);
    }

    @Transactional(readOnly = true)
    public SolicitacaoResponseDTO buscarPorId(Long id) {
        var solicitacao = buscarEntidadeSolicitacaoPorId(id);
        return SolicitacaoMapper.toResponseDTO(solicitacao);
    }

    @Transactional
    public SolicitacaoResponseDTO salvar(SolicitacaoRequestDTO solicitacaoRequestDTO) {

        if (solicitacaoRepository.findSolicitacaoByEmail(solicitacaoRequestDTO.getEmail()).isPresent()) {
            throw new RegraNegocioException("Já existe uma solicitação cadastrada com este e-mail.");
        }

        var solicitacao = SolicitacaoMapper.toEntity(solicitacaoRequestDTO);

        var solicitacaoSalva = solicitacaoRepository.save(solicitacao);
        solicitacaoProducer.enviarSolicitacaoCriada(solicitacaoSalva);
        return SolicitacaoMapper.toResponseDTO(solicitacaoSalva);
    }

    @Transactional
    public void deletar(Long id) {
        var solicitacao = buscarEntidadeSolicitacaoPorId(id);
        solicitacaoRepository.delete(solicitacao);
    }

    private Solicitacao buscarEntidadeSolicitacaoPorId(Long id) {
        return solicitacaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Solicitação com id: " + id + " não encontrada"));
    }
}
