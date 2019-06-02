package tk.kdev.medicalclinic.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.kdev.medicalclinic.model.Role;
import tk.kdev.medicalclinic.model.User;
import tk.kdev.medicalclinic.model.Visit;
import tk.kdev.medicalclinic.service.RoleService;
import tk.kdev.medicalclinic.service.UserService;
import tk.kdev.medicalclinic.service.VisitService;

import java.net.URL;
import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

@Component
public class EditVisitPaneController implements Initializable {

    @FXML
    private DatePicker dateToChange;

    @FXML
    private ChoiceBox<LocalTime> timeToChange;

    @FXML
    private ChoiceBox<User> docToChange;

    @FXML
    private Button submitButton;

    private Visit visitToChange;

    public void setVisitToChange(Visit visit) {
        visitToChange = visit;
    }

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private VisitService visitService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            LocalTime startTime = LocalTime.of(9, 0);
            LocalTime endTime = LocalTime.of(15, 0);
            List<LocalTime> availableHours = new ArrayList<>();

            do {
                availableHours.add(startTime);
                startTime = startTime.plus(Duration.ofMinutes(30));
            } while (startTime.isBefore(endTime));

            timeToChange.getItems().setAll(availableHours);

            Set<Role> roles = new HashSet<>();
            roles.add(roleService.findRoleByRole("DOCTOR").get());
            List<User> doctors = userService.getUsersByRole(roles);

            docToChange.getItems().setAll(doctors);


            dateToChange.setValue(visitToChange.getVisitDate());
            timeToChange.setValue(visitToChange.getVisitTime());
            docToChange.setValue(visitToChange.getDoctor());

            submitButton.setOnAction(event -> {
                Optional<Visit> isBusy = visitService.getVisitByDoctorDateTime(docToChange.getValue(), dateToChange.getValue(), timeToChange.getValue());
                if (isBusy.isPresent()) {
                    showAlert("Cannot edit visit. This term is busy", "Edit Visit", "OH!");
                } else {
                    visitToChange.setDoctor(docToChange.getValue());
                    visitToChange.setVisitDate(dateToChange.getValue());
                    visitToChange.setVisitTime(timeToChange.getValue());
                    visitService.addVisit(visitToChange);
                    showAlert("Your visit edited :D", "Edit visit", "Success");
                    Stage stage = (Stage) submitButton.getScene().getWindow();
                    stage.close();
                }
            });
        });
    }

    private void showAlert(String content, String title, String header) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
