package com.dam.specializedbikefit.DAOs;

import com.dam.specializedbikefit.Classes.Bicycle;
import com.dam.specializedbikefit.Classes.User;
import com.dam.specializedbikefit.DBConnection.Connection;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Set;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean validateCredentials(String email, String password) {
        Connection.initializeConnection();
        Session session = Connection.getSessionFactory().openSession();

        String hql = "FROM " + User.class.getName() + " u " +
                "WHERE u.user_email = :email AND u.user_password = :password";

        Query<User> query = session.createQuery(hql, User.class);
        query.setParameter("email", email);
        query.setParameter("password", password);

        User user = query.uniqueResult();
        session.close();

        if (user != null) {
            return true;
        }
        return false;
    }

    @Override
    public void addBicycleToUser(Bicycle bicycle, User user) {
        Connection.initializeConnection();
        Session session = Connection.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            User userDB = session.merge(user);
            Bicycle bicycleDB = session.merge(bicycle);
            userDB.getBicycles().add(bicycleDB);
            bicycleDB.getUsers().add(userDB);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteBicycleFromUser(User user, Bicycle bicycle) {
        Connection.initializeConnection();
        Session session = Connection.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            User userDB = session.merge(user);
            Bicycle bicycleDB = session.merge(bicycle);
            userDB.getBicycles().remove(bicycleDB);
            bicycleDB.getUsers().remove(userDB);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    @Override
    public void updateProfile(User user) {

        Connection.initializeConnection();
        Session session = Connection.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.merge(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public User getUserByEmail(String email) {
        Connection.initializeConnection();
        Session session = Connection.getSessionFactory().openSession();

        String hql = "FROM " + User.class.getName() + " u " +
                "WHERE u.user_email = :email";

        Query<User> query = session.createQuery(hql, User.class);
        query.setParameter("email", email);

        User user = query.uniqueResult();
        session.close();

        if (user != null) {
            return user;
        }
        return null;
    }

    @Override
    public Set<Bicycle> getUserBicycles(User user) {
        Connection.initializeConnection();
        Session session = Connection.getSessionFactory().openSession();
        Set<Bicycle> userbikes = null;

        try {
            User userConectado = session.merge(user);

            userbikes = userConectado.getBicycles();

            //Sin este código Hibernate no carga la lista, dará error Lazy al querer acceder a la lista
            Hibernate.initialize(userbikes);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return userbikes;
    }

    @Override
    public boolean createUser(User user) {
        Connection.initializeConnection();
        Session session = Connection.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            String hql = "FROM User WHERE user_email = :email";
            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("email", user.getUser_email());

            if (query.uniqueResult() != null) {
                System.out.println("Ya existe el usuario");
                return false;
            }

            session.persist(user);
            transaction.commit();
            return true;

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }
}
