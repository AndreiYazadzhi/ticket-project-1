package com.hibernate.dao.impl;

import com.hibernate.dao.MovieSessionDao;
import com.hibernate.exception.DataProcessException;
import com.hibernate.lib.Dao;
import com.hibernate.model.MovieSession;
import com.hibernate.util.HibernateUtil;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class MovieSessionDaoImpl implements MovieSessionDao {

    @Override
    public MovieSession add(MovieSession movieSession) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(movieSession);
            transaction.commit();
            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessException("Can`t insert Movie Session  " + movieSession, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<MovieSession> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM MovieSession", MovieSession.class).getResultList();
        } catch (Exception e) {
            throw new DataProcessException("Could not get all movie sessions", e);
        }
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("select ms from MovieSession ms "
                    + "left join fetch ms.cinemaHall "
                    + "left join fetch ms.movie "
                    + "where ms.movie.id = :id_movie "
                    + "and DATE_FORMAT(ms.showTime, '%Y-%m-%d') = :date", MovieSession.class)
                    .setParameter("id_movie", movieId)
                    .setParameter("date", DateTimeFormatter.ISO_LOCAL_DATE.format(date))
                    .getResultList();
        } catch (Exception e) {
            throw new DataProcessException("Could not find available sessions for movie "
                    + movieId + " and date " + date, e);
        }
    }
}
