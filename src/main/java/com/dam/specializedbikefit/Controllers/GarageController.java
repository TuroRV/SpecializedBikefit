package com.dam.specializedbikefit.Controllers;

import com.dam.specializedbikefit.Classes.Bicycle;
import com.dam.specializedbikefit.DAOs.BicycleDAO;
import com.dam.specializedbikefit.DAOs.BicycleDAOImpl;
import com.dam.specializedbikefit.DAOs.UserDAO;
import com.dam.specializedbikefit.DAOs.UserDAOImpl;
import com.dam.specializedbikefit.Navigation.Alerts;
import com.dam.specializedbikefit.Singleton.UserSession;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class GarageController {

    BicycleDAO bicycleDAO = new BicycleDAOImpl();
    UserDAO userDAO = new UserDAOImpl();

    public TableView garageTable;
    public TableColumn brandColumn;
    public TableColumn modelColumn;
    public TableColumn sizeColumn;
    public TableColumn yearColumn;
    public TableColumn reachColumn;
    public TableColumn seatTubeColumn;
    public TableColumn topTubeColumn;
    public TableColumn bikeIsEbikeColumn;
    public TextField brandField;
    public TextField modelField;
    public TextField yearField;
    public RadioButton radioS1;
    public ToggleGroup sizeGroup;
    public RadioButton radioS2;
    public RadioButton radioS3;
    public RadioButton radioS4;
    public RadioButton radioS5;
    public CheckBox isEbikeCheck;
    public Label reachValueLabel;
    public Slider reachSlider;
    public Label stackValueLabel;
    public Slider stackSlider;
    public Label seatTubeValueLabel;
    public Slider seatTubeSlider;
    public Label topTubeValueLabel;
    public Slider topTubeSlider;

    public void initialize(){
        setSliderConfig(reachSlider, reachValueLabel);
        setSliderConfig(stackSlider, stackValueLabel);
        setSliderConfig(seatTubeSlider, seatTubeValueLabel);
        setSliderConfig(topTubeSlider, topTubeValueLabel);
    }
    public void setSliderConfig(Slider slider, Label label){
        label.setText((int)slider.getValue() + " mm");
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            label.setText((newValue.intValue() + " mm"));
        });
        }


    public void addBicycle(ActionEvent actionEvent) {

        RadioButton selectedRadio = (RadioButton) sizeGroup.getSelectedToggle();
        String size = selectedRadio.getText();

        Bicycle formBicycle = new Bicycle(brandField.getText(), modelField.getText(), size, Integer.parseInt(yearField.getText()), (float) reachSlider.getValue(), (float) stackSlider.getValue(), (float) seatTubeSlider.getValue(), (float) topTubeSlider.getValue(), isEbikeCheck.isSelected());

        try {
            Bicycle bicycle = bicycleDAO.addBicycle(formBicycle);
            userDAO.addBicycleToUser(bicycle, UserSession.getUser());
            Alerts.showStandardAlert(Alert.AlertType.INFORMATION, "Éxito", "Añadida correctamente", "Bicicleta " + bicycle.getBike_brand() + " " + bicycle.getBike_model() + " añadida al garaje de " + UserSession.getUser().getUser_name());
            clearForm();
        } catch (Exception e) {
            e.printStackTrace();
            Alerts.showStandardAlert(Alert.AlertType.ERROR,"Error","Fallo al añadir a bd","Ha ocurrido un error al guardar en la base de datos.");
        }
    }

    public void clearForm(){
        brandField.clear();
        modelField.clear();
        yearField.clear();
        radioS1.setSelected(false);
        radioS2.setSelected(false);
        radioS3.setSelected(false);
        radioS4.setSelected(false);
        radioS5.setSelected(false);
        isEbikeCheck.setSelected(false);
        reachSlider.adjustValue(450);
        stackSlider.adjustValue(600);
        seatTubeSlider.adjustValue(420);
        topTubeSlider.adjustValue(580);
    }
}
