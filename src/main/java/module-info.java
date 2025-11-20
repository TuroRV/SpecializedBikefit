module com.dam.specializedbikefit {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.dam.specializedbikefit to javafx.fxml;
    exports com.dam.specializedbikefit;
    exports com.dam.specializedbikefit.Controllers;
    opens com.dam.specializedbikefit.Controllers to javafx.fxml;
}