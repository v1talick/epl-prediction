package com.OOP.eplpredictions.connections;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class SessionFactoryHolder {

    private static SessionFactory sessionFactory;

    @Autowired
    public static void setSessionFactory(SessionFactory sf) {
        sessionFactory = sf;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session provideSession() {
        return sessionFactory.openSession();
    }

}
