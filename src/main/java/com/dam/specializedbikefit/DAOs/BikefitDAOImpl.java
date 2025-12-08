package com.dam.specializedbikefit.DAOs;

import com.dam.specializedbikefit.Classes.Bicycle;
import com.dam.specializedbikefit.Classes.Bikefit;
import com.dam.specializedbikefit.Classes.User;
import com.dam.specializedbikefit.DBConnection.Connection;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class BikefitDAOImpl implements  BikefitDAO {
    @Override
    public Bikefit createNewBikefit(String name, User user, Bicycle bicycle) {

        Connection.initializeConnection();
        Session session = Connection.getSessionFactory().openSession();
        Transaction transaction = null;
        Bikefit newBikefit = null;
        try{
            transaction = session.beginTransaction();
            newBikefit = new Bikefit(name, user, bicycle);

            float saddleHeight = user.getUser_inseamlength() * 0.883f;
            newBikefit.setBikefit_saddleHeight(saddleHeight);

            float saddleToBar = (user.getUser_torsolength() + user.getUser_armlength()) * 0.48f;
            newBikefit.setBikefit_saddleToBar(saddleToBar);

            float saddleSetBack = user.getUser_inseamlength() / 10.0f;
            newBikefit.setBikefit_saddleSetBack(saddleSetBack);

            session.persist(newBikefit);
            transaction.commit();
        }
        catch (Exception e){
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }

        return newBikefit;
    }

    @Override
    public List<Bikefit> getBikeFitsByUser(User user) {
        return List.of();
    }

    @Override
    public void deleteBikefit(Bikefit bikefit) {

    }
}
