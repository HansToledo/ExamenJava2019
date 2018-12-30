package model;

import database.DBqueries;
import enums.Actors;
import enums.Hulpdiensten;
import enums.Schepen;
import org.junit.Before;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DatabaseTesten {
    String naam;
    double latitude;
    double longitude;
    double snelheid;
    double grootte;
    double capaciteit;
    int koers;
    DBqueries kustwachtQueries;

    @Before
    public void setUp() throws Exception {
        naam = "junit";
        latitude = 30;
        longitude = 35;
        snelheid = 10;
        grootte = 20;
        capaciteit = 10;
        koers = 180;
        kustwachtQueries = new DBqueries();
    }

    @Test
    public void DatabaseConnection(){
        database.DBConnection.getConnection();
    }

    @Test
    public void addVerkeerstoren() {
        try{
            database.Create.addVerkeerstoren(naam,latitude,longitude);
        }
        catch(Exception ex){
            //ex.printStackTrace();
        }
    }

    @Test
    public void addZeilboot() {
        try{
            database.Create.addSchip(naam, Schepen.ZEILBOOT, latitude, longitude, snelheid, grootte, capaciteit, koers);
        }
        catch(Exception ex){
            //ex.printStackTrace();
        }
    }

    @Test
    public void addTanker() {
        try{
            database.Create.addSchip(naam, Schepen.TANKER, latitude, longitude, snelheid, grootte, capaciteit, koers);
        }
        catch(Exception ex){
            //ex.printStackTrace();
        }
    }

    @Test
    public void addContainerschip() {
        try{
            database.Create.addSchip(naam, Schepen.CONTAINERSCHIP, latitude, longitude, snelheid, grootte, capaciteit, koers);
        }
        catch(Exception ex){
            //ex.printStackTrace();
        }
    }

    @Test
    public void addMotorboot() {
        try{
            database.Create.addSchip(naam, Schepen.MOTORBOOT, latitude, longitude, snelheid, grootte, capaciteit, koers);
        }
        catch(Exception ex){
            //ex.printStackTrace();
        }
    }

    @Test
    public void addScheepsvaartpolitie() {
        try{
            database.Create.addHulpdienst(naam, Hulpdiensten.SCHEEPSVAARTPOLITIE, latitude, longitude, snelheid, grootte, capaciteit, koers);
        }
        catch(Exception ex){
            //ex.printStackTrace();
        }
    }

    @Test
    public void addSeaking() {
        try{
            database.Create.addHulpdienst(naam, Hulpdiensten.SEAKING, latitude, longitude, snelheid, grootte, capaciteit, koers);
        }
        catch(Exception ex){
            //ex.printStackTrace();
        }
    }

    @Test
    public void deleteZeilboot() {
        try{
            kustwachtQueries.deleteSchip(Schepen.ZEILBOOT.toString()+ "-" + naam);;
        }
        catch(Exception ex){
            //ex.printStackTrace();
        }
    }

    @Test
    public void deleteTanker() {
        try{
            kustwachtQueries.deleteSchip(Schepen.TANKER.toString()+ "-" + naam);
        }
        catch(Exception ex){
            //ex.printStackTrace();
        }
    }

    @Test
    public void deleteContainerschip() {
        try{
            kustwachtQueries.deleteSchip(Schepen.CONTAINERSCHIP.toString()+ "-" + naam);
        }
        catch(Exception ex){
            //ex.printStackTrace();
        }
    }

    @Test
    public void deleteMotorboot() {
        try{
            kustwachtQueries.deleteSchip(Schepen.MOTORBOOT.toString()+ "-" + naam);
        }
        catch(Exception ex){
            //ex.printStackTrace();
        }
    }

    @Test
    public void deleteScheepsvaartpolitie() {
        try{
            kustwachtQueries.deleteHulpdienst(Hulpdiensten.SCHEEPSVAARTPOLITIE.toString()+ "-" + naam);
        }
        catch(Exception ex){
            //ex.printStackTrace();
        }
    }

    @Test
    public void deleteSeaking() {
        try{
            kustwachtQueries.deleteHulpdienst(Hulpdiensten.SEAKING.toString()+ "-" + naam);
        }
        catch(Exception ex){
            //ex.printStackTrace();
        }
    }

    @Test
    public void deleteVerkeerstoren() {
        try{
            String test = Actors.VERKEERSTOREN.toString()+ "-" + naam;
            kustwachtQueries.deleteVerkeerstoren(Actors.VERKEERSTOREN.toString()+ "-" + naam);
        }
        catch(Exception ex){
            //ex.printStackTrace();
        }
    }
}