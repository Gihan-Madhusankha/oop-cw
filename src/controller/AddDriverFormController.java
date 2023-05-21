package controller;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import db.Database;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import model.DriverDetails;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddDriverFormController {
    public JFXTextField txtDriverName;
    public JFXTextField txtNIC;
    public JFXTextField txtDrivingLicenseNo;
    public JFXTextField txtAddress;
    public JFXTextField txtContactNo;

    public void initialize() {
        setValidatorMessage(txtDriverName);
        setValidatorMessage(txtNIC);
        setValidatorMessage(txtDrivingLicenseNo);
        setValidatorMessage(txtAddress);
        setValidatorMessage(txtContactNo);
    }

    private void setValidatorMessage(JFXTextField textFieldName) {
        RequiredFieldValidator validator = new RequiredFieldValidator();
        textFieldName.getValidators().add(validator);
        validator.setMessage("No Input Given");

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

    public void addDriverOnAction(ActionEvent actionEvent) throws IOException {

        Pattern p1 = Pattern.compile("^[a-zA-Z\\s?]*");
        Pattern p2 = Pattern.compile("^\\d{9}[V_$]");
        Pattern p3 = Pattern.compile("^B\\d{7}");
        Pattern p4 = Pattern.compile("^[A-Z][a-z_$]*");
        Pattern p5 = Pattern.compile("^\\d[07]\\d{8}");

        Matcher m1 = p1.matcher(txtDriverName.getText());
        Matcher m2 = p2.matcher(txtNIC.getText());
        Matcher m3 = p3.matcher(txtDrivingLicenseNo.getText());
        Matcher m4 = p4.matcher(txtAddress.getText());
        Matcher m5 = p5.matcher(txtContactNo.getText());
        boolean dName = m1.matches();
        boolean dNic = m2.matches();
        boolean dLicenseNo = m3.matches();
        boolean dAddress = m4.matches();
        boolean dContactNo = m5.matches();

        try {
            if (dName && dNic && dLicenseNo && dAddress && dContactNo) {

                Database.driverDetails.add(new DriverDetails(
                        txtDriverName.getText(), txtNIC.getText(), txtDrivingLicenseNo.getText(), txtAddress.getText(), txtContactNo.getText()
                ));
                OverViewFormController.obList2.add(txtDriverName.getText());

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Driver added successfully.",
                        ButtonType.OK);
                Optional<ButtonType> buttonType = alert.showAndWait();

                if (buttonType.get().equals(ButtonType.OK)) {
                    txtDriverName.clear();
                    txtNIC.clear();
                    txtDrivingLicenseNo.clear();
                    txtAddress.clear();
                    txtContactNo.clear();
                }
            } else
                extracted(dName, dNic, dLicenseNo, dAddress, dContactNo);
        } catch (Exception e) {
            extracted(dName, dNic, dLicenseNo, dAddress, dContactNo);
        }

    }

    private void extracted(boolean dName, boolean dNic, boolean dLicenseNo, boolean dAddress, boolean dContactNo) {
        new Alert(Alert.AlertType.ERROR, "Something went wrong..!").show();
        if (!dName) {
            txtDriverName.clear();
            txtDriverName.setUnFocusColor(Paint.valueOf("red"));
        }
        if (!dNic) {
            txtNIC.clear();
            txtNIC.setUnFocusColor(Paint.valueOf("red"));
        }
        if (!dLicenseNo) {
            txtDrivingLicenseNo.clear();
            txtDrivingLicenseNo.setUnFocusColor(Paint.valueOf("red"));
        }
        if (!dAddress) {
            txtAddress.clear();
            txtAddress.setUnFocusColor(Paint.valueOf("red"));
        }
        if (!dContactNo) {
            txtContactNo.clear();
            txtContactNo.setUnFocusColor(Paint.valueOf("red"));
        }
    }


}
