package com.airrentals.account_service;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Exchange exchange() {
        return ExchangeBuilder.fanoutExchange("MemberEvents").build();
    }

    @Bean
    public Queue queue() {
        return QueueBuilder.durable("MemberEvents").build();
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with("*").noargs();
    }

    @Autowired
    public void configure(AmqpAdmin amqpAdmin) {
        amqpAdmin.declareExchange(exchange());
        amqpAdmin.declareQueue(queue());
        amqpAdmin.declareBinding(binding());
    }
}
