package com.dam.specializedbikefit.Singleton;

import com.dam.specializedbikefit.Classes.User;

public class UserSession {
    private static User user;

    public static User getUser() {
        return user;
    }

    public static void setUser(User u) {
        user = u;
    }
}
