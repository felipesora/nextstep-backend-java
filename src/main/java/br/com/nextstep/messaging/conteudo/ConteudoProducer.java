package br.com.nextstep.messaging.conteudo;

import br.com.nextstep.dto.conteudo.ConteudoResponseDTO;
import br.com.nextstep.mapper.ConteudoMapper;
import br.com.nextstep.model.Conteudo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ConteudoProducer {

    private final RabbitTemplate rabbitTemplate;

    public ConteudoProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarConteudoCriada(Conteudo conteudo) {
        ConteudoResponseDTO dto = ConteudoMapper.toResponseDTO(conteudo);
        rabbitTemplate.convertAndSend("fila-conteudos", dto);
        System.out.println("Mensagem enviada para fila: " + dto.getTitulo());
    }
}
