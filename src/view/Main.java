package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/controller/Kustwacht.fxml"));
        primaryStage.setTitle("Examen2019");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(event -> System.exit(0));
        primaryStage.show();
    }


    public static void main(String[] args) {

        BasicConfigurator.configure();
        EventLogger.logger.setLevel(Level.ALL);

        database.DBConnection.getConnection(); //connectie maken met de database
        database.Read.addEnumHulpdienstenEnSchepenToDB();
        database.Read.addEnumStatusVervoermiddelToDB();

        Randomizer random = new Randomizer();

//        random.generateVerkeerstores(5);
//        random.generateHulpdiensten(5);
//        random.generateSchepen(5);

        random.inlezenVerkeerstorens();
        random.inlezenSchepen();
        random.inlezenHulpdiensten();

        random.printAllActors();

        launch(args);

    }
}




