package com.dam.specializedbikefit.Controllers;

import com.dam.specializedbikefit.Classes.Bicycle;
import com.dam.specializedbikefit.Classes.Bikefit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class BikefitController {

    @FXML
    public TableView<Bikefit> historyTable;
    @FXML
    public TableColumn<Bikefit, String> colBikefitName;
    @FXML
    public TableColumn<Bikefit, String> colBike;
    @FXML
    public TableColumn<Bikefit, LocalDate> colDate;
    @FXML
    public TableColumn<Bikefit, Float> colSaddleHeight;
    @FXML
    public TableColumn<Bikefit, Float> colSaddleToBarReach;
    @FXML
    public TableColumn<Bikefit, Float> colSetback;
    @FXML
    public Label resSaddleHeight;
    @FXML
    public Label resReach;
    @FXML
    public Label resSetback;
    @FXML
    public TextField bikefitNameField;
    @FXML
    public ComboBox<Bicycle> bikeSelector;


    public void initialize(){
        colBikefitName.setCellValueFactory(new PropertyValueFactory<>("bikefit_name"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("bikefit_date"));

        colSaddleHeight.setCellValueFactory(new PropertyValueFactory<>("bikefit_saddleHeight"));
        colSaddleToBarReach.setCellValueFactory(new PropertyValueFactory<>("bikefit_saddleToBar"));
        colSetback.setCellValueFactory(new PropertyValueFactory<>("bikefit_saddleSetBack"));

        colBike.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getBicycle().getBike_model())
        );
    }

    public void deleteBikefit(ActionEvent actionEvent) {
    }

    public void generateBikefit(ActionEvent actionEvent) {
    }
}
