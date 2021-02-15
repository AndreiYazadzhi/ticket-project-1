package com.hibernate.controller;

import com.hibernate.model.Order;
import com.hibernate.model.dto.mapping.DtoResponseMapper;
import com.hibernate.model.dto.request.OrderRequestDto;
import com.hibernate.model.dto.response.OrderResponseDto;
import com.hibernate.service.OrderService;
import com.hibernate.service.ShoppingCartService;
import com.hibernate.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;
    private final DtoResponseMapper<OrderResponseDto, Order> responseMapper;

    @Autowired
    public OrderController(OrderService orderService,
                           ShoppingCartService shoppingCartService,
                           UserService userService,
                           DtoResponseMapper<OrderResponseDto, Order> responseMapper) {
        this.orderService = orderService;
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.responseMapper = responseMapper;
    }

    @PutMapping("/complete")
    public void completeOrder(OrderRequestDto dto) {
        orderService.completeOrder(shoppingCartService
                .getByUser(userService.getByEmail(dto.getUserEmail())));
    }

    @GetMapping
    public List<OrderResponseDto> getOrderHistory(@RequestParam Long userId) {
        return orderService.getOrdersHistory(userService.get(userId)).stream()
                .map(responseMapper::toDto)
                .collect(Collectors.toList());
    }
}
