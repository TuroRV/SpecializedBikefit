module com.dam.specializedbikefit {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;

    opens com.dam.specializedbikefit to javafx.fxml;
    opens com.dam.specializedbikefit.Controllers to javafx.fxml;
    opens com.dam.specializedbikefit.Classes to org.hibernate.orm.core,jakarta.persistence;

    exports com.dam.specializedbikefit;
    exports com.dam.specializedbikefit.Controllers;
}