package nl.maxvandewiel.service.userregistration.configuration;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by max on 6/8/16.
 */
@Configuration
//@Profile("rabbitmq")
public class RabbitMQConfiguration {
    public final static String topicExchangeName = "user-registration";

    @Bean(name = "topicExchange")
    TopicExchange topicExchange() {
        return new TopicExchange(topicExchangeName);
    }

    @Bean(name = "rabbitTemplate")
    AmqpTemplate rabbitTemplate() {
        AmqpTemplate template = new RabbitTemplate();
        return template;
    }
}