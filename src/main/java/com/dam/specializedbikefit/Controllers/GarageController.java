package com.dam.specializedbikefit.Controllers;

import com.dam.specializedbikefit.Classes.Bicycle;
import com.dam.specializedbikefit.DAOs.BicycleDAO;
import com.dam.specializedbikefit.DAOs.BicycleDAOImpl;
import com.dam.specializedbikefit.DAOs.UserDAO;
import com.dam.specializedbikefit.DAOs.UserDAOImpl;
import com.dam.specializedbikefit.Navigation.Alerts;
import com.dam.specializedbikefit.Singleton.UserSession;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Set;

public class GarageController {

    BicycleDAO bicycleDAO = new BicycleDAOImpl();
    UserDAO userDAO = new UserDAOImpl();

    private ObservableList<Bicycle> userBicycles;

    @FXML
    public TableView garageTable;
    @FXML
    public TableColumn<Bicycle,String> brandColumn;
    @FXML
    public TableColumn<Bicycle, String> modelColumn;
    @FXML
    public TableColumn<Bicycle, String> sizeColumn;
    @FXML
    public TableColumn<Bicycle, Integer> yearColumn;
    @FXML
    public TableColumn<Bicycle, Float> reachColumn;
    @FXML
    public TableColumn<Bicycle, Float> stackColumn;
    @FXML
    public TableColumn<Bicycle, Float> seatTubeColumn;
    @FXML
    public TableColumn<Bicycle, Float> topTubeColumn;
    @FXML
    public TableColumn<Bicycle, Boolean> bikeIsEbikeColumn;
    @FXML
    public TextField brandField;
    @FXML
    public TextField modelField;
    @FXML
    public TextField yearField;
    @FXML
    public RadioButton radioS1;
    @FXML
    public ToggleGroup sizeGroup;
    @FXML
    public RadioButton radioS2;
    @FXML
    public RadioButton radioS3;
    @FXML
    public RadioButton radioS4;
    @FXML
    public RadioButton radioS5;
    @FXML
    public CheckBox isEbikeCheck;
    @FXML
    public Label reachValueLabel;
    @FXML
    public Slider reachSlider;
    @FXML
    public Label stackValueLabel;
    @FXML
    public Slider stackSlider;
    @FXML
    public Label seatTubeValueLabel;
    @FXML
    public Slider seatTubeSlider;
    @FXML
    public Label topTubeValueLabel;
    @FXML
    public Slider topTubeSlider;

    public void initialize(){
        setSliderConfig(reachSlider, reachValueLabel);
        setSliderConfig(stackSlider, stackValueLabel);
        setSliderConfig(seatTubeSlider, seatTubeValueLabel);
        setSliderConfig(topTubeSlider, topTubeValueLabel);

        brandColumn.setCellValueFactory(new PropertyValueFactory<>("bike_brand"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("bike_model"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("bike_size"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("bike_year"));
        reachColumn.setCellValueFactory(new PropertyValueFactory<>("bike_reach"));
        stackColumn.setCellValueFactory(new PropertyValueFactory<>("bike_stack"));
        seatTubeColumn.setCellValueFactory(new PropertyValueFactory<>("bike_seattubelength"));
        topTubeColumn.setCellValueFactory(new PropertyValueFactory<>("bike_toptubelength"));
        bikeIsEbikeColumn.setCellValueFactory(new PropertyValueFactory<>("bike_isEbike"));

        loadUserBikes();





    }

    private void loadUserBikes() {
        Set<Bicycle> bicyclesSet = userDAO.getUserBicycles(UserSession.getUser());
        userBicycles = FXCollections.observableArrayList(bicyclesSet);
        garageTable.setItems(userBicycles);
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
