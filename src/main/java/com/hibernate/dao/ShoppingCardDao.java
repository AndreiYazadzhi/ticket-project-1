package com.hibernate.dao;

import com.hibernate.model.ShoppingCard;
import com.hibernate.model.User;

public interface ShoppingCardDao {
    ShoppingCard add(ShoppingCard shoppingCard);

    ShoppingCard getByUser(User user);

    void update(ShoppingCard shoppingCard);
}
