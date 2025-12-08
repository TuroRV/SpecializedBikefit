package com.dam.specializedbikefit.Controllers;

import com.dam.specializedbikefit.Navigation.AppView;
import com.dam.specializedbikefit.Navigation.ViewSwitcher;
import javafx.event.ActionEvent;

public class InfoController {
    public void goBack(ActionEvent actionEvent) {
        ViewSwitcher.showView(AppView.MENU);
    }
}
