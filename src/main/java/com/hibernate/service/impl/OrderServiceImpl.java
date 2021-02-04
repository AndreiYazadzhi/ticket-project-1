package com.hibernate.service.impl;

import com.hibernate.dao.OrderDao;
import com.hibernate.dao.ShoppingCartDao;
import com.hibernate.lib.Inject;
import com.hibernate.lib.Service;
import com.hibernate.model.Order;
import com.hibernate.model.ShoppingCart;
import com.hibernate.model.User;
import com.hibernate.service.OrderService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private OrderDao orderDao;
    @Inject
    private ShoppingCartDao shoppingCartDao;

    @Override
    public Order completeOrder(ShoppingCart shoppingCart) {
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setTickets(shoppingCart.getTickets());
        order.setUser(shoppingCart.getUser());
        orderDao.add(order);
        shoppingCart.setTickets(new ArrayList<>());
        shoppingCartDao.update(shoppingCart);
        return order;
    }

    @Override
    public List<Order> getOrdersHistory(User user) {
        return orderDao.getOrdersByUser(user);
    }
}
