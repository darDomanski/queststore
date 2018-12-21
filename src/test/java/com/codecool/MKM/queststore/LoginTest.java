package com.codecool.MKM.queststore;

import com.codecool.MKM.queststore.DAO.DBConnector.DBConnector;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

class LoginTest {
    private HttpExchange httpExchange;
    private Headers headers;
    private DBConnector dbConnector;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Login login;
    private OutputStream outputStream;
    private InputStream inputStream;

    @BeforeEach
    public void setup() {
        httpExchange = mock(HttpExchange.class);
        headers = mock(Headers.class);
        dbConnector = mock(DBConnector.class);
        connection = mock(Connection.class);
        preparedStatement = mock(PreparedStatement.class);
        resultSet = mock(ResultSet.class);
        outputStream = mock(OutputStream.class);
        inputStream = new ByteArrayInputStream("name=x&password=x".getBytes());
        login = new Login(dbConnector);
    }


    @Test
    public void testHandleGetMethodWhenSessionIsActiveThenSendRedirectResponse() throws SQLException, IOException {
        when(httpExchange.getRequestMethod()).thenReturn("GET");
        when(httpExchange.getRequestHeaders()).thenReturn(headers);
        when(httpExchange.getResponseHeaders()).thenReturn(headers);
        when(httpExchange.getResponseBody()).thenReturn(outputStream);
        when(headers.getFirst(anyString())).thenReturn("sessionId=xxx;");
        when(dbConnector.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);

        login.handle(httpExchange);

        verify(httpExchange).sendResponseHeaders(303, 0);
    }

    @Test
    public void testHandleGetMethodWhenSessionIsNotActiveThenSend200Response() throws SQLException, IOException {
        when(httpExchange.getRequestMethod()).thenReturn("GET");
        when(httpExchange.getRequestHeaders()).thenReturn(headers);
        when(httpExchange.getResponseHeaders()).thenReturn(headers);
        when(httpExchange.getResponseBody()).thenReturn(outputStream);
        when(headers.getFirst(anyString())).thenReturn("sessionId=xxx;");
        when(dbConnector.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false);

        login.handle(httpExchange);

        verify(httpExchange).sendResponseHeaders(200, 0);
    }

    @Test
    public void testHandlePostMethodWhenCredentialAreCorrectUserIsStudentThenSendRedirectResponse() throws SQLException, IOException {
        ResultSet sessionResultSet = mock(ResultSet.class);
        ResultSet credentialsResultSet = mock(ResultSet.class);
        when(httpExchange.getRequestMethod()).thenReturn("POST");
        when(httpExchange.getRequestBody()).thenReturn(inputStream);
        when(httpExchange.getResponseHeaders()).thenReturn(headers);
        when(httpExchange.getRequestHeaders()).thenReturn(headers);
        when(headers.getFirst(anyString())).thenReturn("sessionId=xxx");
        when(httpExchange.getResponseBody()).thenReturn(outputStream);
        when(dbConnector.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet, credentialsResultSet, sessionResultSet);
        when(preparedStatement.executeUpdate()).thenReturn(0);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getString(1)).thenReturn("x");
        when(resultSet.getString(2)).thenReturn("x");
        when(resultSet.getString(3)).thenReturn("student");
        when(sessionResultSet.next()).thenReturn(false);
        when(credentialsResultSet.next()).thenReturn(true, false);
        when(credentialsResultSet.getString(1)).thenReturn("x");
        when(credentialsResultSet.getString(2)).thenReturn("x");
        when(credentialsResultSet.getString(3)).thenReturn("student");

        login.handle(httpExchange);

        verify(httpExchange).sendResponseHeaders(303, 0);
    }

    @Test
    public void testHandlePostMethodWhenCredentialAreCorrectUserIsMentorThenSendRedirectResponse() throws SQLException, IOException {
        ResultSet sessionResultSet = mock(ResultSet.class);
        ResultSet credentialsResultSet = mock(ResultSet.class);
        when(httpExchange.getRequestMethod()).thenReturn("POST");
        when(httpExchange.getRequestBody()).thenReturn(inputStream);
        when(httpExchange.getResponseHeaders()).thenReturn(headers);
        when(httpExchange.getRequestHeaders()).thenReturn(headers);
        when(headers.getFirst(anyString())).thenReturn("sessionId=xxx");
        when(httpExchange.getResponseBody()).thenReturn(outputStream);
        when(dbConnector.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet, credentialsResultSet, sessionResultSet);
        when(preparedStatement.executeUpdate()).thenReturn(0);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getString(1)).thenReturn("x");
        when(resultSet.getString(2)).thenReturn("x");
        when(resultSet.getString(3)).thenReturn("mentor");
        when(sessionResultSet.next()).thenReturn(false);
        when(credentialsResultSet.next()).thenReturn(true, false);
        when(credentialsResultSet.getString(1)).thenReturn("x");
        when(credentialsResultSet.getString(2)).thenReturn("x");
        when(credentialsResultSet.getString(3)).thenReturn("mentor");

        login.handle(httpExchange);

        verify(httpExchange).sendResponseHeaders(303, 0);
    }

    @Test
    public void testHandlePostMethodWhenCredentialAreCorrectUserIsCreepyGuyThenSendRedirectResponse() throws SQLException, IOException {
        ResultSet sessionResultSet = mock(ResultSet.class);
        ResultSet credentialsResultSet = mock(ResultSet.class);
        when(httpExchange.getRequestMethod()).thenReturn("POST");
        when(httpExchange.getRequestBody()).thenReturn(inputStream);
        when(httpExchange.getResponseHeaders()).thenReturn(headers);
        when(httpExchange.getRequestHeaders()).thenReturn(headers);
        when(headers.getFirst(anyString())).thenReturn("sessionId=xxx");
        when(httpExchange.getResponseBody()).thenReturn(outputStream);
        when(dbConnector.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet, credentialsResultSet, sessionResultSet);
        when(preparedStatement.executeUpdate()).thenReturn(0);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getString(1)).thenReturn("x");
        when(resultSet.getString(2)).thenReturn("x");
        when(resultSet.getString(3)).thenReturn("creepy");
        when(sessionResultSet.next()).thenReturn(false);
        when(credentialsResultSet.next()).thenReturn(true, false);
        when(credentialsResultSet.getString(1)).thenReturn("x");
        when(credentialsResultSet.getString(2)).thenReturn("x");
        when(credentialsResultSet.getString(3)).thenReturn("creepy");

        login.handle(httpExchange);

        verify(httpExchange).sendResponseHeaders(303, 0);
    }


    @Test
    public void testHandlePostMethodWhenCredentialAreNotCorrectThenSend200Response() throws SQLException, IOException {
        when(httpExchange.getRequestMethod()).thenReturn("POST");
        when(httpExchange.getRequestBody()).thenReturn(inputStream);
        when(httpExchange.getResponseHeaders()).thenReturn(headers);
        when(httpExchange.getRequestHeaders()).thenReturn(headers);
        when(headers.getFirst(anyString())).thenReturn("sessionId=xxx");
        when(httpExchange.getResponseBody()).thenReturn(outputStream);
        when(dbConnector.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(preparedStatement.executeUpdate()).thenReturn(0);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getString(1)).thenReturn("x");
        when(resultSet.getString(2)).thenReturn("x");
        when(resultSet.getString(3)).thenReturn("student");

        login.handle(httpExchange);

        verify(httpExchange).sendResponseHeaders(200, 0);
    }

}