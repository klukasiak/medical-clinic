package tk.kdev.aplikacjebazodanowejavafxspring;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import tk.kdev.aplikacjebazodanowejavafxspring.model.Role;
import tk.kdev.aplikacjebazodanowejavafxspring.model.Specialization;
import tk.kdev.aplikacjebazodanowejavafxspring.model.User;
import tk.kdev.aplikacjebazodanowejavafxspring.repository.RoleRepository;
import tk.kdev.aplikacjebazodanowejavafxspring.repository.SpecializationRepository;
import tk.kdev.aplikacjebazodanowejavafxspring.repository.UserRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class Main extends Application {

    private ConfigurableApplicationContext springContext;
    private Parent rootNode;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(rootNode));
        primaryStage.show();
    }

    @Override
    public void init() throws IOException {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(Main.class);
        springContext = builder.run(getParameters().getRaw().toArray(new String[0]));

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/LoginPane.fxml"));
        fxmlLoader.setControllerFactory(springContext::getBean);
        rootNode = fxmlLoader.load();
    }

    @Override
    public void stop() throws Exception{
        springContext.close();
    }

    @Bean
    public CommandLineRunner initData(UserRepository userRepo, RoleRepository roleRepo, SpecializationRepository specRepo){
        return args -> {
            roleRepo.save(new Role("PATIENT"));
            roleRepo.save(new Role("DOCTOR"));
            roleRepo.save(new Role("ADMIN"));

            specRepo.save(new Specialization("Surgeon"));
            specRepo.save(new Specialization("Pediatrician"));
            specRepo.save(new Specialization("Laryngologist"));

            User doctor = new User("drmichal", "ewq", roleRepo.findRoleByRole("DOCTOR").get(), "Michal", "Jaroszewski", "235478907", "4889012341", specRepo.findSpecializationBySpecialization("Surgeon").get(), specRepo.findSpecializationBySpecialization("Pediatrician").get());


            userRepo.save(new User("adam", "123", roleRepo.findRoleByRole("PATIENT").get(), "Adam", "Adamowski", "123456789", "298321904"));
            userRepo.save(new User("damian", "123456", roleRepo.findRoleByRole("PATIENT").get(), "Damian", "Olszak", "987654321", "483872639"));
            userRepo.save(new User("konrad", "321", roleRepo.findRoleByRole("PATIENT").get(), "Konrad", "B", "647587921", "481234567"));
            userRepo.save(new User("tomek", "qwe", roleRepo.findRoleByRole("PATIENT").get(), "Tomasz", "Jelinski", "234897123", "483457812"));
            userRepo.save(doctor);

        };
    }
}
