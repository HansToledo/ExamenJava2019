package database;

/**
 * @Autor: Hans Van De Weyer
 * @Date: 15/12/2018
 * @Project: Examen Januari 2019
 * @Purpose: Klasse bevat alle preparedstatemens queries.
 */

import calculations.Coördinaten;
import com.mysql.jdbc.Connection;
import model.*;
import strategy.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DBqueries {
    private Connection dbConnection; // manages the connection
    private PreparedStatement newActor;
    private PreparedStatement newVerkeerstoren;
    private PreparedStatement newHulpdienst;
    private PreparedStatement newSchip;
    private PreparedStatement newTypeVervoermiddel;
    private PreparedStatement newStatusVervoermiddel;
    private PreparedStatement getTypeVervoermiddelID;
    private PreparedStatement getStatusVervoermiddelID;
    private PreparedStatement getAllVervoermiddelTypes;
    private PreparedStatement getAllVervoermiddelStatussen;
    private PreparedStatement getAllVerkeerstorens;
    private PreparedStatement getAllSchepen;
    private PreparedStatement getAllHulpdiensten;
    public static String sqlErrorMessageDBqueries = null;
    IHulpdienstStrategy geenStrategy = new GeenStrategy();

    public DBqueries() {
        try {
            dbConnection = database.DBConnection.getConnection();

            getAllVerkeerstorens = dbConnection.prepareStatement(
                    "SELECT type_actor.naam as typenaam, actoren.naam as naam, verkeerstorens.longitude as longitude, verkeerstorens.latitude as latitude FROM verkeerstorens " +
                            "join actoren on (verkeerstorens.ActorID = actoren.ActorID)" +
                            "join type_actor on (type_actor.EnumID = actoren.EnumID);");

            getAllSchepen = dbConnection.prepareStatement(
                    "SELECT type_actor.naam as typenaam, actoren.naam as naam, \n" +
                            "longitude, latitude,snelheid,reactietijd,wendbaarheid,grootte,capaciteit,koers,situatie\n" +
                            "FROM vervoermiddel \n" +
                            "join actoren on (vervoermiddel.ActorID = actoren.ActorID)\n" +
                            "join type_actor on (type_actor.EnumID = actoren.EnumID)\n" +
                            "join status_vervoermiddel on (vervoermiddel.StatusID = status_vervoermiddel.StatusID)\n" +
                            "WHERE (IsSchip = '1');");

            getAllHulpdiensten = dbConnection.prepareStatement(
                    "SELECT type_actor.naam as typenaam, actoren.naam as naam, \n" +
                            "longitude, latitude,snelheid,reactietijd,wendbaarheid,grootte,capaciteit,koers,situatie\n" +
                            "FROM vervoermiddel\n" +
                            "join actoren on (vervoermiddel.ActorID = actoren.ActorID)\n" +
                            "join type_actor on (type_actor.EnumID = actoren.EnumID)\n" +
                            "join status_vervoermiddel on (vervoermiddel.StatusID = status_vervoermiddel.StatusID)\n" +
                            "WHERE not (type_actor.naam ='VERKEERSTOREN') AND (IsSchip = '0');");

            newActor = dbConnection.prepareStatement((
                    "INSERT INTO actoren " +
                            "(Naam,EnumID) " +
                            "VALUES (?,?)"), Statement.RETURN_GENERATED_KEYS);

            newTypeVervoermiddel = dbConnection.prepareStatement(
                    "INSERT INTO type_actor " +
                            "(Naam,IsSchip) " +
                            "VALUES (?,?)");

            newStatusVervoermiddel = dbConnection.prepareStatement(
                    "INSERT INTO status_vervoermiddel " +
                            "(Situatie) " +
                            "VALUES (?)");

            newVerkeerstoren = dbConnection.prepareStatement(
                    "INSERT INTO verkeerstorens " +
                            "(ActorID, latitude, longitude) " +
                            "VALUES (?,?,?)");

            newHulpdienst = dbConnection.prepareStatement(
                    "INSERT INTO vervoermiddel " +
                            "(ActorID,Snelheid,Reactietijd,Wendbaarheid,Grootte,Capaciteit,Koers,Latitude,Longitude,StatusID) " +
                            "VALUES (?,?,?,?,?,?,?,?,?,?)");

            newSchip = dbConnection.prepareStatement(
                    "INSERT INTO vervoermiddel " +
                            "(ActorID,Snelheid,Reactietijd,Wendbaarheid,Grootte,Capaciteit,Koers,Latitude,Longitude,StatusID) " +
                            "VALUES (?,?,?,?,?,?,?,?,?,?)");

            getTypeVervoermiddelID = dbConnection.prepareStatement(
                    "SELECT EnumID FROM type_actor WHERE naam LIKE ?");

            getStatusVervoermiddelID = dbConnection.prepareStatement(
                    "SELECT StatusID FROM status_vervoermiddel WHERE situatie LIKE ?");

            getAllVervoermiddelTypes = dbConnection.prepareStatement(
                    "SELECT naam FROM type_actor");

            getAllVervoermiddelStatussen = dbConnection.prepareStatement(
                    "SELECT situatie FROM status_vervoermiddel");

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.exit(1);
        }
    }

    // opvragen alle verkeerstorens
    public ArrayList<Verkeerstoren> getAllVerkeerstorens() {
        try (ResultSet resultSet = getAllVerkeerstorens.executeQuery()) {
            ArrayList<Verkeerstoren> results = new ArrayList<Verkeerstoren>();

            while (resultSet.next()) {
                String typeNaam = resultSet.getString("typenaam");
                String naam = resultSet.getString("naam");
                double lon = Double.parseDouble(resultSet.getString("longitude"));
                double lat = Double.parseDouble(resultSet.getString("latitude"));
                Coördinaten coördinaten = new Coördinaten(lat, lon);

                results.add(new Verkeerstoren(typeNaam, naam, coördinaten, geenStrategy));
            }
            return results;
        } catch (SQLException sqlException) {
            sqlErrorMessageDBqueries = sqlException.getMessage();
            sqlException.printStackTrace();
        }
        return null;
    }


    // opvragen alle schepen
    public ArrayList<Vervoermiddel> getAllSchepen() {
        try (ResultSet resultSet = getAllSchepen.executeQuery()) {
            ArrayList<Vervoermiddel> results = new ArrayList<Vervoermiddel>();

            while (resultSet.next()) {
                String typeNaam = resultSet.getString("typenaam");
                String naam = resultSet.getString("naam");
                double lon = Double.parseDouble(resultSet.getString("longitude"));
                double lat = Double.parseDouble(resultSet.getString("latitude"));
                Coördinaten coördinaten = new Coördinaten(lat, lon);
                double snelheid = Double.parseDouble(resultSet.getString("snelheid"));
                double reactietijd = Double.parseDouble(resultSet.getString("reactietijd"));
                double wendbaarheid = Double.parseDouble(resultSet.getString("wendbaarheid"));
                double grootte = Double.parseDouble(resultSet.getString("grootte"));
                double capaciteit = Double.parseDouble(resultSet.getString("capaciteit"));
                int koers = Integer.parseInt(resultSet.getString("koers"));
                String status = resultSet.getString("situatie");

                switch (typeNaam) {
                    case "CONTAINERSCHIP":
                        results.add(new ContainerSchip(typeNaam, naam, coördinaten, snelheid, grootte, capaciteit, koers, geenStrategy, status));
                        break;
                    case "MOTORBOOT":
                        results.add(new Motorboot(typeNaam, naam, coördinaten, snelheid, grootte, capaciteit, koers, geenStrategy, status));
                        break;
                    case "TANKER":
                        results.add(new Tanker(typeNaam, naam, coördinaten, snelheid, grootte, capaciteit, koers, geenStrategy, status));
                        break;
                    case "ZEILBOOT":
                        results.add(new Zeilboot(typeNaam, naam, coördinaten, snelheid, grootte, capaciteit, koers, geenStrategy, status));
                        break;
                }
            }
            return results;
        } catch (SQLException sqlException) {
            sqlErrorMessageDBqueries = sqlException.getMessage();
            sqlException.printStackTrace();
        }
        return null;
    }


    // opvragen alle hulpdiensten
    public ArrayList<Vervoermiddel> getAllHulpdiensten() {
        try (ResultSet resultSet = getAllHulpdiensten.executeQuery()) {
            ArrayList<Vervoermiddel> results = new ArrayList<Vervoermiddel>();

            while (resultSet.next()) {
                String typeNaam = resultSet.getString("typenaam");
                String naam = resultSet.getString("naam");
                double lon = Double.parseDouble(resultSet.getString("longitude"));
                double lat = Double.parseDouble(resultSet.getString("latitude"));
                Coördinaten coördinaten = new Coördinaten(lat, lon);
                double snelheid = Double.parseDouble(resultSet.getString("snelheid"));
                double reactietijd = Double.parseDouble(resultSet.getString("reactietijd"));
                double wendbaarheid = Double.parseDouble(resultSet.getString("wendbaarheid"));
                double grootte = Double.parseDouble(resultSet.getString("grootte"));
                double capaciteit = Double.parseDouble(resultSet.getString("capaciteit"));
                int koers = Integer.parseInt(resultSet.getString("koers"));
                String status = resultSet.getString("situatie");

                switch (typeNaam) {
                    case "SEAKING":
                        results.add(new Seaking(typeNaam, naam, coördinaten, snelheid, grootte, capaciteit, koers, geenStrategy, status));
                        break;
                    case "SCHEEPSVAARTPOLITIE":
                        results.add(new ScheepsvaartPolitie(typeNaam, naam, coördinaten, snelheid, grootte, capaciteit, koers, geenStrategy, status));
                        break;
                }
            }
            return results;
        } catch (SQLException sqlException) {
            sqlErrorMessageDBqueries = sqlException.getMessage();
            sqlException.printStackTrace();
        }
        return null;
    }


    // opvragen bestaande statussen vervoermiddelen
    public List getAllVervoermiddelStatussen() {
        try {
            ResultSet resultSet = getAllVervoermiddelStatussen.executeQuery();
            List<String> results = new ArrayList<String>();
            while (resultSet.next()) {
                results.add(resultSet.getString("Situatie"));
            }
            return results;
        } catch (SQLException sqlException) {
            sqlErrorMessageDBqueries = sqlException.getMessage();
            sqlException.printStackTrace();
        }
        return null;
    }


    // vervoermiddel_status toevoegen
    public int addStatusVervoermiddel(String situatie) {
        try {
            newStatusVervoermiddel.setString(1, situatie);
            return newStatusVervoermiddel.executeUpdate();
        } catch (SQLException sqlException) {
            sqlErrorMessageDBqueries = sqlException.getMessage();
            sqlException.printStackTrace();
            return 0;
        }
    }


    // opvragen bestaande types vervoermiddels
    public List getAllVervoermiddelTypes() {
        try {
            ResultSet resultSet = getAllVervoermiddelTypes.executeQuery();
            List<String> results = new ArrayList<String>();
            while (resultSet.next()) {
                results.add(resultSet.getString("Naam"));
            }
            return results;
        } catch (SQLException sqlException) {
            sqlErrorMessageDBqueries = sqlException.getMessage();
            sqlException.printStackTrace();
        }
        return null;
    }


    // vervoermiddel_type toevoegen
    public int addTypeVervoermiddel(String naam, int IsSchip) {
        try {
            newTypeVervoermiddel.setString(1, naam);
            newTypeVervoermiddel.setInt(2, IsSchip);
            return newTypeVervoermiddel.executeUpdate();
        } catch (SQLException sqlException) {
            sqlErrorMessageDBqueries = sqlException.getMessage();
            sqlException.printStackTrace();
            return 0;
        }
    }

    // verkeerstoren toevoegen
    public int addVerkeerstoren(String enumNaam, String naam, Coördinaten coördinaten) {
        try {
            getTypeVervoermiddelID.setString(1, enumNaam);
            ResultSet resultSet = getTypeVervoermiddelID.executeQuery();
            resultSet.next();
            int EnumID = resultSet.getInt("EnumID");

            newActor.setString(1, naam);
            newActor.setInt(2, EnumID);
            newActor.executeUpdate();

            ResultSet rs = null;
            int ActorID = 0;

            rs = newActor.getGeneratedKeys();   //getGeneratedKeys() is een ingebouwde functie die je kan oproepen door ,Statement.RETURN_GENERATED_KEYS toe te voegen aan de prepared statement.
            if (rs.next()) ActorID = rs.getInt(1);

            double lat = coördinaten.getBreedte();
            double lon = coördinaten.getLengte();

            newVerkeerstoren.setDouble(1, ActorID);
            newVerkeerstoren.setDouble(2, lat);
            newVerkeerstoren.setDouble(3, lon);
            return newVerkeerstoren.executeUpdate();
        } catch (SQLException sqlException) {
            sqlErrorMessageDBqueries = sqlException.getMessage();
            sqlException.printStackTrace();
            return 0;
        }
    }

    // hulpdienst toevoegen
    public int addHulpdienst(String enumNaam, String naam, double snelheid, double reactietijd, double wendbaarheid, double grootte, double capaciteit, int koers, String status, Coördinaten coördinaten) {
        try {
            getTypeVervoermiddelID.setString(1, enumNaam);
            ResultSet resultSet = getTypeVervoermiddelID.executeQuery();
            resultSet.next();
            int EnumID = resultSet.getInt("EnumID");

            newActor.setString(1, naam);
            newActor.setInt(2, EnumID);
            newActor.executeUpdate();

            ResultSet rs = null;
            int ActorID = 0;

            rs = newActor.getGeneratedKeys();   //getGeneratedKeys() is een ingebouwde functie die je kan oproepen door ,Statement.RETURN_GENERATED_KEYS toe te voegen aan de prepared statement.
            if (rs.next()) ActorID = rs.getInt(1);

            double lat = coördinaten.getBreedte();
            double lon = coördinaten.getLengte();

            newHulpdienst.setDouble(1, ActorID);
            newHulpdienst.setDouble(2, snelheid);
            newHulpdienst.setDouble(3, reactietijd);
            newHulpdienst.setDouble(4, wendbaarheid);
            newHulpdienst.setDouble(5, grootte);
            newHulpdienst.setDouble(6, capaciteit);
            newHulpdienst.setDouble(7, koers);
            newHulpdienst.setDouble(8, lat);
            newHulpdienst.setDouble(9, lon);

            getStatusVervoermiddelID.setString(1, status);
            ResultSet resultSetStatus = getStatusVervoermiddelID.executeQuery();
            resultSetStatus.next();
            int StatusID = resultSetStatus.getInt("StatusID");

            newHulpdienst.setInt(10, StatusID);
            return newHulpdienst.executeUpdate();
        } catch (SQLException sqlException) {
            sqlErrorMessageDBqueries = sqlException.getMessage();
            sqlException.printStackTrace();
            return 0;
        }
    }

    // schip toevoegen
    public int addSchip(String enumNaam, String naam, double snelheid, double reactietijd, double wendbaarheid, double grootte, double capaciteit, int koers, String status, Coördinaten coördinaten) {
        try {
            getTypeVervoermiddelID.setString(1, enumNaam);
            ResultSet resultSet = getTypeVervoermiddelID.executeQuery();
            resultSet.next();
            int EnumID = resultSet.getInt("EnumID");

            newActor.setString(1, naam);
            newActor.setInt(2, EnumID);
            newActor.executeUpdate();

            ResultSet rs = null;
            int ActorID = 0;

            rs = newActor.getGeneratedKeys();   //getGeneratedKeys() is een ingebouwde functie die je kan oproepen door ,Statement.RETURN_GENERATED_KEYS toe te voegen aan de prepared statement.
            if (rs.next()) ActorID = rs.getInt(1);

            double lat = coördinaten.getBreedte();
            double lon = coördinaten.getLengte();

            newSchip.setDouble(1, ActorID);
            newSchip.setDouble(2, snelheid);
            newSchip.setDouble(3, reactietijd);
            newSchip.setDouble(4, wendbaarheid);
            newSchip.setDouble(5, grootte);
            newSchip.setDouble(6, capaciteit);
            newSchip.setDouble(7, koers);
            newSchip.setDouble(8, lat);
            newSchip.setDouble(9, lon);

            getStatusVervoermiddelID.setString(1, status);
            ResultSet resultSetStatus = getStatusVervoermiddelID.executeQuery();
            resultSetStatus.next();
            int StatusID = resultSetStatus.getInt("StatusID");

            newSchip.setInt(10, StatusID);
            return newSchip.executeUpdate();
        } catch (SQLException sqlException) {
            sqlErrorMessageDBqueries = sqlException.getMessage();
            sqlException.printStackTrace();
            return 0;
        }
    }
}
