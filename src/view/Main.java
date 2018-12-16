package view;

import com.mysql.jdbc.Connection;
import enums.Actors;
import enums.Hulpdiensten;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/controller/sample.fxml"));
        primaryStage.setTitle("Examen2019");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {

        launch(args);

        AbstractActorFactory testHulpdienst = FactoryProducer.getFactory(Actors.HULPDIENST);
        IHulpdienst hulpdienst = testHulpdienst.getHulpDienst(Hulpdiensten.SCHEEPSVAARTPOLITIE);
        hulpdienst.test();

        Connection dbConnection = database.DBConnection.getConnection(); //connectie maken met de database

    }






}



