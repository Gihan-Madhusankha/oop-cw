package controller;

import javafx.scene.Parent;
import main.VSlot;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.InParkingDetails;
import java.io.IOException;

public class InParkingFormController {
    public AnchorPane context1;
    public JFXComboBox<String> cmbComboValues;
    public TableView<InParkingDetails> tblInParking;
    public TableColumn colVehicleNumber;
    public TableColumn colVehicleType;
    public TableColumn colParkingSlot;
    public TableColumn colParkedTime;

    public void initialize() {
        ObservableList<String> ob = FXCollections.observableArrayList();
        ob.add("On Delivery");
        cmbComboValues.setItems(ob);

        colVehicleNumber.setCellValueFactory(new PropertyValueFactory<>("vehicleNumber"));
        colVehicleType.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));
        colParkingSlot.setCellValueFactory(new PropertyValueFactory<>("slotNumber"));
        colParkedTime.setCellValueFactory(new PropertyValueFactory<>("format"));

        loadAllParkedVehicle();

        cmbComboValues.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (cmbComboValues.getValue().equals("On Delivery")) {
                Stage stage = (Stage) context1.getScene().getWindow();
                try {
                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/OnDeliveryForm.fxml"))));
                    stage.setTitle("OnDelivery Form");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stage.centerOnScreen();
            }
        });
    }

    private void loadAllParkedVehicle() {

        ObservableList<InParkingDetails> obList = FXCollections.observableArrayList();
        for (InParkingDetails in : VSlot.parkingDetails) {
            InParkingDetails tm = new InParkingDetails(
                    in.getVehicleNumber(), in.getVehicleType(), in.getSlotNumber(), in.getFormat()
            );
            obList.add(tm);
        }
        tblInParking.setItems(obList);

    }

    public void addVehicleBtnOnAction(ActionEvent actionEvent) throws IOException {
        setUi("AddVehicleForm");
    }

    public void addDriverBtnOnAction(ActionEvent actionEvent) throws IOException {
        setUi("AddDriverForm");
    }

    public void logOutBtnOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) context1.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/OverViewForm.fxml"))));
        stage.centerOnScreen();
    }

    public void setUi(String location) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.setTitle(location);
        stage.show();
    }
}
