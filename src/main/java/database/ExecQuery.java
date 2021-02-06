package database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;

/**
 * Will manage all query's executed on the database.
 * Makes use of the "builder"-design pattern to ease development processes and improve code styling.
 *
 * @author Thomas Werthenbach
 */
public class ExecQuery {
    /**
     * Will be or is being executed on the database.
     */
    private PreparedStatement prep;

    /**
     * Is the ResultSet returned by a executeQuery() call.
     */
    private ResultSet rs;

    /**
     * Keeps track of which parameter is to be filled in a PreparedStatement.
     */
    private int index;

    /**
     * Contains an element for every query that can be executed by this class.
     */
    public enum Query {
        STORE_KEY,
        GET_KEY,
        STORE_TODO,
        GET_LATEST_TODO,
        GET_ALL_TODOS
    }

    /**
     * Contains pairs of Query-elements and Strings, which are used to link the elements of the Query enum to their
     * corresponding query's.
     */
    private static final HashMap<Query, String> queryHashMap = new HashMap<>();

    static {
        queryHashMap.put(Query.STORE_KEY, "INSERT INTO def (key, value) VALUES (?, ?);");
        queryHashMap.put(Query.GET_KEY, "SELECT value as name, count(*) as rowcount from def where key=?;");
        queryHashMap.put(Query.STORE_TODO, "INSERT INTO todos (description, deadline) VALUES (?, ?);");
        queryHashMap.put(Query.GET_LATEST_TODO, "select max(id) as id from todos;");
        queryHashMap.put(Query.GET_ALL_TODOS, "select * from todos;");
    }

    /**
     * Will prepare the given query.
     * Prepared statements's are query's that are protected against sql injection, which may be attempted by malicious
     * users.
     * @param query is to be prepared.
     * @return this current ExecQuery. (See Builder-design pattern)
     * @throws SQLException may be thrown when the query contains a syntax error.
     */
    public ExecQuery prepare(Query query) throws SQLException {
        this.prep = DatabaseConnection.getConn().prepareStatement(queryHashMap.get(query));
        this.index = 1;
        return this;
    }

    /**
     * Is used to set a String parameter in the prepared statement.
     * @param val is to be included in the prepared statement.
     * @return this current ExecQuery. (See Builder-design pattern)
     * @throws SQLException may be thrown when the parameter could not be included in the prepared statement.
     */
    public ExecQuery setParam(String val) throws SQLException {
        this.prep.setString(index++, val);
        return this;
    }

    /**
     * Is used to set a LocalDate parameter in the prepared statement,
     * @param val is to be included in the prepared statement.
     * @return this current ExecQuery. (See Builder-design pattern)
     * @throws SQLException may be thrown when the parameter could not be included in the prepared statement.
     */
    public ExecQuery setParam(LocalDate val) throws SQLException {
        this.prep.setDate(index++, Date.valueOf(val));
        return this;
    }

    /**
     * Is used to set a null value in the prepared statement,
     * @return this current ExecQuery. (See Builder-design pattern)
     * @throws SQLException may be thrown when the null value could not be included in the prepared statement.
     */
    public ExecQuery setParamNull() throws SQLException {
        this.prep.setNull(index++, java.sql.Types.NULL);
        return this;
    }

    /**
     * Is used to execute a prepared statement on the database.
     * @return this current ExecQuery. (See Builder-design pattern)
     * @throws SQLException may be thrown when the prepared statement could not be executed.
     */
    public ExecQuery execute() throws SQLException {
        this.prep.execute();
        return this;
    }

    /**
     * Is used to execute a prepared statement, which expects a result, on the database.
     * @return this current ExecQuery. (See Builder-design pattern)
     * @throws SQLException may be thrown when the prepared statement could not be executed.
     */
    public ExecQuery executeQuery() throws SQLException {
        this.rs = this.prep.executeQuery();
        return this;
    }

    /**
     * Is used to retrieve a String value from the query result.
     * @param key is the key to which the value in the ResultSet should correspond.
     * @return the String value from the query result.
     * @throws SQLException may be thrown when the value could not be extracted from the query result.
     */
    public String getString(String key) throws SQLException {
        return this.rs.getString(key);
    }

    /**
     * Is used to retrieve a integer value from the query result.
     * @param key is the key to which the value in the ResultSet should correspond.
     * @return the integer value from the query result.
     * @throws SQLException may be thrown when the value could not be extracted from the query result.
     */
    public int getInt(String key) throws SQLException {
        return this.rs.getInt(key);
    }

    /**
     * Is used to retrieve a LocalDate value from the query result.
     * @param key is the key to which the value in the ResultSet should correspond.
     * @return the LocalDate value from the query result.
     * @throws SQLException may be thrown when the value could not be extracted from the query result.
     */
    public LocalDate getDate(String key) throws SQLException {
        return this.rs.getDate(key).toLocalDate();
    }
}
