package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;

import java.util.ArrayList;

public class Main extends Application {

    public static ArrayList<String> output = new ArrayList<String>();

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

        database.Read.inlezenVerkeerstorens();
        database.Read.inlezenSchepen();
        database.Read.inlezenHulpdiensten();

        printAllActors();

        launch(args);
    }


    //Afdrukken van alle actoren.
    public static void printAllActors() {
        //ArrayList<String> output = new ArrayList<String>();

        output.add("\nVERKEERSTORENS");
        for (int i = 0; i < Actor.verkeerstorens.size(); i++) {
            output.add("Actor: " + Actor.verkeerstorens.get(i).getEnumNaam() + "   Naam: " + Actor.verkeerstorens.get(i).getNaam() + "   Coördinaten: " + Actor.verkeerstorens.get(i).getCoördinaten());
        }
        output.add("\nHULPDIENSTEN");
        for (int i = 0; i < Actor.mogelijkeHulpdiensten.size(); i++) {
            output.add("Actor: " + Actor.mogelijkeHulpdiensten.get(i).getEnumNaam() + "   Naam: " + Actor.mogelijkeHulpdiensten.get(i).getNaam() + "   Status: " + Actor.mogelijkeHulpdiensten.get(i).getStatus()
                    + "   Capaciteit: " + Actor.mogelijkeHulpdiensten.get(i).getCapaciteit() + "   Coördinaten: " + Actor.mogelijkeHulpdiensten.get(i).getCoördinaten() + "   Grootte: " + Actor.mogelijkeHulpdiensten.get(i).getGrootte()
                    + "   Koers: " + Actor.mogelijkeHulpdiensten.get(i).getKoers() + "   Reactietijd: " + Actor.mogelijkeHulpdiensten.get(i).getReactieTijd() + "   Snelheid: " + Actor.mogelijkeHulpdiensten.get(i).getSnelheid()
                    + "   Wendbaarheid: " + Actor.mogelijkeHulpdiensten.get(i).getWendbaarheid() + "   Strategy: " + Actor.mogelijkeHulpdiensten.get(i).getHulpdienstStrategy());
        }
        output.add("\nSCHEPEN");
        for (int i = 0; i < Actor.schepenOpWater.size(); i++) {
            output.add("Actor: " + Actor.schepenOpWater.get(i).getEnumNaam() + "   Naam: " + Actor.schepenOpWater.get(i).getNaam() + "   Status: " + Actor.schepenOpWater.get(i).getStatus()
                    + "   Capaciteit: " + Actor.schepenOpWater.get(i).getCapaciteit() + "   Coördinaten: " + Actor.schepenOpWater.get(i).getCoördinaten() + "   Grootte: " + Actor.schepenOpWater.get(i).getGrootte()
                    + "   Koers: " + Actor.schepenOpWater.get(i).getKoers() + "   Reactietijd: " + Actor.schepenOpWater.get(i).getReactieTijd() + "   Snelheid: " + Actor.schepenOpWater.get(i).getSnelheid()
                    + "   Wendbaarheid: " + Actor.schepenOpWater.get(i).getWendbaarheid() + "   Strategy: " + Actor.schepenOpWater.get(i).getHulpdienstStrategy());
        }

        for (String object : output) {
            EventLogger.logger.info(String.format(object));
            //System.out.println(object);
        }
    }
}




