package com.dam.specializedbikefit.DAOs;

import com.dam.specializedbikefit.Classes.Bicycle;

public interface BicycleDAO {
    Bicycle addBicycle(Bicycle bicycle);
    void updateBicycle(Bicycle bicycle);
}
