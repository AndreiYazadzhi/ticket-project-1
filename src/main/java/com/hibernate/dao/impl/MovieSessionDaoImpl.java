package com.hibernate.dao.impl;

import com.hibernate.dao.MovieSessionDao;
import com.hibernate.exception.DataProcessException;
import com.hibernate.model.MovieSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MovieSessionDaoImpl implements MovieSessionDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public MovieSessionDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
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
    public Optional<MovieSession> get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(MovieSession.class, id));
        } catch (Exception e) {
            throw new DataProcessException("Could not get movie session by id " + id + ". ", e);
        }
    }

    @Override
    public void update(Long id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(session.get(MovieSession.class, id));
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new DataProcessException("Couldn't update session with id "
                    + id, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void remove(Long id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(session.get(MovieSession.class, id));
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new DataProcessException("Couldn't update session with id " + id, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<MovieSession> getAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM MovieSession", MovieSession.class).getResultList();
        } catch (Exception e) {
            throw new DataProcessException("Could not get all movie sessions", e);
        }
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = sessionFactory.openSession()) {
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
