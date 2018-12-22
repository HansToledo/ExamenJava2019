package database;

/**
 * @Autor: Hans Van De Weyer
 * @Date: 15/12/2018
 * @Project: Examen Januari 2019
 * @Purpose: Klasse bevat alle preparedstatemens queries.
 */

import com.mysql.jdbc.Connection;

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
    public static String sqlErrorMessageDBqueries = null;

    public DBqueries() {
        try {
            dbConnection = database.DBConnection.getConnection();


            newActor = dbConnection.prepareStatement((
                    "INSERT INTO actoren " +
                            "(Naam,EnumID) " +
                            "VALUES (?,?)"), Statement.RETURN_GENERATED_KEYS);

            newTypeVervoermiddel = dbConnection.prepareStatement(
                    "INSERT INTO type_vervoermiddel " +
                            "(Naam) " +
                            "VALUES (?)");

            newStatusVervoermiddel = dbConnection.prepareStatement(
                    "INSERT INTO status_vervoermiddel " +
                            "(Situatie) " +
                            "VALUES (?)");

            newVerkeerstoren = dbConnection.prepareStatement(
                    "INSERT INTO verkeerstorens " +
                            "(ActorID) " +
                            "VALUES (?)");

            newHulpdienst = dbConnection.prepareStatement(
                    "INSERT INTO vervoermiddel " +
                            "(ActorID,Snelheid,Reactietijd,Wendbaarheid,Grootte,Capaciteit,Koers,StatusID) " +
                            "VALUES (?,?,?,?,?,?,?,?)");

            newSchip = dbConnection.prepareStatement(
                    "INSERT INTO vervoermiddel " +
                            "(ActorID,Snelheid,Reactietijd,Wendbaarheid,Grootte,Capaciteit,Koers,StatusID) " +
                            "VALUES (?,?,?,?,?,?,?,?)");

            getTypeVervoermiddelID = dbConnection.prepareStatement(
                    "SELECT EnumID FROM type_vervoermiddel WHERE naam LIKE ?");

            getStatusVervoermiddelID = dbConnection.prepareStatement(
                    "SELECT StatusID FROM status_vervoermiddel WHERE situatie LIKE ?");

            getAllVervoermiddelTypes = dbConnection.prepareStatement(
                    "SELECT naam FROM type_vervoermiddel");

            getAllVervoermiddelStatussen= dbConnection.prepareStatement(
                    "SELECT situatie FROM status_vervoermiddel");

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.exit(1);
        }
    }

    // opvragen bestaande statussen vervoermiddels
    public List getAllVervoermiddelStatussen(){
        try {
            ResultSet resultSet = getAllVervoermiddelStatussen.executeQuery();
            List<String> results = new ArrayList<String>();
            while (resultSet.next()) {
                results.add( resultSet.getString("Situatie"));
            }
            return results;
        }
        catch (SQLException sqlException) {
            sqlErrorMessageDBqueries = sqlException.getMessage();
            sqlException.printStackTrace();
        }
        return null;
    }

    // vervoermiddel_status toevoegen
    public int addStatusVervoermiddel(String situatie) {
        try {
            newStatusVervoermiddel.setString(1,situatie);
            return newStatusVervoermiddel.executeUpdate();
        }
        catch (SQLException sqlException) {
            sqlErrorMessageDBqueries = sqlException.getMessage();
            sqlException.printStackTrace();
            return 0;
        }
    }


    // opvragen bestaande types vervoermiddels
    public List getAllVervoermiddelTypes(){
        try {
        ResultSet resultSet = getAllVervoermiddelTypes.executeQuery();
        List<String> results = new ArrayList<String>();
            while (resultSet.next()) {
                results.add( resultSet.getString("Naam"));
            }
        return results;
        }
        catch (SQLException sqlException) {
            sqlErrorMessageDBqueries = sqlException.getMessage();
            sqlException.printStackTrace();
        }
        return null;
    }


    // vervoermiddel_type toevoegen
    public int addTypeVervoermiddel(String naam) {
        try {
            newTypeVervoermiddel.setString(1,naam);
            return newTypeVervoermiddel.executeUpdate();
        }
        catch (SQLException sqlException) {
            sqlErrorMessageDBqueries = sqlException.getMessage();
            sqlException.printStackTrace();
            return 0;
        }
    }

    // verkeerstoren toevoegen
    public int addVerkeerstoren(String enumNaam, String naam) {
        try {
            getTypeVervoermiddelID.setString(1,enumNaam);
            ResultSet resultSet = getTypeVervoermiddelID.executeQuery();
            resultSet.next();
            int EnumID = resultSet.getInt("EnumID");

            newActor.setString(1,naam);
            newActor.setInt(2,EnumID);
            newActor.executeUpdate();

            ResultSet rs = null;
            int ActorID = 0;

            rs = newActor.getGeneratedKeys();   //getGeneratedKeys() is een ingebouwde functie die je kan oproepen door ,Statement.RETURN_GENERATED_KEYS toe te voegen aan de prepared statement.
            if(rs.next()) ActorID = rs.getInt(1);

            newVerkeerstoren.setDouble(1,ActorID);
            return newVerkeerstoren.executeUpdate();
        }
        catch (SQLException sqlException) {
            sqlErrorMessageDBqueries = sqlException.getMessage();
            sqlException.printStackTrace();
            return 0;
        }
    }

    // hulpdienst toevoegen
    public int addHulpdienst(String enumNaam, String naam, double snelheid,double reactietijd,double wendbaarheid,double grootte,double capaciteit,int koers,String status) {
        try {
            getTypeVervoermiddelID.setString(1,enumNaam);
            ResultSet resultSet = getTypeVervoermiddelID.executeQuery();
            resultSet.next();
            int EnumID = resultSet.getInt("EnumID");

            newActor.setString(1,naam);
            newActor.setInt(2,EnumID);
            newActor.executeUpdate();

            ResultSet rs = null;
            int ActorID = 0;

            rs = newActor.getGeneratedKeys();   //getGeneratedKeys() is een ingebouwde functie die je kan oproepen door ,Statement.RETURN_GENERATED_KEYS toe te voegen aan de prepared statement.
            if(rs.next()) ActorID = rs.getInt(1);

            newHulpdienst.setDouble(1,ActorID);
            newHulpdienst.setDouble(2,snelheid);
            newHulpdienst.setDouble(3,reactietijd);
            newHulpdienst.setDouble(4,wendbaarheid);
            newHulpdienst.setDouble(5,grootte);
            newHulpdienst.setDouble(6,capaciteit);
            newHulpdienst.setDouble(7,koers);

            //VANAF HIER-------------------------------------------------------------------------------------------------
            getStatusVervoermiddelID.setString(1,status);
            ResultSet resultSetStatus = getStatusVervoermiddelID.executeQuery();
            resultSetStatus.next();
            int StatusID = resultSetStatus.getInt("StatusID");

            newHulpdienst.setInt(8,StatusID);
            return newHulpdienst.executeUpdate();
        }
        catch (SQLException sqlException) {
            sqlErrorMessageDBqueries = sqlException.getMessage();
            sqlException.printStackTrace();
            return 0;
        }
    }

    // schip toevoegen
    public int addSchip(String enumNaam, String naam, double snelheid,double reactietijd,double wendbaarheid,double grootte,double capaciteit,int koers,String status) {
        try {
            getTypeVervoermiddelID.setString(1,enumNaam);
            ResultSet resultSet = getTypeVervoermiddelID.executeQuery();
            resultSet.next();
            int EnumID = resultSet.getInt("EnumID");

            newActor.setString(1,naam);
            newActor.setInt(2,EnumID);
            newActor.executeUpdate();

            ResultSet rs = null;
            int ActorID = 0;

            rs = newActor.getGeneratedKeys();   //getGeneratedKeys() is een ingebouwde functie die je kan oproepen door ,Statement.RETURN_GENERATED_KEYS toe te voegen aan de prepared statement.
            if(rs.next()) ActorID = rs.getInt(1);

            newSchip.setDouble(1,ActorID);
            newSchip.setDouble(2,snelheid);
            newSchip.setDouble(3,reactietijd);
            newSchip.setDouble(4,wendbaarheid);
            newSchip.setDouble(5,grootte);
            newSchip.setDouble(6,capaciteit);
            newSchip.setDouble(7,koers);

            //VANAF HIER-------------------------------------------------------------------------------------------------
            getStatusVervoermiddelID.setString(1,status);
            ResultSet resultSetStatus = getStatusVervoermiddelID.executeQuery();
            resultSetStatus.next();
            int StatusID = resultSetStatus.getInt("StatusID");

            newSchip.setInt(8,StatusID);
            return newSchip.executeUpdate();
        }
        catch (SQLException sqlException) {
            sqlErrorMessageDBqueries = sqlException.getMessage();
            sqlException.printStackTrace();
            return 0;
        }
    }
}
