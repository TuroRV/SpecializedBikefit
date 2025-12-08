package com.dam.specializedbikefit.DAOs;

import com.dam.specializedbikefit.Classes.Bicycle;
import com.dam.specializedbikefit.Classes.User;

import java.util.List;
import java.util.Set;

public interface UserDAO {

    boolean validateCredentials(String email, String password);
    void addBicycleToUser(Bicycle bicycle,User user);
    void deleteBicycleFromUser(User user, Bicycle bicycle);
    void updateProfile(User user);
    User getUserByEmail(String email);
    Set<Bicycle> getUserBicycles(User user);
    boolean createUser(User user);


}
