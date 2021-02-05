package util;

import database.ExecQuery;

import java.sql.SQLException;

/**
 * Symbolises the user that is currently logged in and contains functionality related to the user.
 *
 * @Author Thomas Werthenbach
 */
public class User {
    /**
     * The name of the current user.
     */
    public static String name = "";

    /**
     * Loads the name of the current user from the database, if present.
     * @return the current user's name, if stored. Returns null otherwise.
     */
    public static String loadName() {
        try {
            ExecQuery query = new ExecQuery().prepare(ExecQuery.Query.GET_KEY);
            query.executeQuery();
            if (query.getInt("rowcount") > 0) {
                name = query.getString("name");
                return name;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
