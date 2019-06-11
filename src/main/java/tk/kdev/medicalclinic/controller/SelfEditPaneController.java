package tk.kdev.medicalclinic.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.kdev.medicalclinic.exception.NameException;
import tk.kdev.medicalclinic.exception.PeselLengthException;
import tk.kdev.medicalclinic.exception.PhoneNumberException;
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
            try {
                if(peselInput.getText().length() != 11)
                    throw new PeselLengthException();

                String pattern = "[0-9]+";
                if(!phoneNumberInput.getText().matches(pattern) || phoneNumberInput.getText().length() != 9)
                    throw new PhoneNumberException();

                pattern = "[A-Z][a-z]+";
                if(!firstNameInput.getText().matches(pattern) || !lastNameInput.getText().matches(pattern))
                    throw new NameException();

                User newUser = new User(usernameInput.getText(), passwordInput.getText(), firstNameInput.getText(), lastNameInput.getText(), peselInput.getText(), phoneNumberInput.getText());
                newUser.setRoles(userMemory.getRoles());
                newUser.setAddresses(userMemory.getAddresses());
                newUser.setRaports(userMemory.getRaports());
                newUser.setId(userMemory.getId());
                userService.addUser(newUser);
                showAlert("Edit data", "Successful", "Your data has been edited :)");
                Stage stage = (Stage) addButton.getScene().getWindow();
                stage.getOnCloseRequest()
                        .handle(
                                new WindowEvent(
                                        stage,
                                        WindowEvent.WINDOW_CLOSE_REQUEST
                                )
                        );
                stage.close();
            } catch(PeselLengthException e){
                showAlert("Edit data", "Error", e.toString());
            } catch (PhoneNumberException e) {
                showAlert("Edit data", "Error", e.toString());
            } catch (NameException e) {
                showAlert("Edit data", "Error", e.toString());
            }
        });
    }

    private void setDataForEdit(User user) {
        usernameInput.setText(user.getUsername());
        passwordInput.setText(user.getPassword());
        firstNameInput.setText(user.getFirstName());
        lastNameInput.setText(user.getLastName());
        peselInput.setText(user.getPesel());
        phoneNumberInput.setText(user.getPhoneNumber());
    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
