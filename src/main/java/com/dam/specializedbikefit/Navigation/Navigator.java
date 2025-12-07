package com.dam.specializedbikefit.Navigation;

import com.dam.specializedbikefit.AppStart;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Navigator {

    public static void changeStage(Node anyNodeInScene, String fxmlPath, String title) {
        try {
            Stage stage = (Stage) anyNodeInScene.getScene().getWindow();
            URL resource = AppStart.class.getResource(fxmlPath);
            FXMLLoader loader = new FXMLLoader(resource);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            if (title != null) stage.setTitle(title);
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void viewSwitcher() {
        
    }
}
