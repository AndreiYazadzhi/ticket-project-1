package com.hibernate.service.impl;

import com.hibernate.dao.ShoppingCartDao;
import com.hibernate.dao.TicketDao;
import com.hibernate.lib.Inject;
import com.hibernate.lib.Service;
import com.hibernate.model.MovieSession;
import com.hibernate.model.ShoppingCart;
import com.hibernate.model.Ticket;
import com.hibernate.model.User;
import com.hibernate.service.ShoppingCartService;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Inject
    private ShoppingCartDao shoppingCartDao;
    @Inject
    private TicketDao ticketDao;

    @Override
    public void addSession(MovieSession movieSession, User user) {
        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setMovieSession(movieSession);
        ShoppingCart shoppingCartByUser = shoppingCartDao.getByUser(user);
        List<Ticket> tickets = shoppingCartByUser.getTickets();
        tickets.add(ticket);
        ticketDao.add(ticket);
        shoppingCartDao.update(shoppingCartByUser);
    }

    @Override
    public ShoppingCart getByUser(User user) {
        return shoppingCartDao.getByUser(user);
    }

    @Override
    public void registerNewShoppingCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCartDao.add(shoppingCart);
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        shoppingCart.setTickets(new ArrayList<>());
        shoppingCartDao.update(shoppingCart);
    }
}
