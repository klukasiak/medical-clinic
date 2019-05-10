package tk.kdev.aplikacjebazodanowejavafxspring.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import tk.kdev.aplikacjebazodanowejavafxspring.model.Address;
import tk.kdev.aplikacjebazodanowejavafxspring.model.User;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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

            if(addressList.size() > 1){
                address = addressList.get(1);
                setAddressLabels(alStreetLabel, alCityLabel, alZipCodeLabel, alHouseNumberLabel, alApartamentNumberLabel, alStateLabel, address);
            }
        });
    }

    private void setAddressLabels(Label street, Label city, Label zipCode, Label houseNumber, Label apartamentNumber, Label state, Address address){
        street.setText(address.getStreet());
        city.setText(address.getCity());
        zipCode.setText(address.getZipCode());
        houseNumber.setText(address.getHouseNumber());
        apartamentNumber.setText(address.getApartamentNumber());
        state.setText(address.getState());
    }
}
