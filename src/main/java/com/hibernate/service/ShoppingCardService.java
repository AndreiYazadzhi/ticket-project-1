package com.hibernate.service;

import com.hibernate.model.MovieSession;
import com.hibernate.model.ShoppingCard;
import com.hibernate.model.User;

public interface ShoppingCardService {
    void addSession(MovieSession movieSession, User user);

    ShoppingCard getByUser(User user);

    void registerNewShoppingCart(User user);

    void clear(ShoppingCard shoppingCart);
}
