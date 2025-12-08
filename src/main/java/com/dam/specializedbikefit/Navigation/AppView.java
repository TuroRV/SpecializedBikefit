package com.dam.specializedbikefit.Navigation;

public enum AppView {
    LOGIN("/com/dam/specializedbikefit/login-view.fxml"),
    MENU("/com/dam/specializedbikefit/mainMenu-view.fxml"),
    USERPROFILE("/com/dam/specializedbikefit/profile-view.fxml"),
    GARAGE("/com/dam/specializedbikefit/garage-view.fxml"),
    BIKEFIT("/com/dam/specializedbikefit/bikefit-view.fxml"),
    INFO("/com/dam/specializedbikefit/info-view.fxml");

    private final String fxmlFile;

    AppView (String fxmlFile) {
        this.fxmlFile = fxmlFile;
    }

    public String getFxmlFile() {
        return fxmlFile;
    }


}
