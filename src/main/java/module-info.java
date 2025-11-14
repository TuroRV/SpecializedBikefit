module com.dam.specializedbikefit {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.dam.specializedbikefit to javafx.fxml;
    exports com.dam.specializedbikefit;
}