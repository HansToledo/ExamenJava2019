package database;

import com.mysql.jdbc.Connection;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.File;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {
    private static String DATABASE_URL = null;
    private static String USERNAME = null;
    private static String PASSWORD = null;
    private static String DRIVER = null;
    private static Connection dbConnection;

    static {
        try {
            InputStream file = new FileInputStream(new File("db.properties")) ;
            Properties props = new Properties();
            props.load(file);

            DATABASE_URL = props.getProperty("url");
            USERNAME = props.getProperty("user");
            PASSWORD = props.getProperty("password");
            DRIVER = props.getProperty("driver");

            Class.forName(DRIVER);
            dbConnection = (Connection) DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            System.out.println("Database connection successfull!");
        } catch (Exception ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
    }

    public static Connection getConnection() {
        return dbConnection;
    }
}
