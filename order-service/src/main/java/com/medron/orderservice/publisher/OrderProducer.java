package com.medron.orderservice.publisher;

import com.medron.orderservice.service.dto.OrderEvent;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderProducer.class);
    @Value("${rabbitmq.queue.order.name}")
    private String orderQueue;
    @Value("${rabbitmq.binding.routing.key}")
    private String orderRoutingKey;
    @Value("${rabbitmq.exchange.name}")
    private String orderExchange;

    private final RabbitTemplate rabbitTemplate;
    public void sendMessage(OrderEvent orderEvent){
        LOGGER.info(String.format("Order event send to RabbitMQ => %s",orderEvent.toString()));
        rabbitTemplate.convertAndSend(orderExchange,orderRoutingKey,orderEvent);

    }
}
