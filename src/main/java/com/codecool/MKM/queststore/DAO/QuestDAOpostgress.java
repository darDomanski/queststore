package com.codecool.MKM.queststore.DAO;

import com.codecool.MKM.queststore.DAO.DBConnector.DBConnector;
import com.codecool.MKM.queststore.Model.Item;
import com.codecool.MKM.queststore.DAO.StudentDAO;
import com.codecool.MKM.queststore.DAO.StudentDAOpostgress;
import com.codecool.MKM.queststore.Model.User;
import com.codecool.MKM.queststore.Model.Student;


import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QuestDAOpostgress extends DAO implements QuestDAO {

    StudentDAO student;

    public QuestDAOpostgress(DBConnector connector) {
        super(connector);
        student = new StudentDAOpostgress(connector);
    }

    public void addNewQuest(Item itemToAdd) {
        String query = "INSERT INTO quests VALUES(DEFAULT,'" + itemToAdd.getName() + "'," +
                itemToAdd.getCategory() + "'," + itemToAdd.getPrice() + ");";

        Connection connection = this.openDataBase();

        editDataBase(connection, query);
    }

    public ArrayList<Item> getAllQuests(){
        String itemsType = "quests";
        return getAllItems(itemsType);
    }

    public ArrayList<Item> getUserQuests(int userId){
        List<Student> studentById = student.getStudentById(userId);
        Student studentModel = studentById.get(0);
        int[] studentQuestsId = studentModel.getQuests();
        return getQuestsById(studentQuestsId);
    }

    public ArrayList<Item> getQuestsById(int[] questsId){
        ArrayList<Item> questsList = new ArrayList<Item>();

        for(int i=0; i<questsId.length; i++){
            String query = "SELECT * FROM quests WHERE id=" + questsId[i] + ";";
            Item quest = getQuestFromDataBase(query);
            questsList.add(quest);
        }
        return questsList;
    }

    public Item getQuestFromDataBase(String query){
        Item quest = null;
        try {
            Connection connection = this.openDataBase();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = askDataBaseForData(query, statement);

            while (result.next()) {
                quest = new Item(result.getInt("id"), result.getString("firstname"), result.getString("category"), result.getInt("price"));
            }

        } catch(SQLException e){
            e.printStackTrace();
        }
        return quest;
    }
}