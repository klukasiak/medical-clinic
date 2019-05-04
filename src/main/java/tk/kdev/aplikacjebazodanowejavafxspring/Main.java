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
import tk.kdev.aplikacjebazodanowejavafxspring.model.User;
import tk.kdev.aplikacjebazodanowejavafxspring.repository.UserRepository;

import java.io.IOException;

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
    public CommandLineRunner initData(UserRepository userRepo){
        return args -> {
            userRepo.save(new User("adam", "123"));
            userRepo.save(new User("damian", "123456"));
            userRepo.save(new User("konrad", "321"));
            userRepo.save(new User("tomek", "qwe"));
            userRepo.save(new User("michal", "ewq"));

        };
    }
}
