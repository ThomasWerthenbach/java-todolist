package util;

import database.ExecQuery;

import java.sql.SQLException;

public class User {
    public static String name = "";

    public static String loadName() {
        try {
            ExecQuery query = new ExecQuery().Prepare(ExecQuery.Query.GET_NAME);
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
