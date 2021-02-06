package util;

import database.ExecQuery;

import java.sql.SQLException;
import java.time.LocalDate;

/**
 * This class symbolises the todo-items that the user submitted.
 *
 * @author Thomas Werthenbach
 */
public class Todo {
    /**
     * The description of the todoitem.
     */
    private String description = null;

    /**
     * The deadline of the todoitem.
     */
    private LocalDate deadline = null;

    /**
     * Sets the deadline of the todoitem.
     * @param deadline the deadline of the todoitem.
     */
    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    /**
     * Sets the description of the todoitem.
     * @param description the description of the todoitem.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the description of the todoitem.
     * @return the description of the todoitem.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * This method stores a todoitem in the database.
     * @return the id value of the newly created todoitem.
     */
    public int storeTodo() {
        try {
            ExecQuery query = new ExecQuery()
                                .prepare(ExecQuery.Query.STORE_TODO)
                                .setParam(this.description);
            if (this.deadline != null)
                query.setParam(this.deadline);
            else
                query.setParamNull();
            query.execute();

            return query.prepare(ExecQuery.Query.GET_LATEST_TODO).executeQuery().getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // Failed
        }
    }
}
