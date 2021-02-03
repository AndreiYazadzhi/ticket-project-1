package com.hibernate.service.impl;

import com.hibernate.dao.ShoppingCardDao;
import com.hibernate.dao.TicketDao;
import com.hibernate.lib.Inject;
import com.hibernate.lib.Service;
import com.hibernate.model.MovieSession;
import com.hibernate.model.ShoppingCard;
import com.hibernate.model.Ticket;
import com.hibernate.model.User;
import com.hibernate.service.ShoppingCardService;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingCardServiceImpl implements ShoppingCardService {
    @Inject
    private ShoppingCardDao shoppingCardDao;
    @Inject
    private TicketDao ticketDao;

    @Override
    public void addSession(MovieSession movieSession, User user) {
        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setMovieSession(movieSession);
        ShoppingCard shoppingCartByUser = shoppingCardDao.getByUser(user);
        List<Ticket> tickets = shoppingCartByUser.getTickets();
        ticketDao.add(ticket);
        shoppingCardDao.update(shoppingCartByUser);
    }

    @Override
    public ShoppingCard getByUser(User user) {
        return shoppingCardDao.getByUser(user);
    }

    @Override
    public void registerNewShoppingCart(User user) {
        ShoppingCard shoppingCard = new ShoppingCard();
        shoppingCard.setUser(user);
        shoppingCardDao.add(shoppingCard);
    }

    @Override
    public void clear(ShoppingCard shoppingCart) {
        shoppingCart.setTickets(new ArrayList<>());
        shoppingCardDao.update(shoppingCart);
    }
}
