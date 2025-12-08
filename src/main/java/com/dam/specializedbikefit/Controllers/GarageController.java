package com.dam.specializedbikefit.Controllers;

import com.dam.specializedbikefit.Classes.Bicycle;
import com.dam.specializedbikefit.DAOs.BicycleDAO;
import com.dam.specializedbikefit.DAOs.BicycleDAOImpl;
import com.dam.specializedbikefit.DAOs.UserDAO;
import com.dam.specializedbikefit.DAOs.UserDAOImpl;
import com.dam.specializedbikefit.Navigation.Alerts;
import com.dam.specializedbikefit.Navigation.AppView;
import com.dam.specializedbikefit.Navigation.ViewSwitcher;
import com.dam.specializedbikefit.Singleton.UserSession;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Optional;
import java.util.Set;

public class GarageController {


    BicycleDAO bicycleDAO = new BicycleDAOImpl();
    UserDAO userDAO = new UserDAOImpl();

    private ObservableList<Bicycle> userBicycles;
    private Bicycle selectedBike = null;

    @FXML
    public TableView<Bicycle> garageTable;
    @FXML
    public TableColumn<Bicycle, String> brandColumn;
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
    @FXML
    public Button actionBtn;


    public void initialize() {
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

        garageTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {

                selectedBike = newSelection;
                fillForm(newSelection);
                actionBtn.setText("Modificar Bicicleta"); // Feedback visual
            }
        });


    }

    private void fillForm(Bicycle bike) {
        brandField.setText(bike.getBike_brand());
        modelField.setText(bike.getBike_model());
        yearField.setText(String.valueOf(bike.getBike_year()));
        reachSlider.setValue(bike.getBike_reach());
        stackSlider.setValue(bike.getBike_stack());
        seatTubeSlider.setValue(bike.getBike_seattubelength());
        topTubeSlider.setValue(bike.getBike_toptubelength());
        isEbikeCheck.setSelected(bike.isBike_isEbike());

        String size = bike.getBike_size();
        for (Toggle t : sizeGroup.getToggles()) {
            RadioButton rb = (RadioButton) t;
            if (rb.getText().equals(size)) {
                sizeGroup.selectToggle(rb);
                break;
            }
        }
    }

    private void loadUserBikes() {
        Set<Bicycle> bicyclesSet = userDAO.getUserBicycles(UserSession.getUser());
        userBicycles = FXCollections.observableArrayList(bicyclesSet);
        garageTable.setItems(userBicycles);
    }

    public void setSliderConfig(Slider slider, Label label) {
        label.setText((int) slider.getValue() + " mm");
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            label.setText((newValue.intValue() + " mm"));
        });
    }

    public void deleteBicycle(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Eliminar bicicleta");
        alert.setHeaderText(null);
        alert.setContentText("¿Estás seguro de que quieres eliminar la bicicleta seleccionada?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {

            Bicycle selectedBicycle = (Bicycle) garageTable.getSelectionModel().getSelectedItem();
            if (selectedBicycle != null) {
                try {
                    userDAO.deleteBicycleFromUser(UserSession.getUser(), selectedBicycle);
                    userBicycles.remove(selectedBicycle);
                    Alerts.showStandardAlert(Alert.AlertType.INFORMATION, "Éxito", "Eliminada correctamente", "Bicicleta " + selectedBicycle.getBike_brand() + " " + selectedBicycle.getBike_model() + " eliminada del garaje de " + UserSession.getUser().getUser_name());
                } catch (Exception e) {
                    Alerts.showStandardAlert(Alert.AlertType.ERROR, "Error", "Fallo al eliminar de bd", "Ha ocurrido un error al eliminar de la base de datos.");
                }
            }

        }
    }

    public void goToMenu(ActionEvent actionEvent) {
        ViewSwitcher.showView(AppView.MENU);
    }

    public void saveOrUpdateBicycle(ActionEvent actionEvent) {

        if (brandField.getText().isEmpty() || modelField.getText().isEmpty() || sizeGroup.getSelectedToggle() == null) {
            Alerts.showStandardAlert(Alert.AlertType.ERROR, "Error", "Campos vacíos", "Rellena todos los datos.");
            return;
        }

        try {

            String size = ((RadioButton) sizeGroup.getSelectedToggle()).getText();

            if (selectedBike == null) {
                // === MODO CREAR (ADD) ===
                Bicycle newBike = new Bicycle(
                        brandField.getText(), modelField.getText(), size,
                        Integer.parseInt(yearField.getText()),
                        (float) reachSlider.getValue(), (float) stackSlider.getValue(),
                        (float) seatTubeSlider.getValue(), (float) topTubeSlider.getValue(),
                        isEbikeCheck.isSelected()
                );

                Bicycle savedBike = bicycleDAO.addBicycle(newBike);
                userDAO.addBicycleToUser(savedBike, UserSession.getUser());

                userBicycles.add(savedBike); // Actualizar tabla
                Alerts.showStandardAlert(Alert.AlertType.INFORMATION, "Éxito", "Añadida", "Bici creada.");

            } else {

                selectedBike.setBike_brand(brandField.getText());
                selectedBike.setBike_model(modelField.getText());
                selectedBike.setBike_size(size);
                selectedBike.setBike_year(Integer.parseInt(yearField.getText()));
                selectedBike.setBike_reach((float) reachSlider.getValue());
                selectedBike.setBike_stack((float) stackSlider.getValue());
                selectedBike.setBike_seattubelength((float) seatTubeSlider.getValue());
                selectedBike.setBike_toptubelength((float) topTubeSlider.getValue());
                selectedBike.setBike_isEbike(isEbikeCheck.isSelected());

                bicycleDAO.updateBicycle(selectedBike);

                garageTable.refresh();

                Alerts.showStandardAlert(Alert.AlertType.INFORMATION, "Éxito", "Modificada", "Datos actualizados.");
            }

            clearForm();

        } catch (Exception e) {
            e.printStackTrace();
            Alerts.showStandardAlert(Alert.AlertType.ERROR, "Error", "Fallo", "Error al guardar.");
        }
    }

    public void clearForm() {

        brandField.clear();
        modelField.clear();
        yearField.clear();
        sizeGroup.selectToggle(null);
        isEbikeCheck.setSelected(false);
        selectedBike = null;
        garageTable.getSelectionModel().clearSelection();
        actionBtn.setText("Añadir al Garaje");

        selectedBike = null;
    }
}
