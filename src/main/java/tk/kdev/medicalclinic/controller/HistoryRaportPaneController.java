package tk.kdev.medicalclinic.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.kdev.medicalclinic.model.Raport;
import tk.kdev.medicalclinic.model.User;
import tk.kdev.medicalclinic.service.RaportService;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class HistoryRaportPaneController implements Initializable {

    @FXML
    private TableView<Raport> tableView;

    @FXML
    private TableColumn<Raport, Date> dateColumn;

    @FXML
    private TableColumn<Raport, String> descColumn;

    @Autowired
    private RaportService raportService;

    private User user;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        user = LoginPaneController.getUserMemory();
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateRaport"));
        descColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        ObservableList<Raport> raports = FXCollections.observableList(raportService.getAllRaportsByUserId(user.getId()));
        tableView.setItems(raports);
    }
}
