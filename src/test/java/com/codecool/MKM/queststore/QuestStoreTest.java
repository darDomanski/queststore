package com.codecool.MKM.queststore;

import com.codecool.MKM.queststore.Controller.SessionController;
import com.codecool.MKM.queststore.Controller.StoreController;
import com.codecool.MKM.queststore.Controller.StudentController;
import com.codecool.MKM.queststore.DAO.DBConnector.DBConnector;
import com.codecool.MKM.queststore.Helpers.CookieHelper;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.net.HttpCookie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

class QuestStoreTest {

    private QuestStore questStore;


    @Mock
    private CookieHelper cookieHelper;
    //private HttpCookie httpCookie;
    //private SessionController sessionController;
    //private StoreController storeController;
    //private StudentController studentController;
    private DBConnector connector;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private HttpExchange httpExchange;
    private ResultSet resultSet;
    private Headers headers;
    //private Optional<HttpCookie> optionalCookie;

    @BeforeEach
    public void setUp() throws SQLException {

        this.cookieHelper = mock(CookieHelper.class);
        //this.optionalCookie = Optional.ofNullable(null);
        //this.sessionController = mock(SessionController.class);
        //this.storeController = mock(StoreController.class);
        //this.studentController = mock(StudentController.class);
        this.connector = mock(DBConnector.class);
        this.connection = mock(Connection.class);
        this.preparedStatement = mock(PreparedStatement.class);
        this.httpExchange = mock(HttpExchange.class);
        this.resultSet = mock(ResultSet.class);
        this.headers = mock(Headers.class);
        this.questStore = new QuestStore(connector);
    }

    @Test
    public void testHandle() throws IOException, SQLException {

        arrangeMocks();

        when(this.headers.getFirst(anyString())).thenReturn("sessionId=xxx");
        when(this.httpExchange.getRequestHeaders()).thenReturn(this.headers);
        when(this.httpExchange.getRequestMethod()).thenReturn("GET");

        when(this.connector.getConnection()).thenReturn(this.connection);
        when(this.connection.prepareStatement(anyString())).thenReturn(this.preparedStatement);
        when(this.preparedStatement.executeQuery()).thenReturn(this.resultSet);
        when(this.resultSet.next()).thenReturn(true, false);

        when(this.resultSet.getInt(1)).thenReturn(1);
        when(this.resultSet.getString(2)).thenReturn("Krzys");
        when(this.resultSet.getString(3)).thenReturn("Puchatek");
        when(this.resultSet.getString(4)).thenReturn("444-333-222");
        when(this.resultSet.getString(5)).thenReturn("mis@oe.pl");
        when(this.resultSet.getString(6)).thenReturn("{1,44,3,6,7,54}");
        when(this.resultSet.getString(7)).thenReturn("{61,3,4,6,5,44}");
        when(this.resultSet.getString(8)).thenReturn("{51,24,3,6,7,43}");
        when(this.resultSet.getString(9)).thenReturn("B1");
        when(this.resultSet.getInt(10)).thenReturn(100);
        when(this.resultSet.getInt(11)).thenReturn(280);


        this.questStore.handle(this.httpExchange);
    }

    private void arrangeMocks() {

        //when(this.headers.getFirst(anyString())).thenReturn("sessionId=xxx");
        //when(this.httpExchange.getRequestMethod()).thenReturn("GET");
        //when(this.httpExchange.getResponseHeaders()).thenReturn(this.headers);
        //when(this.cookieHelper.getSessionIdCookie(anyObject(), anyString())).thenReturn(this.optionalCookie);
    }

}