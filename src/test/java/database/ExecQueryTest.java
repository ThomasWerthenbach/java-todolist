package database;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

public class ExecQueryTest  {
    Connection mockConn;

    @Before
    public void setUp() {
        mockConn = mock(Connection.class);
        DatabaseConnection.setConn(mockConn);
    }

    @Test
    public void prepareTest() throws SQLException {
        ExecQuery query = new ExecQuery();
        PreparedStatement prep = mock(PreparedStatement.class);
        when(mockConn.prepareStatement(Mockito.anyString())).thenReturn(prep);

        query.prepare(ExecQuery.Query.GET_KEY);

        verify(mockConn, times(1)).prepareStatement(Mockito.anyString());
    }
}