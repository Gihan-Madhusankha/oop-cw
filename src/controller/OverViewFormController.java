package controller;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import main.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import db.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.DriverDetails;
import model.InParkingDetails;
import model.OnDeliveryDetails;
import model.VehicleDetails;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class OverViewFormController {

    public static String vNo, vType, dL;
    public static ObservableList<String> obList;
    public static ObservableList<String> obList2;
    public static int count = 0, count1 = 0, count2 = 0;
    public static Vehicle v1 = null;
    public JFXComboBox<String> cmbComboSelectVehicle;
    public JFXComboBox<String> cmbComboDriver;
    public JFXTextField txtVehicleType;
    public JFXTextField txtSlotNumber;
    public JFXButton btnOnDeliveryShift;
    public JFXButton btnParkVehicle;
    public Label time;
    public AnchorPane overViewContext;

    public void initialize() {
        timeNow();

        obList = FXCollections.observableArrayList();
        for (VehicleDetails v1 : Database.vehicleDetails) {
            obList.add(v1.getVehicleNumber());
        }
        cmbComboSelectVehicle.setItems(obList);

        obList2 = FXCollections.observableArrayList();
        for (DriverDetails d1 : Database.driverDetails) {
            obList2.add(d1.getDriverName());
        }
        cmbComboDriver.setItems(obList2);

        cmbComboSelectVehicle.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            int x = 0;
            for (int i = 0; i < Database.vehicleDetails.size(); i++) {
                if (cmbComboSelectVehicle.getValue().equals(Database.vehicleDetails.get(i).getVehicleNumber())) {
                    x = i;
                }
            }
            txtVehicleType.setEditable(false);
            txtVehicleType.setText(Database.vehicleDetails.get(x).getVehicleType());
            vNo = cmbComboSelectVehicle.getValue();
            vType = Database.vehicleDetails.get(x).getVehicleType();

            new VSlot();

            txtSlotNumber.setEditable(false);
            txtSlotNumber.setText(String.valueOf(VSlot.slotNo));

            AtomicInteger k = new AtomicInteger();
            for (DVehicle dv : VSlot.hm.values()) {
                if (OverViewFormController.vNo.equals(dv.getVehicleNumber())) {
                    VSlot.hm.forEach((key, value) -> {
                        if (value.getVehicleNumber().equals(dv.getVehicleNumber())) {
                            txtSlotNumber.setText(String.valueOf(key));
                            k.set(key);     // k = key;
                        }
                    });
                }
            }

            if (VSlot.hm.size() == 0) {
                btnOnDeliveryShift.setDisable(true);
            }

            VSlot.hm.forEach((key, value) -> {
                if (txtSlotNumber.getText().equals(k.toString())) {
                    btnParkVehicle.setDisable(true);
                    btnOnDeliveryShift.setDisable(false);
                    cmbComboDriver.getSelectionModel().clearSelection();
                } else {
                    btnOnDeliveryShift.setDisable(true);
                    btnParkVehicle.setDisable(false);
                }
            });

            isFull(); // whether the slots are full
            setCount();
        });

        cmbComboDriver.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            dL = cmbComboDriver.getValue();
        });

    }

    private void isFull() {
        for (InParkingDetails in : VSlot.parkingDetails) {
            if (in.getVehicleType().equals("Van")) {
                count++;
            } else if (in.getVehicleType().equals("Bus")) {
                count1++;
            } else {
                count2++;
            }

            if ((txtVehicleType.getText().equals("Van") && VSlot.indexVan.size() <= count) || (txtVehicleType.getText().equals("Bus") && VSlot.indexBus.size() <= count1)
                    || (txtVehicleType.getText().equals("Cargo Lorry") && VSlot.indexCargoLorry.size() <= count2)) {
                for (InParkingDetails ip : VSlot.parkingDetails) {
                    if (cmbComboSelectVehicle.getValue().equals(ip.getVehicleNumber())) {
                        setCount();
                        return;
                    }
                }
                btnParkVehicle.setDisable(true);
                btnOnDeliveryShift.setDisable(true);
                txtSlotNumber.clear();
                setCount();
                new Alert(Alert.AlertType.WARNING, "Parking slots are full").show();
                return;
            }
        }
    }

    private void setCount() {
        count = 0;
        count1 = 0;
        count2 = 0;
    }

    public void parkVehicleOnAction(ActionEvent actionEvent) throws IOException {

        try {
            getVehicleObject();
            for (InParkingDetails in : VSlot.parkingDetails
            ) {
                if (cmbComboSelectVehicle.getValue().equals(in.getVehicleNumber())) {
                    new Alert(Alert.AlertType.ERROR, "This vehicle is already added.", ButtonType.OK).show();
                    return;
                }
            }
            v1.park();
            new Alert(Alert.AlertType.CONFIRMATION, "Added Vehicle", ButtonType.OK).show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong..!").show();
        }
    }

    public void onDeliveryShiftOnAction(ActionEvent actionEvent) throws IOException {
        try {
            getVehicleObject();
            for (OnDeliveryDetails od : VSlot.leaveParkingDetails
            ) {
                if (cmbComboSelectVehicle.getValue().equals(od.getVehicleNumber())) {
                    new Alert(Alert.AlertType.ERROR, "This vehicle is already released.", ButtonType.OK).show();
                    return;
                }
            }
            v1.leavePark();
            new Alert(Alert.AlertType.CONFIRMATION, "Released Vehicle", ButtonType.OK).show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong..!").show();
        }
    }

    private void getVehicleObject() {
        if (txtVehicleType.getText().equals("Van")) {
            v1 = new Van();
        }
        if (txtVehicleType.getText().equals("Bus")) {
            v1 = new Bus();
        }
        if (txtVehicleType.getText().equals("Cargo Lorry")) {
            v1 = new CargoLorry();
        }
    }

    public void managementLoginOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) overViewContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ManagementLoginForm.fxml"))));
        stage.setTitle("Login Form");
        stage.centerOnScreen();
    }

    private void timeNow() {
        Thread thread = new Thread(() -> {
            SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss aa \nMMMM dd, yyyy");
            while (true) {
                try {
                    Thread.sleep(1000);
                    final String now = formatter.format(new Date());
                    Platform.runLater(() -> {
                        time.setText(now);
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

}
