package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class ExecQuery {
    private PreparedStatement prep;
    private ResultSet rs;
    private int index;

    public enum Query {
        STORE_NAME,
        GET_NAME
    }

    private static final HashMap<Query, String> queryHashMap = new HashMap<>();

    static {
        queryHashMap.put(Query.STORE_NAME, "INSERT INTO def (key, value) VALUES(?, ?);");
        queryHashMap.put(Query.GET_NAME, "SELECT value as name, count(*) as rowcount from def where key='name'");
    }

    public ExecQuery Prepare(Query query) throws SQLException {
        this.prep = DatabaseConnection.getConn().prepareStatement(queryHashMap.get(query));
        this.index = 1;
        return this;
    }

    public ExecQuery setParam(int val) throws SQLException {
        this.prep.setInt(index++, val);
        return this;
    }

    public ExecQuery setParam(String val) throws SQLException {
        this.prep.setString(index++, val);
        return this;
    }

    public void execute() throws SQLException {
        this.prep.execute();
    }

    public void executeQuery() throws SQLException {
        rs = this.prep.executeQuery();
    }

    public String getString(String key) throws SQLException {
        return this.rs.getString(key);
    }

    public int getInt(String key) throws SQLException {
        return this.rs.getInt(key);
    }
}
