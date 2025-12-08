package com.dam.specializedbikefit.Controllers;

import com.dam.specializedbikefit.Classes.User;
import com.dam.specializedbikefit.DAOs.UserDAO;
import com.dam.specializedbikefit.DAOs.UserDAOImpl;
import com.dam.specializedbikefit.Navigation.Alerts;
import com.dam.specializedbikefit.Navigation.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Optional;

public class RegisterController {

    UserDAO userDAO = new UserDAOImpl();

    @FXML
    public TextField nameTextField;
    @FXML
    public TextField surnameTextField;
    @FXML
    public TextField emailTextField;
    @FXML
    public PasswordField passwordTextField;
    @FXML
    public PasswordField confirmPasswordTextField;
    @FXML
    public TextField heightField;
    @FXML
    public TextField torsoLengthField;
    @FXML
    public TextField armLengthField;
    @FXML
    public TextField inseamLengthField;
    @FXML
    public Button returnLogin;
    @FXML
    public Button createProfile;

    public void returnLogin(ActionEvent actionEvent) {
        Navigator.changeStage(returnLogin,"login-view.fxml","Login");
    }

    public void createProfile(ActionEvent actionEvent) {
        if (nameTextField.getText().isEmpty() || surnameTextField.getText().isEmpty() || emailTextField.getText().isEmpty() || passwordTextField.getText().isEmpty() || confirmPasswordTextField.getText().isEmpty() || heightField.getText().isEmpty() || torsoLengthField.getText().isEmpty() || armLengthField.getText().isEmpty() || inseamLengthField.getText().isEmpty()) {
            Alerts.showStandardAlert(Alert.AlertType.ERROR,"Error","Campos vacíos","Comprueba que todos los campos contienen datos");
            return;
        }
        try {
            float heightCheck = Float.parseFloat(heightField.getText());
            float torsoCheck = Float.parseFloat(torsoLengthField.getText());
            float armCheck = Float.parseFloat(armLengthField.getText());
            float inseamCheck = Float.parseFloat(inseamLengthField.getText());
        } catch (NumberFormatException e) {
            Alerts.showStandardAlert(Alert.AlertType.ERROR,"Error","Error de formato","Comprueba que los campos de medidas contienen datos numéricos");
            return;
        }
        if (!passwordTextField.getText().equals(confirmPasswordTextField.getText())) {
            Alerts.showStandardAlert(Alert.AlertType.ERROR,"Error","Las contraseñas no coinciden","Comprueba que has escrito correctamente la contraseña en ambos campos");
            return;
        }
        User newuser = new User(nameTextField.getText(),surnameTextField.getText(),emailTextField.getText(),passwordTextField.getText(),Float.parseFloat(heightField.getText()),Float.parseFloat(torsoLengthField.getText()),Float.parseFloat(armLengthField.getText()),Float.parseFloat(inseamLengthField.getText()));

        boolean added = userDAO.createUser(newuser);
        if (added) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Éxito");
            alert.setHeaderText("Usuario creado");
            alert.setContentText("Accede a la aplicación con tus credenciales");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                Navigator.changeStage(createProfile,"login-view.fxml","Login");
            }
        }
        else {
            Alerts.showStandardAlert(Alert.AlertType.ERROR,"Error","Error al crear cuenta","No se ha podido crear la cuenta");
        }


    }
}
