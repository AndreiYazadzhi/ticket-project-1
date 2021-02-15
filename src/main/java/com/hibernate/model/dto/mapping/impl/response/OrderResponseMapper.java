package com.hibernate.model.dto.mapping.impl.response;

import com.hibernate.model.Order;
import com.hibernate.model.Ticket;
import com.hibernate.model.dto.mapping.DtoResponseMapper;
import com.hibernate.model.dto.response.OrderResponseDto;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class OrderResponseMapper implements
        DtoResponseMapper<OrderResponseDto, Order> {
    @Override
    public OrderResponseDto toDto(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setOrderDate(order.getOrderDate()
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
        orderResponseDto.setId(order.getId());
        orderResponseDto.setTicketsId(order.getTickets().stream()
                .map(Ticket::getId).collect(Collectors.toList()));
        orderResponseDto.setUserEmail(order.getUser().getEmail());
        return orderResponseDto;
    }
}
