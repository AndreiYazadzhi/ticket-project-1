package com.hibernate.dao.impl;

import com.hibernate.dao.TicketDao;
import com.hibernate.exception.DataProcessException;
import com.hibernate.lib.Dao;
import com.hibernate.model.Ticket;
import com.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class TicketDaoImpl implements TicketDao {
    @Override
    public Ticket add(Ticket ticket) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(ticket);
            transaction.commit();
            return ticket;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessException("Can`t insert ticket  " + ticket, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
