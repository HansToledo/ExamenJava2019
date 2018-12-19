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
    private PreparedStatement selectAllBoats;


    public DBqueries() {
        try {
            dbConnection = database.DBConnection.getConnection();

            // EXMAPLE: query that selects all boats entries
            selectAllBoats = dbConnection.prepareStatement(
                    "SELECT * FROM boats ORDER BY BoatName, Name");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.exit(1);
        }
    }
}
