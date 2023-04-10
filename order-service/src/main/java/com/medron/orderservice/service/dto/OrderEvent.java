package com.medron.orderservice.service.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderEvent {
    private String status;
    private String message;
    private OrderDto orderDto;

}
