package com.mfdigital.apollo_users.core.http.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {
    @Bean
    public TopicExchange userExchange() {
        return new TopicExchange("user.exchange");
    }

    @Bean
    public Queue userQueue() {
        return new Queue("user.queue");
    }

    @Bean
    public Binding userBinding(@Qualifier("userQueue") Queue userQueue, TopicExchange userExchange) {
        return BindingBuilder.bind(userQueue).to(userExchange).with("user");
    }
}
