package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ManagementLoginFormController {

    public JFXTextField txtUserName;
    public JFXPasswordField pwdPassword;
    public AnchorPane loginContext;

    public void loginBtnOnAction(ActionEvent actionEvent) throws IOException {
        if (txtUserName.getText().equals("m") && pwdPassword.getText().equals("123")) {
            Stage stage = (Stage) loginContext.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/InParkingForm.fxml"))));
            stage.setTitle("InParking Form");
            stage.centerOnScreen();
        } else {
            new Alert(Alert.AlertType.ERROR, "Log In failed...!").show();
        }
    }

    public void cancelBtnOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) loginContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/OverViewForm.fxml"))));
        stage.centerOnScreen();
    }

}
