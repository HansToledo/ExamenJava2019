package database;

/**
 * @Autor: Hans Van De Weyer
 * @Date: 15/12/2018
 * @Project: Examen Januari 2019
 * @Purpose: Klasse bevat alle preparedstatemens queries.
 */

import com.mysql.jdbc.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBqueries {
    private Connection dbConnection; // manages the connection
    private PreparedStatement newActor;
    private PreparedStatement newVerkeerstoren;
    private PreparedStatement newHulpdienst;
    private PreparedStatement newSchip;
    public static String sqlErrorMessageDBqueries = null;

    public DBqueries() {
        try {
            dbConnection = database.DBConnection.getConnection();

            newActor = dbConnection.prepareStatement(
                    "INSERT INTO actoren " +
                            "(Naam) " +
                            "VALUES (?)");

            newVerkeerstoren = dbConnection.prepareStatement(
                    "INSERT INTO verkeerstoren " +
                            "(Naam) " +
                            "VALUES (?)");

            newHulpdienst = dbConnection.prepareStatement(
                    "INSERT INTO voertuigen " +
                            "(Naam,Snelheid,Reactietijd,Wendbaarheid,Grootte,Capaciteit,Koers,Status) " +
                            "VALUES (?,?,?,?,?,?,?,?)");

            newSchip = dbConnection.prepareStatement(
                    "INSERT INTO voertuigen " +
                            "(Naam,Snelheid,Reactietijd,Wendbaarheid,Grootte,Capaciteit,Koers,Status) " +
                            "VALUES (?,?,?,?,?,?,?,?)");



        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.exit(1);
        }
    }

    // actor toevoegen
    public int addActor(String naam) {
        try {
            newActor.setString(1,naam);
            return newActor.executeUpdate();
        }
        catch (SQLException sqlException) {
            sqlErrorMessageDBqueries = sqlException.getMessage();
            sqlException.printStackTrace();
            return 0;
        }
    }

    // verkeerstoren toevoegen
    public int addVerkeerstoren(String naam) {
        try {
            newVerkeerstoren.setString(1,naam);
            return newVerkeerstoren.executeUpdate();
        }
        catch (SQLException sqlException) {
            sqlErrorMessageDBqueries = sqlException.getMessage();
            sqlException.printStackTrace();
            return 0;
        }
    }

    // hulpdienst toevoegen
    public int addHulpdienst(String naam,double snelheid,double reactietijd,double wendbaarheid,double grootte,double capaciteit,int koers,String status) {
        try {
            newHulpdienst.setString(1,naam);
            newHulpdienst.setDouble(2,snelheid);
            newHulpdienst.setDouble(3,reactietijd);
            newHulpdienst.setDouble(4,wendbaarheid);
            newHulpdienst.setDouble(5,grootte);
            newHulpdienst.setDouble(6,capaciteit);
            newHulpdienst.setDouble(7,koers);
            newHulpdienst.setString(8,status);
            return newHulpdienst.executeUpdate();
        }
        catch (SQLException sqlException) {
            sqlErrorMessageDBqueries = sqlException.getMessage();
            sqlException.printStackTrace();
            return 0;
        }
    }

    // schip toevoegen
    public int addSchip(String naam,double snelheid,double reactietijd,double wendbaarheid,double grootte,double capaciteit,int koers,String status) {
        try {
            newSchip.setString(1,naam);
            newSchip.setDouble(2,snelheid);
            newSchip.setDouble(3,reactietijd);
            newSchip.setDouble(4,wendbaarheid);
            newSchip.setDouble(5,grootte);
            newSchip.setDouble(6,capaciteit);
            newSchip.setDouble(7,koers);
            newSchip.setString(8,status);
            return newSchip.executeUpdate();
        }
        catch (SQLException sqlException) {
            sqlErrorMessageDBqueries = sqlException.getMessage();
            sqlException.printStackTrace();
            return 0;
        }
    }
}
