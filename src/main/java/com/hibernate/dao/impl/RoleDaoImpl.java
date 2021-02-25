package com.hibernate.dao.impl;

import com.hibernate.dao.RoleDao;
import com.hibernate.exception.DataProcessException;
import com.hibernate.model.Role;
import com.hibernate.model.RoleName;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements RoleDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public RoleDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Role getRoleByName(String roleName) {
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createQuery("FROM Role WHERE roleName = :roleName", Role.class)
                    .setParameter("roleName", RoleName.valueOf(roleName))
                    .getSingleResult();
        } catch (Exception e) {
            throw new DataProcessException("Can't get Role:" + roleName, e);
        }
    }

    @Override
    public void add(Role role) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(role);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessException("Can't add new role " + role.getRoleName(), e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
