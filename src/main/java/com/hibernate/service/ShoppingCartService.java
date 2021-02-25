package com.hibernate.service;

import com.hibernate.model.PerformanceSession;
import com.hibernate.model.ShoppingCart;
import com.hibernate.model.User;

public interface ShoppingCartService {
    void addSession(PerformanceSession performanceSession, User user);

    ShoppingCart getByUser(User user);

    void registerNewShoppingCart(User user);

    void clear(ShoppingCart shoppingCart);
}
