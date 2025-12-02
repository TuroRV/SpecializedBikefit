package com.dam.specializedbikefit.DAOs;

import com.dam.specializedbikefit.Classes.Bicycle;
import com.dam.specializedbikefit.Classes.User;
import com.dam.specializedbikefit.DBConnection.Connection;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean validateCredentials(String email, String password) {
        Connection.initializeConnection();
        Session session = Connection.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

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
    public void addBicycle(Bicycle bicycle) {

    }

    @Override
    public void deleteBicycle(Bicycle bicycle) {

    }

    @Override
    public void updateProfile() {

    }

    @Override
    public User getUserByEmail(String email) {
        Connection.initializeConnection();
        Session session = Connection.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

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
}
