package com.dam.specializedbikefit.DAOs;

import com.dam.specializedbikefit.Classes.Bicycle;
import com.dam.specializedbikefit.Classes.User;
import com.dam.specializedbikefit.DBConnection.Connection;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean validateCredentials(String email, String password) {
        Connection.initializeConnection();
        Session session = Connection.getSessionFactory().openSession();

        String hql = "FROM " + User.class.getName() + " u " +
                "WHERE u.user_email = :email AND u.user_password = :password";

        Query<User> query = session.createQuery(hql,User.class);
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
    public void deleteBicycle(Bicycle bicycle) {

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
        }
        finally {
            session.close();
        }
    }

    @Override
    public User getUserByEmail(String email) {
        Connection.initializeConnection();
        Session session = Connection.getSessionFactory().openSession();

        String hql = "FROM " + User.class.getName() + " u " +
                "WHERE u.user_email = :email";

        Query<User> query = session.createQuery(hql,User.class);
        query.setParameter("email", email);

        User user = query.uniqueResult();
        session.close();

        if (user != null) {
            return user;
        }
        return null;
    }

    @Override
    public List<Bicycle> getUserBikes(User user) {
        return List.of();
    }
}
