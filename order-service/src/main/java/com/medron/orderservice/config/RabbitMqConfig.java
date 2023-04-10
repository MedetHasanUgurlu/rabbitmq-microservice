package com.medron.orderservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    @Value("${rabbitmq.queue.order.name}")
    private String orderQueue;
    @Value("${rabbitmq.exchange.name}")
    private String orderExchange;
    @Value("${rabbitmq.binding.routing.key}")
    private String orderRoutingKey;
    @Bean
    public Queue orderQueue(){
        return new Queue(orderQueue);
    }
    @Bean
    public TopicExchange orderExchange(){
        return new TopicExchange(orderExchange);
    }
    @Bean
    public Binding binding(){
        return BindingBuilder
                .bind(orderQueue())
                .to(orderExchange())
                .with(orderRoutingKey);

    }
    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public AmqpTemplate amqTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }



}


