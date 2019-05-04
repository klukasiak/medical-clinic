package tk.kdev.aplikacjebazodanowejavafxspring.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.kdev.aplikacjebazodanowejavafxspring.model.User;
import tk.kdev.aplikacjebazodanowejavafxspring.service.UserService;

import java.net.URL;
import java.util.Optional;
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
                String username = login.getText();
                String pass = password.getText();
                Optional<User> user = userService.findUserByUsername(username);
                if(!user.isPresent())
                    testLabel.setText("NIE ZNALEZIONO");
                else
                    if(user.get().getPassword().equals(pass))
                        testLabel.setText("ZALOGOWANO");
                    else
                        testLabel.setText("ZLE HASLO");

        });
    }
}
