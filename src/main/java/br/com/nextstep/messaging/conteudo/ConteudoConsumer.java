package br.com.nextstep.messaging.conteudo;

import br.com.nextstep.model.Conteudo;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ConteudoConsumer {

    @RabbitListener(queues = "fila-conteudos")
    public void processarConteudo(Conteudo conteudo) {
        System.out.println("Mensagem recebida da fila: " + conteudo.getTitulo());
    }
}
