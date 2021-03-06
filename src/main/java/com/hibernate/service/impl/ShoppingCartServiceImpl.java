package com.hibernate.service.impl;

import com.hibernate.dao.ShoppingCartDao;
import com.hibernate.dao.TicketDao;
import com.hibernate.model.PerformanceSession;
import com.hibernate.model.ShoppingCart;
import com.hibernate.model.Ticket;
import com.hibernate.model.User;
import com.hibernate.service.ShoppingCartService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartDao shoppingCartDao;
    private final TicketDao ticketDao;

    @Autowired
    public ShoppingCartServiceImpl(ShoppingCartDao shoppingCartDao,
                                   TicketDao ticketDao) {
        this.shoppingCartDao = shoppingCartDao;
        this.ticketDao = ticketDao;
    }

    @Override
    public void addSession(PerformanceSession performanceSession, User user) {
        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setMovieSession(performanceSession);
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
