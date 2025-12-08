package com.dam.specializedbikefit.Controllers;

import com.dam.specializedbikefit.Classes.User;
import com.dam.specializedbikefit.DAOs.UserDAO;
import com.dam.specializedbikefit.DAOs.UserDAOImpl;
import com.dam.specializedbikefit.DBConnection.Connection;
import com.dam.specializedbikefit.Navigation.Alerts;
import com.dam.specializedbikefit.Navigation.Navigator;
import com.dam.specializedbikefit.Singleton.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class LoginController {


    UserDAO userDAO = new UserDAOImpl();

    @FXML
    private Label loginLabelTitle;
    @FXML
    public PasswordField passwordTextField;
    @FXML
    public TextField emailTextField;
    @FXML
    public Button loginButton;

    @FXML
    protected void login() {
        String user_email = emailTextField.getText();
        String password = passwordTextField.getText();

        if(userDAO.validateCredentials(user_email, password)) {

            User user = userDAO.getUserByEmail(user_email);
            UserSession.setUser(user);
            Navigator.changeStage(loginButton,"home-view.fxml","Home");

        }
        else  {
            Alerts.showStandardAlert(Alert.AlertType.ERROR,"Error de Login","Credenciales no válidas","El email o la contraseña no son correctos");
        }
    }

    public void goToCreateProfile(ActionEvent actionEvent) {
        Navigator.changeStage(loginButton,"register-view.fxml","Crear Cuenta");
    }
}
