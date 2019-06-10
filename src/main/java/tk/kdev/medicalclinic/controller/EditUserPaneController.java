package tk.kdev.medicalclinic.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.kdev.medicalclinic.model.Address;
import tk.kdev.medicalclinic.model.Role;
import tk.kdev.medicalclinic.model.Specialization;
import tk.kdev.medicalclinic.model.User;
import tk.kdev.medicalclinic.service.AddressService;
import tk.kdev.medicalclinic.service.RoleService;
import tk.kdev.medicalclinic.service.SpecializationService;
import tk.kdev.medicalclinic.service.UserService;

import java.net.URL;
import java.util.*;

@Component
public class EditUserPaneController implements Initializable {

    @FXML
    private TextField usernameInput;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private TextField firstNameInput;

    @FXML
    private TextField lastNameInput;

    @FXML
    private TextField phoneNumberInput;

    @FXML
    private TextField peselInput;

    @FXML
    private ChoiceBox<Role> pickRole;

    @FXML
    private ListView<Specialization> pickSpecializations;

    @FXML
    private Button addUserButton;

    @FXML
    private TextField firstStreetInput;

    @FXML
    private TextField firstCityInput;

    @FXML
    private TextField firstZipCodeInput;

    @FXML
    private TextField firstHouseNumberInput;

    @FXML
    private TextField firstApartamentNumberInput;

    @FXML
    private TextField firstStateInput;

    @FXML
    private TextField secondStreetInput;

    @FXML
    private TextField secondCityInput;

    @FXML
    private TextField secondZipCodeInput;

    @FXML
    private TextField secondHouseNumberInput;

    @FXML
    private TextField secondApartamentNumberInput;

    @FXML
    private TextField secondStateInput;

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private SpecializationService specializationService;

    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    private boolean isAdmin;

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            if (!isAdmin) {
                pickRole.setDisable(true);
                pickSpecializations.setDisable(true);
            }

            List<Role> roles = roleService.getAllRoles();
            List<Specialization> specializations = specializationService.getAllSpecializations();

            if (isAdmin) {
                pickSpecializations.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

                pickRole.setItems(FXCollections.observableList(roles));
                pickSpecializations.setItems(FXCollections.observableList(specializations));
            }

            if (user != null) {
                usernameInput.setText(user.getUsername());
                firstNameInput.setText(user.getFirstName());
                lastNameInput.setText(user.getLastName());
                peselInput.setText(user.getPesel());
                phoneNumberInput.setText(user.getPhoneNumber());
                passwordInput.setText(user.getPassword());

                List<Specialization> userSpecializations = new ArrayList<>(user.getSpecializations());
                for (Specialization s : userSpecializations) {
                    pickSpecializations.getSelectionModel().select(s);
                }

                List<Address> addresses = new ArrayList<>(user.getAddresses());

                if (addresses.size() > 0) {
                    Address a = addresses.get(0);
                    initializeInputs(firstStreetInput, firstCityInput, firstZipCodeInput, firstHouseNumberInput, firstApartamentNumberInput, firstStateInput, a);
                    if (addresses.size() > 1) {
                        a = addresses.get(1);
                        initializeInputs(secondStreetInput, secondCityInput, secondZipCodeInput, secondHouseNumberInput, secondApartamentNumberInput, secondStateInput, a);
                    }
                }
            }

            addUserButton.setOnAction(event -> {
                List<Address> addresses;
                if (user == null) {
                    user = new User();
                    addresses = new ArrayList<>();
                } else {
                    addresses = new ArrayList<>(user.getAddresses());
                }
                user.setUsername(usernameInput.getText());
                user.setFirstName(firstNameInput.getText());
                user.setLastName(lastNameInput.getText());
                user.setPhoneNumber(phoneNumberInput.getText());
                user.setPesel(peselInput.getText());
                user.setPassword(passwordInput.getText());
                userService.addUser(user);
                if (isAdmin) {
                    user.addRole(pickRole.getValue());
                } else {
                    Set<Role> patientRole = new HashSet<>();
                    patientRole.add(roles.get(0));
                    user.setRoles(patientRole);
                }

                userService.addUser(user);

                List<Address> addressesToAdd = new ArrayList<>();

                if (addresses.size() == 0) {
                    Address ad = createAddressFromInput(firstStreetInput, firstCityInput, firstZipCodeInput, firstHouseNumberInput, firstApartamentNumberInput, firstStateInput, null);
                    addressesToAdd.add(ad);
                    if (!secondCityInput.getText().equals("")) {
                        ad = createAddressFromInput(secondStreetInput, secondCityInput, secondZipCodeInput, secondHouseNumberInput, secondApartamentNumberInput, secondStateInput, null);
                        addressesToAdd.add(ad);
                    }
                } else if (addresses.size() == 1) {
                    Address ad = createAddressFromInput(firstStreetInput, firstCityInput, firstZipCodeInput, firstHouseNumberInput, firstApartamentNumberInput, firstStateInput, addresses.get(0));
                    addressesToAdd.add(ad);
                    if (!secondCityInput.getText().equals("")) {
                        ad = createAddressFromInput(secondStreetInput, secondCityInput, secondZipCodeInput, secondHouseNumberInput, secondApartamentNumberInput, secondStateInput, null);
                        addressesToAdd.add(ad);
                    }
                } else {
                    Address ad = createAddressFromInput(firstStreetInput, firstCityInput, firstZipCodeInput, firstHouseNumberInput, firstApartamentNumberInput, firstStateInput, addresses.get(0));
                    addressesToAdd.add(ad);
                    ad = createAddressFromInput(firstStreetInput, firstCityInput, firstZipCodeInput, firstHouseNumberInput, firstApartamentNumberInput, firstStateInput, addresses.get(1));
                    addressesToAdd.add(ad);
                }

                Set<Address> addressSet = new HashSet<>(addressesToAdd);
                user.setAddresses(addressSet);

                List<Specialization> selectedSpecializations = pickSpecializations.getSelectionModel().getSelectedItems();
                System.out.println(selectedSpecializations);
                userService.addUser(user);

                if (isAdmin) {
                    user.getSpecializations().clear();
                    for(Specialization s : selectedSpecializations)
                        user.addSpecialization(s);
                }

                userService.addUser(user);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("User Adding");
                alert.setHeaderText("User Added");
                alert.setContentText("User has been added successfully");
                alert.showAndWait();
                Stage stage = (Stage) addUserButton.getScene().getWindow();
                stage.close();

            });
        });
    }

    public Address createAddressFromInput(TextField street, TextField city, TextField zipCode, TextField houseNumber, TextField apartamentNumber, TextField state, Address address) {
        if (address == null) {
            address = new Address(street.getText(), city.getText(), zipCode.getText(), houseNumber.getText(), apartamentNumber.getText(), state.getText());
        } else {
            address.setStreet(street.getText());
            address.setZipCode(zipCode.getText());
            address.setState(state.getText());
            address.setHouseNumber(houseNumber.getText());
            address.setApartamentNumber(apartamentNumber.getText());
            address.setCity(city.getText());
        }
        return address;
    }

    public void initializeInputs(TextField street, TextField city, TextField zipCode, TextField houseNumber, TextField apartamentNumber, TextField state, Address address) {
        street.setText(address.getStreet());
        city.setText(address.getCity());
        zipCode.setText(address.getZipCode());
        houseNumber.setText(address.getHouseNumber());
        apartamentNumber.setText(address.getApartamentNumber());
        state.setText(address.getState());
    }
}
