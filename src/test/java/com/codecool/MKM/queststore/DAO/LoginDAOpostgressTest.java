package com.codecool.MKM.queststore.DAO;

import com.codecool.MKM.queststore.DAO.DBConnector.DBConnector;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

class LoginDAOpostgressTest {
    private LoginDAOpostgress loginDAOpostgress;
    private DBConnector dbConnector;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private String query;



    @BeforeEach
    public void setup() {
        dbConnector = mock(DBConnector.class);
        connection = mock(Connection.class);
        loginDAOpostgress = new LoginDAOpostgress(dbConnector);
        preparedStatement = mock(PreparedStatement.class);
        resultSet = mock(ResultSet.class);
        query = "test";
    }

    @Test
    public void testGetUsersListFromDataBase() throws SQLException {
        when(dbConnector.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getString(1)).thenReturn("test");
        when(resultSet.getString(2)).thenReturn("test");
        when(resultSet.getString(3)).thenReturn("test");
        List<List<String>> expected = new ArrayList<>();
        List<String> user = new ArrayList<>(Arrays.asList("test", "test", "test"));
        expected.add(user);

        List<List<String>> actual = loginDAOpostgress.getUsersListFromDataBase();

        assertEquals(expected, actual);
    }

    @Test
    public void testRemoveUserFromDataBaseByLogin() throws SQLException {
        when(dbConnector.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);

        loginDAOpostgress.removeUserFromDataBaseByLogin("test");

        verify(preparedStatement).executeQuery();
    }

}