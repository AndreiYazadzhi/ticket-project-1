package com.hibernate.dao.impl;

import com.hibernate.dao.MovieDao;
import com.hibernate.exception.DataProcessException;
import com.hibernate.lib.Dao;
import com.hibernate.model.Movie;
import com.hibernate.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class MovieDaoImpl implements MovieDao {

    @Override
    public Movie add(Movie movie) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(movie);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessException("Can`t insert Movie entity " + movie, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    @Override
    public List<Movie> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Movie", Movie.class).getResultList();
        } catch (Exception e) {
            throw new DataProcessException("Could not get all movies", e);
        }
    }
}
