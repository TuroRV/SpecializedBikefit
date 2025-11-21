package com.dam.specializedbikefit.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private Label loginLabelTitle;
    @FXML
    public PasswordField passwordTextField;
    @FXML
    public TextField emailTextField;

    @FXML
    protected void login() {
        String user_email = emailTextField.getText();
        String password = passwordTextField.getText();


    }
}
