package br.com.nextstep.messaging.trilha;

import br.com.nextstep.model.Trilha;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class TrilhaConsumer {

    @RabbitListener(queues = "fila-trilhas")
    public void processarTrilha(Trilha trilha) {
        System.out.println("Mensagem recebida da fila: " + trilha.getNome());
        // Aqui você pode, por exemplo:
        // - Enviar um e-mail
        // - Atualizar outro sistema
        // - Gerar relatório
    }
}
