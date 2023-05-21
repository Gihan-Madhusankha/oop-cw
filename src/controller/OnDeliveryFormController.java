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
import model.OnDeliveryDetails;
import java.io.IOException;

public class OnDeliveryFormController {
    public AnchorPane context2;
    public JFXComboBox<String> cmbComboValues;
    public TableView<OnDeliveryDetails> tblLeaveParking;
    public TableColumn colVehicleNumber;
    public TableColumn colVehicleType;
    public TableColumn colDriverName;
    public TableColumn colLeftTime;

    public void initialize() {
        ObservableList<String> ob = FXCollections.observableArrayList();
        ob.add("In Parking");
        cmbComboValues.setItems(ob);


        colVehicleNumber.setCellValueFactory(new PropertyValueFactory<>("vehicleNumber"));
        colVehicleType.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));
        colDriverName.setCellValueFactory(new PropertyValueFactory<>("driverName"));
        colLeftTime.setCellValueFactory(new PropertyValueFactory<>("format"));

        loadAllLeaveVehicles();

        cmbComboValues.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (cmbComboValues.getValue().equals("In Parking")) {
                Stage stage = (Stage) context2.getScene().getWindow();
                try {
                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/InParkingForm.fxml"))));
                    stage.setTitle("InParking Form");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stage.centerOnScreen();
            }
        });
    }

    private void loadAllLeaveVehicles() {
        ObservableList<OnDeliveryDetails> obList = FXCollections.observableArrayList();
        for (OnDeliveryDetails od : VSlot.leaveParkingDetails) {
            OnDeliveryDetails tm = new OnDeliveryDetails(od.getVehicleNumber(), od.getVehicleType(), od.getDriverName(),
                    od.getFormat());
            obList.add(tm);
        }
        tblLeaveParking.setItems(obList);
    }

    public void addVehicleBtnOnAction(ActionEvent actionEvent) throws IOException {
        setUi("AddVehicleForm");
    }

    public void addDriverBtnOnAction(ActionEvent actionEvent) throws IOException {
        setUi("AddDriverForm");
    }

    public void setUi(String location) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.setTitle(location);
        stage.show();
    }
}
