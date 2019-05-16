package tk.kdev.medicalclinic.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.kdev.medicalclinic.model.User;
import tk.kdev.medicalclinic.service.UserService;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class SelfEditPaneController implements Initializable {
    @FXML
    private TextField usernameInput;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private TextField firstNameInput;

    @FXML
    private TextField lastNameInput;

    @FXML
    private TextField peselInput;

    @FXML
    private TextField phoneNumberInput;

    @FXML
    private Button addButton;

    @FXML
    private Button cancelButton;

    private User userMemory;

    @Autowired
    private UserService userService;

    public void setUser(User user) {
        this.userMemory = user;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userMemory = LoginPaneController.getUserMemory();

        setDataForEdit(userMemory);

        cancelButton.setOnAction(event -> {
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        });

        addButton.setOnAction(event -> {
            User newUser = new User(usernameInput.getText(), passwordInput.getText(), userMemory.getRole(), firstNameInput.getText(), lastNameInput.getText(), peselInput.getText(), phoneNumberInput.getText());
            newUser.setAddresses(userMemory.getAddresses());
            newUser.setRaports(userMemory.getRaports());
            newUser.setId(userMemory.getId());
            userService.addUser(newUser);
            System.out.println(newUser + " added");
        });
    }

    public void setDataForEdit(User user) {
        usernameInput.setText(user.getUsername());
        passwordInput.setText(user.getPassword());
        firstNameInput.setText(user.getFirstName());
        lastNameInput.setText(user.getLastName());
        peselInput.setText(user.getPesel());
        phoneNumberInput.setText(user.getPhoneNumber());
    }
}
