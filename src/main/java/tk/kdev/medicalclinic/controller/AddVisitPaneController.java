package tk.kdev.medicalclinic.controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import tk.kdev.medicalclinic.model.Specialization;
import tk.kdev.medicalclinic.model.User;
import tk.kdev.medicalclinic.model.Visit;
import tk.kdev.medicalclinic.service.SpecializationService;
import tk.kdev.medicalclinic.service.UserService;
import tk.kdev.medicalclinic.service.VisitService;

import java.net.URL;
import java.time.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class AddVisitPaneController implements Initializable {

    @FXML
    private DatePicker suggestedDate;

    @FXML
    private ChoiceBox<Specialization> specializationPick;

    @FXML
    private ChoiceBox<User> doctorPick;

    @FXML
    private TableView<Visit> tableView;

    @FXML
    private TableColumn<Visit, Date> dateColumn;

    @FXML
    private TableColumn<Visit, Date> timeColumn;

    @FXML
    private TableColumn<Visit, String> doctorColumn;

    @FXML
    private Button addVisit;

    @FXML
    private Button searchButton;

    private User user;

    @Autowired
    private SpecializationService specializationService;

    @Autowired
    private UserService userService;

    @Autowired
    private VisitService visitService;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        user = LoginPaneController.getUserMemory();

        List<Specialization> specializations = specializationService.getAllSpecializations();
        specializationPick.setItems(FXCollections.observableList(specializations));
        specializationPick.setValue(specializations.get(0));

        dateColumn.setCellValueFactory(new PropertyValueFactory<>("visitDate"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("visitTime"));

        doctorColumn.setCellValueFactory(
                Visit -> {
                    SimpleObjectProperty property = new SimpleObjectProperty();
                    property.setValue(Visit.getValue().getDoctor().getFirstName() + " " + Visit.getValue().getDoctor().getLastName());
                    return property;
                }
        );

        specializationPick.setOnAction(event -> {
            List<User> specializedUsers = userService.findUserBySpecialization(specializationPick.getValue());
            doctorPick.setItems(FXCollections.observableList(specializedUsers));
        });

        searchButton.setOnAction(event -> {
            LocalDate date = suggestedDate.getValue();
            List<Visit> visits = visitService.getAllByDateAndDoctor(doctorPick.getValue(), date);
            List<LocalTime> busyHours = new ArrayList<>();
            for(Visit v : visits)
                busyHours.add(v.getVisitTime());
            LocalTime startTime = LocalTime.of(9, 0);
            LocalTime endTime = LocalTime.of(15, 0);
            List<Visit> visitsForPatient = new ArrayList<>();
            do{
               if(!busyHours.contains(startTime)){
                   Visit v = new Visit(date, startTime, user, doctorPick.getValue());
                   System.out.println(v);
                   visitsForPatient.add(v);
               }
               startTime = startTime.plus(Duration.ofMinutes(30));
            } while(startTime.isBefore(endTime));

            tableView.setItems(FXCollections.observableList(visitsForPatient));
        });

        addVisit.setOnAction(event -> {
            Visit v = tableView.getSelectionModel().getSelectedItem();
            visitService.addVisit(v);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Visit Added");
            alert.setHeaderText("Visit");
            alert.setContentText("Your visit has been added");
            alert.showAndWait();
            tableView.getItems().clear();
        });

    }
}
