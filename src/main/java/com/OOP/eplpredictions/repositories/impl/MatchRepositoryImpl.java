package com.OOP.eplpredictions.repositories.impl;

import com.OOP.eplpredictions.entities.MatchEntity;
import com.OOP.eplpredictions.repositories.MatchRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MatchRepositoryImpl implements MatchRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public MatchRepositoryImpl() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    @Override
    public MatchEntity save(MatchEntity match) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(match);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return match;
    }

    @Override
    public MatchEntity update(MatchEntity match) {
        Session session = getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(match);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return match;
    }

    @Override
    public MatchEntity delete(int id) {
        MatchEntity match = findById(id);
        if (match != null) {
            Session session = getSession();
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.delete(match);
                tx.commit();
            } catch (HibernateException e) {
                if (tx != null) tx.rollback();
                e.printStackTrace();
            } finally {
                session.close();
            }
        }
        return match;
    }

    @Override
    public MatchEntity findById(int matchId) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        MatchEntity result = new MatchEntity();
        try {
            tx = session.beginTransaction();
            result = session.get(MatchEntity.class, matchId);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }

    @Override
    public List<MatchEntity> findAll() {
        return getSession().createQuery("FROM MatchEntity", MatchEntity.class).getResultList();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }

}
