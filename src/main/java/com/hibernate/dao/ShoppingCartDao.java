package com.hibernate.dao;

import com.hibernate.model.ShoppingCart;
import com.hibernate.model.User;

public interface ShoppingCartDao {
    ShoppingCart add(ShoppingCart shoppingCart);

    ShoppingCart getByUser(User user);

    void update(ShoppingCart shoppingCart);
}
