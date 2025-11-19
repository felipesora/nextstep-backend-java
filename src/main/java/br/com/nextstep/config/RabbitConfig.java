package br.com.nextstep.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String FILA_TRILHAS = "fila-trilhas";
    public static final String FILA_CONTEUDOS = "fila-conteudos";
    public static final String FILA_USUARIOS_ADMIN = "fila-usuarios-admin";
    public static final String FILA_SOLICITACOES = "fila-solicitacoes";

    @Bean
    public Queue trilhasQueue() {
        return new Queue(FILA_TRILHAS, false);
    }

    @Bean
    public Queue conteudosQueue() {
        return new Queue(FILA_CONTEUDOS, false);
    }

    @Bean
    public Queue usuariosQueue() {
        return new Queue(FILA_USUARIOS_ADMIN, false);
    }

    @Bean
    public Queue solicitacoesQueue() {
        return new Queue(FILA_SOLICITACOES, false);
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter converter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(converter);
        return template;
    }
}
