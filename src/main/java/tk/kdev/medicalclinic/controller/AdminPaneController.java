package tk.kdev.medicalclinic.controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.kdev.medicalclinic.Main;
import tk.kdev.medicalclinic.model.User;
import tk.kdev.medicalclinic.model.Visit;
import tk.kdev.medicalclinic.service.UserService;
import tk.kdev.medicalclinic.service.VisitService;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class AdminPaneController implements Initializable {

    @FXML
    private TableView<User> userTable;

    @FXML
    private TableColumn<User, Long> idUserCol;

    @FXML
    private TableColumn<User, String> firstNameUserCol;

    @FXML
    private TableColumn<User, String> lastNameUserCol;

    @FXML
    private TableColumn<User, String> loginUserCol;

    @FXML
    private TableColumn<User, String> peselUserCol;

    @FXML
    private TableColumn<User, String> phoneNoUserCol;

    @FXML
    private TableColumn<User, String> roleUserCol;

    @FXML
    private TableColumn<User, String> specUserCol;

    @FXML
    private Button editUser;

    @FXML
    private Button addUser;

    @FXML
    private TableView<Visit> visitTable;

    @FXML
    private TableColumn<Visit, Long> idVisitCol;

    @FXML
    private TableColumn<Visit, LocalDate> dateVisitCol;

    @FXML
    private TableColumn<Visit, LocalTime> timeVisitCol;

    @FXML
    private TableColumn<Visit, String> patientVisitCol;

    @FXML
    private TableColumn<Visit, String> doctorVisitCol;

    @FXML
    private Button editVisit;

    @FXML
    private Button refreshButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button deleteUser;

    @Autowired
    private UserService userService;

    @Autowired
    private VisitService visitService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<User> users = userService.getAllUsers();
        List<Visit> visits = visitService.getAllVisits();

        idUserCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameUserCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameUserCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        loginUserCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        peselUserCol.setCellValueFactory(new PropertyValueFactory<>("pesel"));
        phoneNoUserCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        roleUserCol.setCellValueFactory(
                User -> {
                    SimpleObjectProperty property = new SimpleObjectProperty();
                    property.setValue(User.getValue().getRoles());
                    return property;
                }
        );
        specUserCol.setCellValueFactory(
                User -> {
                    SimpleObjectProperty property = new SimpleObjectProperty();
                    property.setValue(User.getValue().getSpecializations());
                    return property;
                }
        );

        userTable.setItems(FXCollections.observableList(users));


        idVisitCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        dateVisitCol.setCellValueFactory(new PropertyValueFactory<>("visitDate"));
        timeVisitCol.setCellValueFactory(new PropertyValueFactory<>("visitTime"));
        patientVisitCol.setCellValueFactory(
                Visit -> {
                    SimpleObjectProperty property = new SimpleObjectProperty();
                    property.setValue(Visit.getValue().getPatient().getFirstName() + " " + Visit.getValue().getPatient().getLastName());
                    return property;
                }
        );

        doctorVisitCol.setCellValueFactory(
                Visit -> {
                    SimpleObjectProperty property = new SimpleObjectProperty();
                    property.setValue(Visit.getValue().getDoctor().getFirstName() + " " + Visit.getValue().getDoctor().getLastName());
                    return property;
                }
        );

        visitTable.setItems(FXCollections.observableList(visits));

        addUser.setOnAction(event -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/EditUserPane.fxml"));
                fxmlLoader.setControllerFactory(Main.getSpringContext()::getBean);
                Parent root = fxmlLoader.load();
                EditUserPaneController eupc = fxmlLoader.getController();
                eupc.setUser(null);
                eupc.setIsAdmin(true);
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        refreshButton.setOnAction(event -> {
            List<User> refreshUsers = userService.getAllUsers();
            userTable.setItems(FXCollections.observableList(refreshUsers));
        });

        editUser.setOnAction(event -> {

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/EditUserPane.fxml"));
                fxmlLoader.setControllerFactory(Main.getSpringContext()::getBean);
                Parent root = fxmlLoader.load();
                EditUserPaneController eupc = fxmlLoader.getController();
                eupc.setUser(userTable.getSelectionModel().getSelectedItem());
                eupc.setIsAdmin(true);
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

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

        deleteUser.setOnAction(event -> {
            User fromTable = userTable.getSelectionModel().getSelectedItem();
            userService.deleteUserByUser(fromTable);
            System.out.println("Deleted");
        });
    }
}
