package com.hibernate.dao.impl;

import com.hibernate.dao.ShoppingCardDao;
import com.hibernate.exception.DataProcessException;
import com.hibernate.lib.Dao;
import com.hibernate.model.ShoppingCard;
import com.hibernate.model.User;
import com.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class ShoppingCardDaoImpl implements ShoppingCardDao {
    @Override
    public ShoppingCard add(ShoppingCard shoppingCard) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(shoppingCard);
            transaction.commit();
            return shoppingCard;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessException("Can`t insert shopping Card  " + shoppingCard, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public ShoppingCard getByUser(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from ShoppingCard sc "
                    + "left join fetch sc.tickets "
                    + "where sc.user = :user", ShoppingCard.class)
                    .setParameter("user", user)
                    .getSingleResult();
        } catch (Exception e) {
            throw new DataProcessException("Could not find card by user: " + user, e);
        }
    }

    @Override
    public void update(ShoppingCard shoppingCard) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(shoppingCard);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessException("Can`t insert shopping Card  " + shoppingCard, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
