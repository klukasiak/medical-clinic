package tk.kdev.medicalclinic.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.kdev.medicalclinic.Main;
import tk.kdev.medicalclinic.exception.LoginAndPasswordNotMatch;
import tk.kdev.medicalclinic.exception.UserNotFoundException;
import tk.kdev.medicalclinic.model.User;
import tk.kdev.medicalclinic.service.UserService;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class LoginPaneController implements Initializable {

    @FXML
    private PasswordField password;

    @FXML
    private TextField login;

    @FXML
    private Button submit;

    @FXML
    private Label testLabel;

    @Autowired
    private UserService userService;

    private static User userMemory;

    static User getUserMemory() {
        return userMemory;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        submit.setOnAction(event -> {

            String username = login.getText();
            String pass = password.getText();

            try {
                User user = userService.findUserByUsername(username);
                if (!user.getPassword().equals(pass))
                    throw new LoginAndPasswordNotMatch();
                userMemory = user;
                callView(userMemory);
                Stage stage = (Stage) submit.getScene().getWindow();
                stage.close();
            } catch (UserNotFoundException e) {
                testLabel.setText(e.toString());
            } catch (LoginAndPasswordNotMatch e) {
                testLabel.setText(e.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void callView(User user) {
        String userRole = user.getRole().getRole();
        System.out.println(userRole);
        FXMLLoader fxmlLoader = null;

        switch (userRole) {
            case "PATIENT":
                fxmlLoader = new FXMLLoader(getClass().getResource("/view/PatientPane.fxml"));
                break;
            case "DOCTOR":
                fxmlLoader = new FXMLLoader(getClass().getResource("/view/DoctorPane.fxml"));
                break;
            case "ADMIN":
                fxmlLoader = new FXMLLoader(getClass().getResource("/view/AdminPane.fxml"));
                break;
        }

        try {
            assert fxmlLoader != null;
            openNewWindow(fxmlLoader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void openNewWindow(FXMLLoader fxmlLoader) throws java.io.IOException {
        fxmlLoader.setControllerFactory(Main.getSpringContext()::getBean);
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
