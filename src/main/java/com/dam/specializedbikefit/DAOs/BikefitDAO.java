package com.dam.specializedbikefit.DAOs;

import com.dam.specializedbikefit.Classes.Bicycle;
import com.dam.specializedbikefit.Classes.Bikefit;
import com.dam.specializedbikefit.Classes.User;

import java.util.List;

public interface BikefitDAO {

    Bikefit createNewBikefit(String name, User user, Bicycle bicycle);
    List<Bikefit> getBikeFitsByUser (User user);
    void deleteBikefit(Bikefit bikefit);
}
