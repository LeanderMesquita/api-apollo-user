package com.mfdigital.apollo_users.core.http.config;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfdigital.apollo_users.core.entity.User;

@Service
public class UserEventPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private TopicExchange userExchange;

    public void publishUserCreated(User user) {
        rabbitTemplate.convertAndSend(userExchange.getName(), "user", user);
    }
}
