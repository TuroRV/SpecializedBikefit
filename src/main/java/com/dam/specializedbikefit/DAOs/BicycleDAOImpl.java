package com.dam.specializedbikefit.DAOs;

import com.dam.specializedbikefit.Classes.Bicycle;
import com.dam.specializedbikefit.DBConnection.Connection;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class BicycleDAOImpl implements BicycleDAO {

    @Override
    public Bicycle addBicycle(Bicycle bicycle) {
        Connection.initializeConnection();
        Session session = Connection.getSessionFactory().openSession();
        Transaction transaction = null;
        Bicycle resultBicycle = null;
        try {
            transaction = session.beginTransaction();
            String hql = "FROM Bicycle b WHERE " +
                    "b.bike_brand = :brand " +
                    "AND b.bike_model = :model " +
                    "AND b.bike_size = :size " +
                    "AND b.bike_year = :year " +
                    "AND b.bike_reach = :reach " +
                    "AND b.bike_stack = :stack " +
                    "AND b.bike_seattubelength = :seatTube " +
                    "AND b.bike_toptubelength = :topTube " +
                    "AND b.bike_isEbike = :isEbike";

            Query<Bicycle> query = session.createQuery(hql, Bicycle.class);

            query.setParameter("brand", bicycle.getBike_brand());
            query.setParameter("model", bicycle.getBike_model());
            query.setParameter("size", bicycle.getBike_size());
            query.setParameter("year", bicycle.getBike_year());
            query.setParameter("reach", bicycle.getBike_reach());
            query.setParameter("stack", bicycle.getBike_stack());
            query.setParameter("seatTube", bicycle.getBike_seattubelength());
            query.setParameter("topTube", bicycle.getBike_toptubelength());
            query.setParameter("isEbike", bicycle.isBike_isEbike());

            Bicycle existing = query.uniqueResult();

            if (existing!=null){
                resultBicycle = existing;
            } else {
                session.persist(bicycle);
                resultBicycle = bicycle;
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return resultBicycle;
    }
}
