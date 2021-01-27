package com.hibernate.model;

import java.util.List;

public class ShoppingCard {
    private Long id;
    private List<Ticket> tickets;
    private User user;

    public ShoppingCard() {
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
