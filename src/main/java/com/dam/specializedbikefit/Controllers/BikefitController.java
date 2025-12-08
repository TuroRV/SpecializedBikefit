package com.dam.specializedbikefit.Controllers;

import com.dam.specializedbikefit.Classes.Bicycle;
import com.dam.specializedbikefit.Classes.Bikefit;
import com.dam.specializedbikefit.DAOs.BikefitDAO;
import com.dam.specializedbikefit.DAOs.BikefitDAOImpl;
import com.dam.specializedbikefit.DAOs.UserDAO;
import com.dam.specializedbikefit.DAOs.UserDAOImpl;
import com.dam.specializedbikefit.Navigation.Alerts;
import com.dam.specializedbikefit.Navigation.ViewSwitcher;
import com.dam.specializedbikefit.Singleton.UserSession;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class BikefitController {

    UserDAO userDAO = new UserDAOImpl();
    BikefitDAO bikefitDAO = new BikefitDAOImpl();

    ObservableList<Bikefit> bikefitsList;

    @FXML
    public TableView<Bikefit> bikefitsTable;
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

        loadBicyclesCombobox();
        loadBikefitsTable();
        bikefitsTable.setItems(bikefitsList);

    }

    private void loadBikefitsTable() {
        List<Bikefit> bikefits = bikefitDAO.getBikeFitsByUser(UserSession.getUser());
        bikefitsList = javafx.collections.FXCollections.observableArrayList(bikefits);
    }

    public void loadBicyclesCombobox(){
        Set<Bicycle> userbikes = userDAO.getUserBicycles(UserSession.getUser());
        bikeSelector.getItems().addAll(userbikes);
    }

    public void deleteBikefit(ActionEvent actionEvent) {
        Bikefit selectedBikefit = bikefitsTable.getSelectionModel().getSelectedItem();
        if (selectedBikefit != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Eliminar Bikefit");
            alert.setHeaderText(null);
            alert.setContentText("¿Estás seguro de que quieres eliminar el bikefit seleccionado?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {

                bikefitDAO.deleteBikefit(selectedBikefit);
                bikefitsList.remove(selectedBikefit);
                Alerts.showStandardAlert(Alert.AlertType.INFORMATION, "Éxito", "Bikefit Borrado", "Bikefit borrado correctamente");
            }
        }
        else {
            Alerts.showStandardAlert(Alert.AlertType.ERROR, "Error", "Ningún dato a borrar", "Selecciona algún bikefit");
        }
    }

    public void generateBikefit(ActionEvent actionEvent) {
        if (bikefitNameField.getText().isEmpty() || bikeSelector.getValue() == null){
            Alerts.showStandardAlert(Alert.AlertType.ERROR,"Error","Error en los campos","Rellene todos los campos del formulario");
            return;
        }
        else {
           Bikefit newFit = bikefitDAO.createNewBikefit(bikefitNameField.getText(), UserSession.getUser(), bikeSelector.getValue());

            if (newFit != null) {

                bikefitsList.add(newFit);

                resSaddleHeight.setText(String.format("%.1f cm", newFit.getBikefit_saddleHeight()));
                resReach.setText(String.format("%.1f cm", newFit.getBikefit_saddleToBar()));
                resSetback.setText(String.format("%.1f cm", newFit.getBikefit_saddleSetBack()));

                Alerts.showStandardAlert(Alert.AlertType.INFORMATION, "Éxito", "Bikefit Guardado", "Tu nuevo bikefit ha sido guardado correctamente.");
            }

        }
    }

    public void goToMenu(ActionEvent actionEvent) {
        ViewSwitcher.showView(com.dam.specializedbikefit.Navigation.AppView.MENU);
    }
}
