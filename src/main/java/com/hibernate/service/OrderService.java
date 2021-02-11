package com.hibernate.service;

import com.hibernate.model.Order;
import com.hibernate.model.ShoppingCart;
import com.hibernate.model.User;
import java.util.List;

public interface OrderService {
    Order completeOrder(ShoppingCart shoppingCart);

    List<Order> getOrdersHistory(User user);
}
