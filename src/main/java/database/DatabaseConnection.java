package database;

import main.Main;

import java.io.File;
import java.sql.*;

public class DatabaseConnection {
    private static Connection conn = null;

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
//                DatabaseMetaData meta = conn.getMetaData();
                createTables();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    private static void createTables() {
        try {
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS def (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "key text," +
                    "value text" +
                    ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
