package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import db.Database;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import model.VehicleDetails;

import java.io.IOException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddVehicleFormController {
    public JFXTextField txtVehicleNumber;
    public JFXTextField txtMaximumWeight;
    public JFXTextField txtNoOfPassengers;
    public JFXComboBox<String> txtVehicleType;

    public void initialize() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        obList.add("Bus");
        obList.add("Van");
        obList.add("Cargo Lorry");
        txtVehicleType.setItems(obList);

        setValidatorMessage(txtVehicleNumber);
        setValidatorMessage(txtMaximumWeight);
        setValidatorMessage(txtNoOfPassengers);

    }

    private void setValidatorMessage(JFXTextField textFieldName) {
        RequiredFieldValidator validator = new RequiredFieldValidator();

        textFieldName.getValidators().add(validator);
        validator.setMessage("no input given");

        textFieldName.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    textFieldName.validate();
                }
            }
        });

        textFieldName.setOnKeyReleased(event -> {
            textFieldName.resetValidation();
            textFieldName.setUnFocusColor(Paint.valueOf("#130f40"));
        });
    }

    public void addVehicleOnAction(ActionEvent actionEvent) {

        Pattern p1 = Pattern.compile("^[a-zA-Z]*\\s-?\\s?\\d{4}");
        Pattern p2 = Pattern.compile("^[0-9]{3,4} *");
        Pattern p3 = Pattern.compile("^[0-9]{1,2} *");
        Matcher m1 = p1.matcher(txtVehicleNumber.getText());
        Matcher m2 = p2.matcher(String.valueOf(txtMaximumWeight.getText()));
        Matcher m3 = p3.matcher(String.valueOf(txtNoOfPassengers.getText()));

        boolean number = m1.matches();
        boolean weight = m2.matches();
        boolean passengers = m3.matches();

        if (m1.matches() && weight && passengers) {
            Database.vehicleDetails.add(new VehicleDetails(
                    txtVehicleNumber.getText(), txtVehicleType.getValue(), Integer.parseInt(txtMaximumWeight.getText()), Integer.parseInt(txtNoOfPassengers.getText())
            ));
            OverViewFormController.obList.add(txtVehicleNumber.getText());

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Vehicle added successfully.",
                    ButtonType.OK);
            Optional<ButtonType> buttonType = alert.showAndWait();

            if (buttonType.get().equals(ButtonType.OK)) {
                txtVehicleNumber.clear();
                txtVehicleType.getSelectionModel().clearSelection();
                txtMaximumWeight.clear();
                txtNoOfPassengers.clear();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Something went wrong..!").show();
            if (!number) {
                txtVehicleNumber.clear();
                txtVehicleNumber.setUnFocusColor(Paint.valueOf("red"));
            }
            if (!weight) {
                txtMaximumWeight.clear();
                txtMaximumWeight.setUnFocusColor(Paint.valueOf("red"));
            }
            if (!passengers) {
                txtNoOfPassengers.clear();
                txtNoOfPassengers.setUnFocusColor(Paint.valueOf("red"));
            }
        }

    }
}
