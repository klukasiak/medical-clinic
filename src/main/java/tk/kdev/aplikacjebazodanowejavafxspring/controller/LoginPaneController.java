package tk.kdev.aplikacjebazodanowejavafxspring.controller;

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
import tk.kdev.aplikacjebazodanowejavafxspring.exception.LoginAndPasswordNotMatch;
import tk.kdev.aplikacjebazodanowejavafxspring.exception.UserNotFoundException;
import tk.kdev.aplikacjebazodanowejavafxspring.model.User;
import tk.kdev.aplikacjebazodanowejavafxspring.service.UserService;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        submit.setOnAction(event -> {
                String username;
                username = login.getText();
                String pass;
                pass = password.getText();
                try {
                    User user;
                    user = userService.findUserByUsername(username);
                    if(!user.getPassword().equals(pass))
                        throw new LoginAndPasswordNotMatch();
                    callView(user);
                    Stage stage = (Stage) submit.getScene().getWindow();
                    stage.close();
                } catch(UserNotFoundException e){
                    testLabel.setText(e.toString());
                } catch (LoginAndPasswordNotMatch e) {
                    testLabel.setText(e.toString());
                } catch (Exception e){
                    System.out.println(e);
                }
        });
    }

    public void callView(User user){
        Parent root = null;
        try {
            if (user.getRole().getRole().equals("DOCTOR")) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/DoctorPane.fxml"));
                root = fxmlLoader.load();
                DoctorPaneController dpc = fxmlLoader.getController();
                dpc.setUser(user);
            } else if (user.getRole().getRole().equals("PATIENT")) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/PatientPane.fxml"));
                root = fxmlLoader.load();
                PatientPaneController ppc = fxmlLoader.getController();
                ppc.setUser(user);
            } else {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/AdminPane.fxml"));
            }

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch(Exception e){
            System.out.println(e);
        }
    }
}
