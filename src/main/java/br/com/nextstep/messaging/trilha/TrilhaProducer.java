package br.com.nextstep.messaging.trilha;

import br.com.nextstep.dto.trilha.TrilhaResponseDTO;
import br.com.nextstep.mapper.TrilhaMapper;
import br.com.nextstep.model.Trilha;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class TrilhaProducer {

    private final RabbitTemplate rabbitTemplate;

    public TrilhaProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarTrilhaCriada(Trilha trilha) {
        TrilhaResponseDTO dto = TrilhaMapper.toResponseDTO(trilha);
        rabbitTemplate.convertAndSend("fila-trilhas", dto);
        System.out.println("Mensagem enviada para fila: " + dto.getNome());
    }
}
