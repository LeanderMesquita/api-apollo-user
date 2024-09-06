package com.mfdigital.apollo_users.core.http.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
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
    public Queue userCreatedQueue() {
        return new Queue("user.created.queue");
    }

    @Bean
    public Queue userAuthoritiesQueue() {
        return new Queue("user.authorities.queue");
    }

    @Bean
    public Binding userCreatedBinding(@Qualifier("userCreatedQueue") Queue userCreatedQueue, TopicExchange userExchange) {
        return BindingBuilder.bind(userCreatedQueue).to(userExchange).with("user.created");
    }

    @Bean
    public Binding userAuthoritiesBinding(@Qualifier("userAuthoritiesQueue") Queue userAuthoritiesQueue, TopicExchange userExchange) {
        return BindingBuilder.bind(userAuthoritiesQueue).to(userExchange).with("user.authorities");
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }
}
