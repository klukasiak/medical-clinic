package tk.kdev.aplikacjebazodanowejavafxspring.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import tk.kdev.aplikacjebazodanowejavafxspring.model.User;

import java.net.URL;
import java.util.ResourceBundle;

public class DoctorPaneController implements Initializable {

    @FXML
    private Label infoLabel;

    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            infoLabel.setText(String.valueOf(user));
        });
    }
}
