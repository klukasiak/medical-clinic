package tk.kdev.medicalclinic.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import tk.kdev.medicalclinic.model.Address;
import tk.kdev.medicalclinic.model.User;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class PatientPaneController implements Initializable {

    @FXML
    private Label usernameLabel;

    @FXML
    private Label firstNameLabel;

    @FXML
    private Label lastNameLabel;

    @FXML
    private Label peselLabel;

    @FXML
    private Label phoneNumberLabel;

    @FXML
    private Button changePersonalDataButton;

    @FXML
    private Label streetLabel;

    @FXML
    private Label cityLabel;

    @FXML
    private Label zipCodeLabel;

    @FXML
    private Label houseNumberLabel;

    @FXML
    private Label apartamentNumberLabel;

    @FXML
    private Label stateLabel;

    @FXML
    private Button changeAddress;

    @FXML
    private Label alStreetLabel;

    @FXML
    private Label alCityLabel;

    @FXML
    private Label alZipCodeLabel;

    @FXML
    private Label alHouseNumberLabel;

    @FXML
    private Label alApartamentNumberLabel;

    @FXML
    private Label alStateLabel;

    @FXML
    private Button changeNdAddress;

    @FXML
    private Label loggedAsLabel;

    @FXML
    private Button logoutButton;

    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            loggedAsLabel.setText("Logged as: " + user.getFirstName() + " " + user.getLastName());
            usernameLabel.setText(user.getUsername());
            firstNameLabel.setText(user.getFirstName());
            lastNameLabel.setText(user.getLastName());
            peselLabel.setText(user.getPesel());
            phoneNumberLabel.setText(user.getPhoneNumber());

            List<Address> addressList = new ArrayList<>(user.getAddresses());
            Address address = addressList.get(0);

            setAddressLabels(streetLabel, cityLabel, zipCodeLabel, houseNumberLabel, apartamentNumberLabel, stateLabel, address);

            if (addressList.size() > 1) {
                address = addressList.get(1);
                setAddressLabels(alStreetLabel, alCityLabel, alZipCodeLabel, alHouseNumberLabel, alApartamentNumberLabel, alStateLabel, address);
            }

            logoutButton.setOnAction(event -> {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/LoginPane.fxml"));
                openNewWindow(fxmlLoader);
                Stage stage2 = (Stage) logoutButton.getScene().getWindow();
                stage2.close();

            });

            changePersonalDataButton.setOnAction(event -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SelfEditPane.fxml"));
                    Parent root = loader.load();
                    SelfEditPaneController sepc = loader.getController();
                    sepc.setUser(user);
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        });
    }

    private void setAddressLabels(Label street, Label city, Label zipCode, Label houseNumber, Label apartamentNumber, Label state, Address address) {
        street.setText(address.getStreet());
        city.setText(address.getCity());
        zipCode.setText(address.getZipCode());
        houseNumber.setText(address.getHouseNumber());
        apartamentNumber.setText(address.getApartamentNumber());
        state.setText(address.getState());
    }

    private void openNewWindow(FXMLLoader loader) {
        try {
            Parent root = loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
