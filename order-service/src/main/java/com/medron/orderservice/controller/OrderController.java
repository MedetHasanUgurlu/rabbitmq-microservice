package com.medron.orderservice.controller;

import com.medron.orderservice.publisher.OrderProducer;
import com.medron.orderservice.service.dto.OrderDto;
import com.medron.orderservice.service.dto.OrderEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderProducer producer;
    @GetMapping
    public String takeOrders(@RequestBody OrderDto orderDto){
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setMessage("Order is pending.");
        orderEvent.setStatus("PENDING");
        orderEvent.setOrderDto(orderDto);
        producer.sendMessage(orderEvent);
        return "Order sent to the RabbitMQ.";

    }
}
