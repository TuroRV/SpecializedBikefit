package com.dam.specializedbikefit.DAOs;

import com.dam.specializedbikefit.Classes.Bicycle;
import com.dam.specializedbikefit.Classes.User;

public interface UserDAO {

    boolean validateCredentials(String email, String password);
    void addBicycle(Bicycle bicycle);
    void deleteBicycle(Bicycle bicycle);
    void updateProfile();
    User getUserByEmail(String email);


}
