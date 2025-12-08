package com.dam.specializedbikefit.DAOs;

import com.dam.specializedbikefit.Classes.Bicycle;
import com.dam.specializedbikefit.Classes.User;

import java.util.List;

public interface UserDAO {

    boolean validateCredentials(String email, String password);
    void addBicycleToUser(Bicycle bicycle,User user);
    void deleteBicycle(Bicycle bicycle);
    void updateProfile(User user);
    User getUserByEmail(String email);
    List<Bicycle> getUserBikes(User user);


}
