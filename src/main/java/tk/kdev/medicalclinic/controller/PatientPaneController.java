package tk.kdev.medicalclinic.controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.kdev.medicalclinic.Main;
import tk.kdev.medicalclinic.exception.UserNotFoundException;
import tk.kdev.medicalclinic.model.Address;
import tk.kdev.medicalclinic.model.Raport;
import tk.kdev.medicalclinic.model.User;
import tk.kdev.medicalclinic.model.Visit;
import tk.kdev.medicalclinic.service.RaportService;
import tk.kdev.medicalclinic.service.UserService;
import tk.kdev.medicalclinic.service.VisitService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
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
    private Label loggedAsLabel;

    @FXML
    private Button logoutButton;

    @FXML
    private Button raportHistoryButton;

    @FXML
    private Button refreshButton;

    @FXML
    private TableView<Visit> visitTable;

    @FXML
    private TableColumn<Visit, Date> dateColumn;

    @FXML
    private TableColumn<Visit, Date> timeColumn;

    @FXML
    private TableColumn<Visit, String> doctorColumn;

    @FXML
    private TableColumn<Visit, String> specColumn;

    @FXML
    private Button addVisitButton;

    @FXML
    private Button editVisitButton;

    @FXML
    private Button deleteVisitButton;

    private User userMemory;

    public void setUser(User user) {
        userMemory = user;
    }

    @Autowired
    private RaportService raportService;

    @Autowired
    private UserService userService;

    @Autowired
    private VisitService visitService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        userMemory = LoginPaneController.getUserMemory();

        setPersonalLabels(userMemory);
        takeAddressesAndSetLabels();

        initializeTable();


        logoutButton.setOnAction(event -> {
            try {
                callView("/view/LoginPane.fxml");
                Stage stage2 = (Stage) logoutButton.getScene().getWindow();
                stage2.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        changePersonalDataButton.setOnAction(event -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/SelfEditPane.fxml"));
                fxmlLoader.setControllerFactory(Main.getSpringContext()::getBean);
                Parent root = fxmlLoader.load();
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                stage.setOnCloseRequest(event1 -> {
                    try {
                        refreshData();
                    } catch (UserNotFoundException e) {
                        e.printStackTrace();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        raportHistoryButton.setOnAction(event -> {
            try {
                callView("/view/HistoryRaportPane.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
            List<Raport> raports = raportService.getAllRaportsByUserId(userMemory.getId());
            for (Raport r : raports)
                System.out.println(r);
        });

        refreshButton.setOnAction(event -> {
            try {
                refreshData();
            } catch (UserNotFoundException e) {
                e.printStackTrace();
            }
        });

        changeAddress.setOnAction(event -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/ChangeAddressPane.fxml"));
                fxmlLoader.setControllerFactory(Main.getSpringContext()::getBean);
                Parent root = fxmlLoader.load();
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                stage.setOnCloseRequest(event1 -> {
                    try {
                        refreshData();
                    } catch (UserNotFoundException e) {
                        e.printStackTrace();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        addVisitButton.setOnAction(event -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/AddVisitPane.fxml"));
                fxmlLoader.setControllerFactory(Main.getSpringContext()::getBean);
                Parent root = fxmlLoader.load();
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                stage.setOnCloseRequest(event1 -> {
                    try {
                        refreshData();
                    } catch (UserNotFoundException e) {
                        e.printStackTrace();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
            visitTable.setItems(FXCollections.observableList(visitService.getAllVisits()));
        });

        editVisitButton.setOnAction(event -> {
            Visit v = visitTable.getSelectionModel().getSelectedItem();
            System.out.println(v);
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/EditVisitPane.fxml"));
                fxmlLoader.setControllerFactory(Main.getSpringContext()::getBean);
                Parent root = fxmlLoader.load();
                EditVisitPaneController evpc = fxmlLoader.getController();
                evpc.setVisitToChange(v);
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                stage.setOnCloseRequest(event1 -> {
                    visitTable.setItems(FXCollections.observableList(visitService.getAllVisits()));
                });
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e);
            }

        });

        deleteVisitButton.setOnAction(event -> {
            visitService.deleteVisitByVisit(visitTable.getSelectionModel().getSelectedItem());
            System.out.println("Deleted");
            visitTable.setItems(FXCollections.observableList(visitService.getAllVisits()));

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

    private void setPersonalLabels(User user) {
        try {
            setUser(userService.findById(user.getId()));
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        loggedAsLabel.setText("Logged as: " + user.getFirstName() + " " + user.getLastName());
        usernameLabel.setText(user.getUsername());
        firstNameLabel.setText(user.getFirstName());
        lastNameLabel.setText(user.getLastName());
        peselLabel.setText(user.getPesel());
        phoneNumberLabel.setText(user.getPhoneNumber());
    }

    private void takeAddressesAndSetLabels() {
        List<Address> addressList = new ArrayList<>(userMemory.getAddresses());
        Address address = addressList.get(0);

        setAddressLabels(streetLabel, cityLabel, zipCodeLabel, houseNumberLabel, apartamentNumberLabel, stateLabel, address);

        if (addressList.size() > 1) {
            address = addressList.get(1);
            setAddressLabels(alStreetLabel, alCityLabel, alZipCodeLabel, alHouseNumberLabel, alApartamentNumberLabel, alStateLabel, address);
        }
    }

    private void callView(String path) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(path));
        LoginPaneController.openNewWindow(fxmlLoader);
    }

    private void initializeTable() {
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("visitDate"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("visitTime"));
        doctorColumn.setCellValueFactory(
                Visit -> {
                    SimpleObjectProperty property = new SimpleObjectProperty();
                    property.setValue(Visit.getValue().getDoctor().getFirstName() + " " + Visit.getValue().getDoctor().getLastName());
                    return property;
                }
        );
        specColumn.setCellValueFactory(
                Visit -> {
                    SimpleObjectProperty property = new SimpleObjectProperty();
                    property.setValue(Visit.getValue().getDoctor().getSpecializations());
                    return property;
                }
        );
        ObservableList<Visit> visits = FXCollections.observableList(visitService.getAllByPatientId(userMemory.getId()));
        visitTable.setItems(visits);
        visitTable.getSortOrder().add(dateColumn);
    }

    private void refreshData() throws UserNotFoundException {
        userMemory = userService.findById(userMemory.getId());
        setPersonalLabels(userMemory);
        takeAddressesAndSetLabels();
        ObservableList<Visit> visits = FXCollections.observableList(visitService.getAllByPatientId(userMemory.getId()));
        visitTable.setItems(visits);
        visitTable.getSortOrder().add(dateColumn);
    }
}
