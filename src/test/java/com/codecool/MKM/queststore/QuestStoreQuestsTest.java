package com.codecool.MKM.queststore;

import com.codecool.MKM.queststore.Controller.*;
import com.codecool.MKM.queststore.DAO.DBConnector.DBConnector;
import com.codecool.MKM.queststore.DAO.QuestDAOpostgress;
import com.codecool.MKM.queststore.Helpers.CookieHelper;
import com.codecool.MKM.queststore.Model.Item;
import com.codecool.MKM.queststore.Model.Student;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;

import java.io.IOException;
import java.net.HttpCookie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static junit.framework.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

class QuestStoreQuestsTest {

    String SESSION_COOKIE_NAME = "tom";
    int idItem = 1;
    String nameItem = "cart1";
    String categoryItem = "basic";
    int priceItem = 100;

    QuestStoreQuests questStoreQuests;

    @Mock
    DBConnector connector;
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    ResultSet resultSet2;
    HttpExchange httpExchange;
    CookieHelper cookieHelper;
    Headers headers;
//    Optional<HttpCookie> cookie;
    SessionController session;
    StoreController questStore;
    List<Item> quests;
    List<String> categories;
    StudentController studentController;
    List<Student> studentsList;
    //    Optional<Student>student;
    Map<String, String> questsPictures;
    Map<String, String> questsDescriptions;
    JtwigTemplate template;
    JtwigModel model;

    @BeforeEach
    public void initialize()  {

        connector = mock(DBConnector.class);
        connection = mock(Connection.class);
        questStoreQuests = new QuestStoreQuests( connector );
        preparedStatement = mock(PreparedStatement.class);
        resultSet = mock(ResultSet.class);
        resultSet2 = mock(ResultSet.class);
        httpExchange = mock(HttpExchange.class);
        cookieHelper = mock(CookieHelper.class);
        headers = mock(Headers.class);
//        cookie = mock(Optional.class);
        session = mock(BasicSessionController.class);
        questStore = mock(BasicStoreController.class);
        quests = mock(ArrayList.class);
        categories =  mock(ArrayList.class);
        studentController = mock( BasicStudentController.class);

        studentsList = mock(ArrayList.class );
//        student = mock(Optional.class);
        questsPictures = mock( Map.class );
        questsDescriptions = mock( Map.class );
        template = mock(JtwigTemplate.class);
        model = mock(JtwigModel.class);

    }


    @Test
    public void handleTest( ) throws IOException, SQLException {

        Optional<Student> student = Optional.ofNullable(studentsList.get(0));
        int coolcoins = 100;

        when( connector.getConnection()).thenReturn( connection );
        when( connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when( preparedStatement.executeQuery()).thenReturn(resultSet);
        when( resultSet.next()).thenReturn(true,false).thenReturn(true,false);
//        when( resultSet2.next()).thenReturn(true,false);

        when( httpExchange.getRequestMethod()).thenReturn("GET");
//        when( session.isSessionActive("xxx")).thenReturn(true);
//        when( session.getUserLogin( "xxx" )).thenReturn("yyy");
        when( questStore.getAllQuests()). thenReturn( quests );
        when( questStore.getAllCategories(quests) ).thenReturn(categories );
        when( httpExchange.getRequestHeaders()).thenReturn(headers);
        when( headers.getFirst(anyString())).thenReturn("sessionId=xxx");
//        when( studentController.getStudentByName(anyString())).thenReturn(student);

//        when( student.get().getWallet() ).thenReturn(111);

//        when( questStore.getQuestsPictures()).thenReturn(questsPictures  );
//        when( questStore.getQuestsDescriptions()).thenReturn(questsDescriptions );
//        when( questStore.getProfilePicture("xx")).thenReturn("yy");

//        when( JtwigTemplate.classpathTemplate("templates/store/quests.twig")).thenReturn(template);
//        when( template.render(model)  );

        questStoreQuests.handle( httpExchange );
        verify( httpExchange ).sendResponseHeaders(303, 0);

        }

}