package br.com.nextstep.messaging.solicitacao;

import br.com.nextstep.dto.solicitacao.SolicitacaoResponseDTO;
import br.com.nextstep.mapper.SolicitacaoMapper;
import br.com.nextstep.model.Solicitacao;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class SolicitacaoProducer {

    private final RabbitTemplate rabbitTemplate;

    public SolicitacaoProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarSolicitacaoCriada(Solicitacao solicitacao) {
        SolicitacaoResponseDTO dto = SolicitacaoMapper.toResponseDTO(solicitacao);
        rabbitTemplate.convertAndSend("fila-solicitacoes", dto);
        System.out.println("Mensagem enviada para fila: " + dto.getNome());
    }
}
