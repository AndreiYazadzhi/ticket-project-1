package com.hibernate.model.dto.mapping.impl.response;

import com.hibernate.model.Order;
import com.hibernate.model.Ticket;
import com.hibernate.model.dto.mapping.DtoResponseMapper;
import com.hibernate.model.dto.response.OrderResponseDto;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class OrderResponseMapper implements
        DtoResponseMapper<OrderResponseDto, Order> {
    private static final String PATTERN = "dd.MM.yyyy HH:mm";

    @Override
    public OrderResponseDto toDto(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setOrderDate(order.getOrderDate()
                .format(DateTimeFormatter.ofPattern(PATTERN)));
        orderResponseDto.setId(order.getId());
        orderResponseDto.setTicketIds(order.getTickets().stream()
                .map(Ticket::getId).collect(Collectors.toList()));
        orderResponseDto.setUserEmail(order.getUser().getEmail());
        return orderResponseDto;
    }
}
