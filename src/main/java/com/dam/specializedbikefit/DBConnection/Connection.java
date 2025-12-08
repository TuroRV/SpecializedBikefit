package com.dam.specializedbikefit.DBConnection;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class Connection {

    private static SessionFactory sessionFactory;

    public static void initializeConnection(){
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

        configuration.addAnnotatedClass(com.dam.specializedbikefit.Classes.User.class);
        configuration.addAnnotatedClass(com.dam.specializedbikefit.Classes.Bicycle.class);
        configuration.addAnnotatedClass(com.dam.specializedbikefit.Classes.Bikefit.class);

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(builder.build());
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void setSessionFactory(SessionFactory sessionFactory) {
        Connection.sessionFactory = sessionFactory;
    }

    public static void shutdown(){
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
