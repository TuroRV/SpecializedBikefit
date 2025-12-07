package com.dam.specializedbikefit.Controllers;

import com.dam.specializedbikefit.DAOs.UserDAO;
import com.dam.specializedbikefit.DAOs.UserDAOImpl;
import com.dam.specializedbikefit.Navigation.Alerts;
import com.dam.specializedbikefit.Navigation.AppView;
import com.dam.specializedbikefit.Navigation.ViewSwitcher;
import com.dam.specializedbikefit.Singleton.UserSession;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ProfileController {

    UserDAO userDAO = new UserDAOImpl();

    public TextField nameTextField;
    public TextField surnameTextField;
    public Label emailLabel;
    public Label passwordLabel;
    public TextField heightField;
    public TextField torsoLengthField;
    public TextField armLengthField;
    public TextField inseamLengthField;

    public void initialize() {
        nameTextField.setText(UserSession.getUser().getUser_name());
        surnameTextField.setText(UserSession.getUser().getUser_surname());
        emailLabel.setText(UserSession.getUser().getUser_email());
        passwordLabel.setText(UserSession.getUser().getUser_password());
        heightField.setText(String.valueOf(UserSession.getUser().getUser_height()));
        torsoLengthField.setText(String.valueOf(UserSession.getUser().getUser_torsolength()));
        armLengthField.setText(String.valueOf(UserSession.getUser().getUser_armlength()));
        inseamLengthField.setText(String.valueOf(UserSession.getUser().getUser_inseamlength()));
    }

    public void modifyProfile(ActionEvent actionEvent) {

        if (nameTextField.getText().isEmpty() || surnameTextField.getText().isEmpty() || heightField.getText().isEmpty() || torsoLengthField.getText().isEmpty() || armLengthField.getText().isEmpty() || inseamLengthField.getText().isEmpty()) {
            Alerts.showStandardAlert(Alert.AlertType.ERROR, "Error", "Fallo al modificar", "No puede haber campos vacíos");
            return;
        }

            try {
                float newHeight = Float.parseFloat(heightField.getText());
                float newTorsoLength = Float.parseFloat(torsoLengthField.getText());
                float newArmLength = Float.parseFloat(armLengthField.getText());
                float newInseamLength = Float.parseFloat(inseamLengthField.getText());
                if (nameTextField.getText().equals(UserSession.getUser().getUser_name())
                        && surnameTextField.getText().equals(UserSession.getUser().getUser_surname())
                        && heightField.getText().equals(String.valueOf(UserSession.getUser().getUser_height()))
                        && torsoLengthField.getText().equals(String.valueOf(UserSession.getUser().getUser_torsolength()))
                        && armLengthField.getText().equals(String.valueOf(UserSession.getUser().getUser_armlength()))
                        && inseamLengthField.getText().equals(String.valueOf(UserSession.getUser().getUser_inseamlength()))) {
                    Alerts.showStandardAlert(Alert.AlertType.ERROR, "Error", "Fallo al modificar", "No hay modificaciones que hacer");
                    return;
                }

                UserSession.getUser().setUser_name(nameTextField.getText());
                UserSession.getUser().setUser_surname(surnameTextField.getText());
                UserSession.getUser().setUser_height(Float.parseFloat(heightField.getText()));
                UserSession.getUser().setUser_torsolength(Float.parseFloat(torsoLengthField.getText()));
                UserSession.getUser().setUser_armlength(Float.parseFloat(armLengthField.getText()));
                UserSession.getUser().setUser_inseamlength(Float.parseFloat(inseamLengthField.getText()));

                userDAO.updateProfile(UserSession.getUser());

                Alerts.showStandardAlert(Alert.AlertType.INFORMATION,"Actualizado","Actualización correcta","Los datos han sido actualizados correctamente");

            } catch (NumberFormatException e) {
                Alerts.showStandardAlert(Alert.AlertType.ERROR,"Error","Error de formato","Asegúrate de que los valores de los campos númericos sean válidos");
            } catch (Exception e) {
                Alerts.showStandardAlert(Alert.AlertType.ERROR,"Error","Error de conexión","Ha ocurrido un error conectando a base de datos");
            }

    }

    public void returnHome(ActionEvent actionEvent) {
        ViewSwitcher.showView(AppView.MENU);
    }
}
