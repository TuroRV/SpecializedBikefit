package com.dam.specializedbikefit.Navigation;

import javafx.scene.control.Alert;
import javafx.stage.Modality;

public class Alerts {
    public static void showStandardAlert(Alert.AlertType type, String title, String header, String content){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.showAndWait();
    }
}
