package com.dam.specializedbikefit.Controllers;

import com.dam.specializedbikefit.Classes.Bicycle;
import com.dam.specializedbikefit.DAOs.BicycleDAO;
import com.dam.specializedbikefit.DAOs.BicycleDAOImpl;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class GarageController {

    BicycleDAO bicycleDAO = new BicycleDAOImpl();

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

        Bicycle bicycle = new Bicycle(brandField.getText(), modelField.getText(), size, Integer.parseInt(yearField.getText()),(float)reachSlider.getValue(),(float)stackSlider.getValue(),(float)seatTubeSlider.getValue(),(float)topTubeSlider.getValue(),isEbikeCheck.isSelected());




    }
}
