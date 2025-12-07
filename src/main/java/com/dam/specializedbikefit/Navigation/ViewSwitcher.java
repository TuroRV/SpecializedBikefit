package com.dam.specializedbikefit.Navigation;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Objects;

public class ViewSwitcher {

    private static BorderPane mainContentPane;

    public static void setMainContentPane(BorderPane mainContentPane) {
        ViewSwitcher.mainContentPane = mainContentPane;
    }

    public static void showView(AppView view) {
        if (mainContentPane == null) {
            System.out.println("Error al cargar la vista " + view.getFxmlFile());
            return;
        }
        try {
            Parent viewRoot = FXMLLoader.load(Objects.requireNonNull(ViewSwitcher.class.getResource(view.getFxmlFile())));
            mainContentPane.setCenter(viewRoot);


        } catch (IOException e) {
            System.out.println("Error al cargar la vista " + view.getFxmlFile());
            throw new RuntimeException(e);
        }
    }
}
