package br.com.nextstep.messaging.solicitacao;

import br.com.nextstep.model.Solicitacao;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class SolicitacaoConsumer {

    @RabbitListener(queues = "fila-solicitacoes")
    public void processarSolicitacao(Solicitacao solicitacao) {
        System.out.println("Mensagem recebida da fila: " + solicitacao.getNome());
    }
}
