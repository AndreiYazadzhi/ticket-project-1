package com.hibernate.service;

import com.hibernate.model.MovieSession;
import com.hibernate.model.ShoppingCart;
import com.hibernate.model.User;

public interface ShoppingCartService {
    void addSession(MovieSession movieSession, User user);

    ShoppingCart getByUser(User user);

    void registerNewShoppingCart(User user);

    void clear(ShoppingCart shoppingCart);
}
