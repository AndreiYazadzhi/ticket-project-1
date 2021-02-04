package com.hibernate.dao;

import com.hibernate.model.Order;
import com.hibernate.model.User;
import java.util.List;

public interface OrderDao {
    Order add(Order order);

    List<Order> getOrdersByUser(User user);
}
