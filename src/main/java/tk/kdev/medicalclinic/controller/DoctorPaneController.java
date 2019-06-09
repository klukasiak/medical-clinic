package tk.kdev.medicalclinic.controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.kdev.medicalclinic.model.Raport;
import tk.kdev.medicalclinic.model.User;
import tk.kdev.medicalclinic.model.Visit;
import tk.kdev.medicalclinic.service.RaportService;
import tk.kdev.medicalclinic.service.UserService;
import tk.kdev.medicalclinic.service.VisitService;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

@Component
public class DoctorPaneController implements Initializable {

    @FXML
    private TableColumn<Visit, LocalDate> dateSchedulde;

    @FXML
    private Button takeSchedulde;

    @FXML
    private TableView<Visit> scheduldeTable;

    @FXML
    private TableColumn<Visit, LocalTime> timeSchedulde;

    @FXML
    private TableColumn<Visit, String> patientSchedulde;

    @FXML
    private TextField peselInput;

    @FXML
    private Button takePatient;

    @FXML
    private Label firstNameLabel;

    @FXML
    private Label lastNameLabel;

    @FXML
    private Label peselLabel;

    @FXML
    private Label phoneNumberLabel;

    @FXML
    private TableView<Raport> raportTable;

    @FXML
    private TableColumn<Raport, LocalDate> raportDate;

    @FXML
    private TableColumn<Raport, String> raportDesc;

    @FXML
    private Button addRaport;

    @FXML
    private DatePicker datePick;

    @FXML
    private Button logoutButton;

    private User user;

    @Autowired
    private VisitService visitService;

    @Autowired
    private UserService userService;

    @Autowired
    private RaportService raportService;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        user = LoginPaneController.getUserMemory();
        dateSchedulde.setCellValueFactory(new PropertyValueFactory<>("visitDate"));
        timeSchedulde.setCellValueFactory(new PropertyValueFactory<>("visitTime"));
        patientSchedulde.setCellValueFactory(
                Visit -> {
                    SimpleObjectProperty property = new SimpleObjectProperty();
                    property.setValue(Visit.getValue().getPatient().getFirstName() + " " + Visit.getValue().getPatient().getLastName());
                    return property;
                }
        );

        raportDate.setCellValueFactory(new PropertyValueFactory<>("dateRaport"));
        raportDesc.setCellValueFactory(new PropertyValueFactory<>("description"));


        takeSchedulde.setOnAction(event -> {
            List<Visit> visits = visitService.getAllByDateAndDoctor(user, datePick.getValue());
            scheduldeTable.setItems(FXCollections.observableList(visits));
        });

        takePatient.setOnAction(event -> {
            User patient = userService.getUserByPesel(peselInput.getText());
            firstNameLabel.setText(patient.getFirstName());
            lastNameLabel.setText(patient.getLastName());
            peselLabel.setText(patient.getPesel());
            phoneNumberLabel.setText(patient.getPhoneNumber());
            List<Raport> raports = raportService.getAllRaportsByUserId(patient.getId());
            raportTable.setItems(FXCollections.observableList(raports));
        });

        addRaport.setOnAction(event -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Add raport");
            dialog.setHeaderText("Add raport for patient:");
            dialog.setContentText("Description: ");

            Optional<String> result = dialog.showAndWait();

            result.ifPresent(description -> {
                User patient = userService.getUserByPesel(peselInput.getText());
                patient.getRaports().add(new Raport(description, LocalDate.now()));
                userService.addUser(patient);
                patient = userService.getUserByPesel(peselInput.getText());
                firstNameLabel.setText(patient.getFirstName());
                lastNameLabel.setText(patient.getLastName());
                peselLabel.setText(patient.getPesel());
                phoneNumberLabel.setText(patient.getPhoneNumber());
                List<Raport> raports = raportService.getAllRaportsByUserId(patient.getId());
                raportTable.setItems(FXCollections.observableList(raports));
            });
        });

        logoutButton.setOnAction(event -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/LoginPane.fxml"));
                LoginPaneController.openNewWindow(fxmlLoader);
                Stage stage2 = (Stage) logoutButton.getScene().getWindow();
                stage2.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
