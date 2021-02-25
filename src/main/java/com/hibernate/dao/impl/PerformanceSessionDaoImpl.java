package com.hibernate.dao.impl;

import com.hibernate.dao.PerformanceSessionDao;
import com.hibernate.exception.DataProcessException;
import com.hibernate.model.PerformanceSession;
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
public class PerformanceSessionDaoImpl implements PerformanceSessionDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public PerformanceSessionDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public PerformanceSession add(PerformanceSession performanceSession) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(performanceSession);
            transaction.commit();
            return performanceSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessException("Can`t insert Movie Session  " + performanceSession, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<PerformanceSession> get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(PerformanceSession.class, id));
        } catch (Exception e) {
            throw new DataProcessException("Could not get movie session by id " + id + ". ", e);
        }
    }

    @Override
    public void update(PerformanceSession performanceSession) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(performanceSession);
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new DataProcessException("Couldn't update the " + performanceSession, e);
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
            session.delete(session.get(PerformanceSession.class, id));
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
    public List<PerformanceSession> getAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM MovieSession", PerformanceSession.class).getResultList();
        } catch (Exception e) {
            throw new DataProcessException("Could not get all movie sessions", e);
        }
    }

    @Override
    public List<PerformanceSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select ms from MovieSession ms "
                    + "left join fetch ms.cinemaHall "
                    + "left join fetch ms.movie "
                    + "where ms.movie.id = :id_movie "
                    + "and DATE_FORMAT(ms.showTime, '%Y-%m-%d') = :date", PerformanceSession.class)
                    .setParameter("id_movie", movieId)
                    .setParameter("date", DateTimeFormatter.ISO_LOCAL_DATE.format(date))
                    .getResultList();
        } catch (Exception e) {
            throw new DataProcessException("Could not find available sessions for movie "
                    + movieId + " and date " + date, e);
        }
    }
}
