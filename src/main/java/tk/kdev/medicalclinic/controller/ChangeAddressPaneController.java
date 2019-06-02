package tk.kdev.medicalclinic.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.kdev.medicalclinic.model.Address;
import tk.kdev.medicalclinic.model.User;
import tk.kdev.medicalclinic.service.AddressService;
import tk.kdev.medicalclinic.service.UserService;

import java.net.URL;
import java.util.*;

@Component
public class ChangeAddressPaneController implements Initializable {

    @FXML
    private TextField streetInput;

    @FXML
    private TextField cityInput;

    @FXML
    private TextField zipCodeInput;

    @FXML
    private TextField houseNumberInput;

    @FXML
    private TextField apartamentNumberInput;

    @FXML
    private TextField stateInput;

    @FXML
    private Button addButton;

    @FXML
    private Button cancelButton;

    @FXML
    private ChoiceBox<String> choiceAddress;

    private User user;

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        user = LoginPaneController.getUserMemory();
        List<Address> addressList = new ArrayList<>(user.getAddresses());
        if (addressList.size() > 1) {
            choiceAddress.getItems().addAll("0", "1");
        } else {
            choiceAddress.getItems().addAll("0", "add new");
        }
        choiceAddress.setValue("0");
        setTextFieldsValues("0", addressList);
        choiceAddress.setOnAction(event -> {
            setTextFieldsValues(choiceAddress.getValue(), addressList);
        });

        addButton.setOnAction(event -> {
            Address addressToUpdate = getChoicedAddress(choiceAddress.getValue(), addressList);
            addressToUpdate.setApartamentNumber(apartamentNumberInput.getText());
            addressToUpdate.setCity(cityInput.getText());
            addressToUpdate.setHouseNumber(houseNumberInput.getText());
            addressToUpdate.setState(stateInput.getText());
            addressToUpdate.setZipCode(zipCodeInput.getText());
            addressToUpdate.setStreet(streetInput.getText());
            if (!choiceAddress.getValue().equals("add new")) {
                addressList.set(Integer.parseInt(choiceAddress.getValue()), addressToUpdate);
                addressService.addAddress(addressToUpdate);
            } else {
                addressList.add(addressToUpdate);
                addressService.addAddress(addressToUpdate);
            }
            User userToAdd = user;
            Set<Address> setAddress = new HashSet<>(addressList);
            userToAdd.setAddresses(setAddress);
            userService.addUser(userToAdd);
            Stage stage = (Stage) addButton.getScene().getWindow();
            stage.close();
        });

        cancelButton.setOnAction(event -> {
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        });
    }

    private void setTextFieldsValues(String n, List<Address> addressList) {
        Address ad = getChoicedAddress(n, addressList);
        streetInput.setText(ad.getStreet());
        cityInput.setText(ad.getCity());
        zipCodeInput.setText(ad.getZipCode());
        houseNumberInput.setText(ad.getHouseNumber());
        apartamentNumberInput.setText(ad.getApartamentNumber());
        stateInput.setText(ad.getState());
    }

    private Address getChoicedAddress(String choice, List<Address> addressList) {
        Address address;
        switch (choice) {
            case "0":
                address = addressList.get(0);
                break;
            case "1":
                address = addressList.get(1);
                break;
            default:
                address = new Address();
        }
        return address;
    }
}
