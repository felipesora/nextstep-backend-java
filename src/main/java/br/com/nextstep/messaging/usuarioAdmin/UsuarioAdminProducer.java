package br.com.nextstep.messaging.usuarioAdmin;

import br.com.nextstep.dto.usuarioAdmin.UsuarioAdminResponseDTO;
import br.com.nextstep.mapper.UsuarioAdminMapper;
import br.com.nextstep.model.UsuarioAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class UsuarioAdminProducer
{
    private final RabbitTemplate rabbitTemplate;

    public UsuarioAdminProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarAdminCriado(UsuarioAdmin usuarioAdmin) {
        UsuarioAdminResponseDTO dto = UsuarioAdminMapper.toResponseDTO(usuarioAdmin);
        rabbitTemplate.convertAndSend("fila-usuarios-admin", dto);
        System.out.println("Mensagem enviada para fila: " + dto.getNome());
    }
}
