package com.dam.specializedbikefit.Controllers;

import com.dam.specializedbikefit.Navigation.AppView;
import com.dam.specializedbikefit.Navigation.ViewSwitcher;
import javafx.event.ActionEvent;

public class MainMenuController {

    public void goToSettings(ActionEvent actionEvent) {
    }

    public void goToBikefits(ActionEvent actionEvent) {
    }

    public void goToGarage(ActionEvent actionEvent) {
        ViewSwitcher.showView(AppView.GARAGE);
    }
}
