package com.dam.specializedbikefit.Controllers;

import com.dam.specializedbikefit.Navigation.Navigator;
import com.dam.specializedbikefit.Singleton.UserSession;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class HomeController {

    public Label userInfoLabel;
    public BorderPane contentPane;

    public void initialize() {
        userInfoLabel.setText(UserSession.getUser().getUser_email());
    }


}
