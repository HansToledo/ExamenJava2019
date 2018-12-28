package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/controller/Kustwacht.fxml"));
        primaryStage.setTitle("Examen2019");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {

        database.DBConnection.getConnection(); //connectie maken met de database
        database.Create.addEnumHulpdienstenEnSchepenToDB();
        database.Create.addEnumStatusVervoermiddelToDB();

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




