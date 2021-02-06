package database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Keeps track of the connection to the database.
 * Contains the "singleton"-design pattern to make sure only one instance of a connection to the database can exist.
 *
 * @author Thomas Werthenbach
 */
public class DatabaseConnection {
    /**
     * The connection to the database.
     */
    private static Connection conn = null;

    /**
     * Creates the connection to the database when it is not yet present.
     * Will create a database in src/main/resources when not yet present.
     * @return the connection to the database.
     */
    public static Connection getConn() {
        if (conn == null) {
            String pathToResources = new File("src/main/resources").getAbsolutePath();
            try {
                if (!(pathToResources.endsWith("\\") || pathToResources.endsWith("/")))
                    if (System.getProperty("os.name").toLowerCase().contains("windows"))
                        pathToResources = pathToResources + "\\";
                    else
                        pathToResources = pathToResources + "/";
                conn = DriverManager.getConnection("jdbc:sqlite:" + pathToResources +  "database.db");
                createTables();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    /**
     * Will attempt to create all the tables if they do not yet exist.
     */
    private static void createTables() {
        try {
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS def (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "key text," +
                    "value text" +
                    ")");
            stmt.execute("CREATE TABLE IF NOT EXISTS todos (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "description text not null," +
                    "deadline date" +
                    ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Is used to set the Connection object. SHOULD ONLY BE USED FOR TESTING PURPOSES.
     * @param connection the new connection object.
     */
    public static void setConn(Connection connection) {
        conn = connection;
    }
}
