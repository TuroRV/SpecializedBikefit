package com.dam.specializedbikefit.Controllers;

import com.dam.specializedbikefit.Navigation.AppView;
import com.dam.specializedbikefit.Navigation.ViewSwitcher;
import javafx.event.ActionEvent;

public class MainMenuController {

    public void goToSettings(ActionEvent actionEvent) {
        ViewSwitcher.showView(AppView.USERPROFILE);
    }

    public void goToBikefits(ActionEvent actionEvent) {
        ViewSwitcher.showView(AppView.BIKEFIT);
    }

    public void goToGarage(ActionEvent actionEvent) {
        ViewSwitcher.showView(AppView.GARAGE);
    }
}
