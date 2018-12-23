package view;

import com.mysql.jdbc.Connection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/controller/sample.fxml"));
        primaryStage.setTitle("Examen2019");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        
        Connection dbConnection = database.DBConnection.getConnection(); //connectie maken met de database

        Randomizer random = new Randomizer();
        random.addEnumHulpdienstenEnSchepenToDB();
        random.addEnumStatusVervoermiddelToDB();
        random.generateVerkeerstores(3);
        random.generateHulpdiensten(5);
        random.generateSchepen(5);

        calculations.GPSDistance gpstest = new calculations.GPSDistance();
        System.out.println(gpstest.GPSDistance(50.0359,5.4253,58.3838,3.0414));
        launch(args);

    }


}



