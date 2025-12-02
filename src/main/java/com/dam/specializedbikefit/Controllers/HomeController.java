package com.dam.specializedbikefit.Controllers;

import com.dam.specializedbikefit.Singleton.UserSession;
import javafx.scene.control.Label;

public class HomeController {

    public Label userInfoLabel;

    public void initialize() {
        userInfoLabel.setText(UserSession.getUser().getUser_email());
    }
}
