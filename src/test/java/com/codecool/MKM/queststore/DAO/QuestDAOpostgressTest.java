package com.codecool.MKM.queststore.DAO;

import com.codecool.MKM.queststore.DAO.DBConnector.DBConnector;
import com.codecool.MKM.queststore.Model.Item;
import com.codecool.MKM.queststore.Model.Student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;


class QuestDAOpostgressTest {

    QuestDAOpostgress questDAOpostgress;

    @Mock
    StudentDAOpostgress studentDAO;
    Student student;
    DBConnector connector;
    Item item;
    Item item11;
    Item item22;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    Connection connection;
    DAO dao;
    List<Student> studentById;


    @BeforeEach
    public void initialize()  {

        connector = mock(DBConnector.class);
//        when(connector.getConnection()).thenReturn( connection );
        questDAOpostgress = spy(new QuestDAOpostgress(connector));
//        studentDAO = new StudentDAOpostgress(connector);
        connection = mock(Connection.class);
        preparedStatement = mock(PreparedStatement.class);
        resultSet = mock(ResultSet.class);
        studentDAO = mock(StudentDAOpostgress.class);
        student = mock(Student.class);
        item = mock(Item.class);
        item11 = mock(Item.class);
        item22 = mock(Item.class);
        dao =mock(DAO.class);
        studentById = mock(ArrayList.class);
    }


    @Test
    public void getQuestFromDataBaseTest ( ) throws SQLException {

        String query = "xxxx";
        Item quest = new Item( 10,"xx","yy",11);

        when(connector.getConnection()).thenReturn( connection );
        when(connection.prepareStatement(query)).thenReturn(preparedStatement);
       // when(questDAOpostgress.askDataBaseForData(query, preparedStatement)).thenReturn(resultSet);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        when(resultSet.next()).thenReturn(true, true, false);
        when(resultSet.getInt("id")).thenReturn(10);
        when(resultSet.getString("firstname")).thenReturn("xx");
        when(resultSet.getString("category")).thenReturn("yy");
        when(resultSet.getInt("price")).thenReturn(11);

        Item item = questDAOpostgress.getQuestFromDataBase(query);
        assertEquals( quest.getName(),item.getName() );

    }

    @Test
    public void getQuestsByIdTest ( ) throws SQLException {

        int[] questsId = {1,1,1 };
        Item tempitem = new Item( 10,"xx","yy",11);

        Item quest01 = mock(Item.class);
        Item quest02 = mock(Item.class);
        Item quest03 = mock(Item.class);

        List<Item> itemsTemp = new ArrayList<>();
        itemsTemp.add(quest01);
        itemsTemp.add(quest02);
        itemsTemp.add(quest03);

        when(connector.getConnection()).thenReturn( connection );
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
       // when(questDAOpostgress.askDataBaseForData(query2, preparedStatement)).thenReturn(resultSet);

        when(resultSet.next()).thenReturn(true, true,true,false);
        when(resultSet.getInt("id")).thenReturn(10);
        when(resultSet.getString("firstname")).thenReturn("xx");
        when(resultSet.getString("category")).thenReturn("yy");
        when(resultSet.getInt("price")).thenReturn(11);

        //when(questDAOpostgress.getQuestFromDataBase(query2)).thenReturn( tempitem );
//        doReturn(tempitem).when(questDAOpostgress).getQuestFromDataBase(query2);

        List<Item> itemsTests =  questDAOpostgress.getQuestsById( questsId );

        assertEquals( itemsTemp.size() , itemsTests.size());
    }


    @Test
    public void getUserQuestsTest() throws SQLException {

        int userId = 1;
        int[] studentQuestsId = {1,2,3 };

        Item quest01 = mock(Item.class);
        Item quest02 = mock(Item.class);
        Item quest03 = mock(Item.class);

        List<Item> itemsTemp = new ArrayList<>();
        itemsTemp.add(quest01);
        itemsTemp.add(quest02);
        itemsTemp.add(quest03);

        when(connector.getConnection()).thenReturn( connection );
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        when(resultSet.next()).thenReturn(true,true,false);
        when(resultSet.getInt("id")).thenReturn(10);
        when(resultSet.getString("firstname")).thenReturn("xx");
        when(resultSet.getString("category")).thenReturn("yy");
        when(resultSet.getInt("price")).thenReturn(11);

        when(studentById.get(0)).thenReturn(student);
        when(student.getQuests()).thenReturn(studentQuestsId );
        when(studentDAO.getStudentById(userId)).thenReturn(studentById);

        when(this.resultSet.getInt(1)).thenReturn(1);
        when(this.resultSet.getString(2)).thenReturn("Krzys");
        when(this.resultSet.getString(3)).thenReturn("Puchatek");
        when(this.resultSet.getString(4)).thenReturn("444-333-222");
        when(this.resultSet.getString(5)).thenReturn("mis@oe.pl");
        when(this.resultSet.getString(6)).thenReturn("{1,44,3}");
        when(this.resultSet.getString(7)).thenReturn("{61,3,4,6,5,44}");
        when(this.resultSet.getString(8)).thenReturn("{51,24,3,6,7,43}");
        when(this.resultSet.getString(9)).thenReturn("B1");
        when(this.resultSet.getInt(10)).thenReturn(100);
        when(this.resultSet.getInt(11)).thenReturn(280);

        ArrayList<Item> tests = questDAOpostgress.getUserQuests(userId);

        assertEquals( itemsTemp.size() , tests.size() );
    }

    @Test
    public void  getAllQuestsTest() throws SQLException {
        int[] questsId = {1,1,1 };
        Item tempitem = new Item( 10,"xx","yy",11);

        Item quest01 = mock(Item.class);
        Item quest02 = mock(Item.class);
        Item quest03 = mock(Item.class);

        List<Item> itemsTemp = new ArrayList<>();
        itemsTemp.add(quest01);
        itemsTemp.add(quest02);
        itemsTemp.add(quest03);

        when(connector.getConnection()).thenReturn( connection );
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        // when(questDAOpostgress.askDataBaseForData(query2, preparedStatement)).thenReturn(resultSet);

        when(resultSet.next()).thenReturn(true, true,true,false);
        when(resultSet.getInt("id")).thenReturn(10);
        when(resultSet.getString("firstname")).thenReturn("xx");
        when(resultSet.getString("category")).thenReturn("yy");
        when(resultSet.getInt("price")).thenReturn(11);

        List<Item> itemsTests =  questDAOpostgress.getAllQuests();

        assertEquals( itemsTemp.size() , itemsTests.size());
    }

    @Test
    public void  addNewQuest() throws SQLException{

        Item quest01 = mock(Item.class);

        when(connector.getConnection()).thenReturn( connection );
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        questDAOpostgress.addNewQuest(quest01);
        verify( questDAOpostgress).editDataBase(anyObject(),anyString());

    }
}