package org.example.mafia.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfiguration {
    @Bean
    public Queue documentRequestQueue() {
        return new Queue("documentRequestQueue", false);
    }

    @Bean
    public Queue documentResponseQueue() {return new Queue("documentResponseQueue", false);}
}
