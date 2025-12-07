package com.dam.specializedbikefit.Controllers;

import com.dam.specializedbikefit.Navigation.Alerts;
import com.dam.specializedbikefit.Navigation.AppView;
import com.dam.specializedbikefit.Navigation.Navigator;
import com.dam.specializedbikefit.Navigation.ViewSwitcher;
import com.dam.specializedbikefit.Singleton.UserSession;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.util.Optional;

public class HomeController {

    public Label userInfoLabel;
    public BorderPane contentPane;
    public Button profileButton;

    public void initialize() {

        ViewSwitcher.setMainContentPane(contentPane);
        ViewSwitcher.showView(AppView.MENU);

        userInfoLabel.setText(UserSession.getUser().getUser_email());

    }

    public void goToProfile(){
        ViewSwitcher.showView(AppView.USERPROFILE);
    }


    public void closeSession(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cerrar Sesión");
        alert.setHeaderText(null);
        alert.setContentText("¿Estás seguro de que quieres cerrar la sesión?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            UserSession.setUser(null);
            Navigator.changeStage(profileButton, "login-view.fxml", "Login");
        }
    }
}
