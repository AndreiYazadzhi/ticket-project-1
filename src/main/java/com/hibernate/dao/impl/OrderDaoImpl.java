package com.hibernate.dao.impl;

import com.hibernate.dao.OrderDao;
import com.hibernate.exception.DataProcessException;
import com.hibernate.model.Order;
import com.hibernate.model.User;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl implements OrderDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public OrderDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Order add(Order order) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
            return order;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessException("Can`t insert order entity " + order, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Order> getOrdersByUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT distinct FROM Order o"
                    + " left join fetch o.tickets t"
                    + " left join fetch t.movieSession m"
                    + " left join fetch m.movie"
                    + " left join fetch m.cinemaHall"
                    + " where o.user = :user", Order.class)
                    .setParameter("user", user)
                    .getResultList();
        } catch (Exception e) {
            throw new DataProcessException("Could not find orders by user: " + user, e);
        }
    }
}
