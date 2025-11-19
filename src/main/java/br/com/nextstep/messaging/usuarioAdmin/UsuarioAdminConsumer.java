package br.com.nextstep.messaging.usuarioAdmin;

import br.com.nextstep.model.UsuarioAdmin;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class UsuarioAdminConsumer {

    @RabbitListener(queues = "fila-usuarios-admin")
    public void processarAdmin(UsuarioAdmin usuarioAdmin) {
        System.out.println("Mensagem recebida da fila: " + usuarioAdmin.getNome());
    }
}
