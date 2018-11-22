package com.codecool.MKM.queststore.DAO;

import com.codecool.MKM.queststore.Model.Quest;

import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QuestDAOpostgress extends DAO implements QuestDAO {

    public void addNewQuest(Quest questToAdd) {
        String query = "INSERT INTO quests VALUES(DEFAULT,'" + questToAdd.getName() + "'," +
                questToAdd.getCategory() + "'," + questToAdd.getPrice() + ");";

        Connection connection = this.openDataBase();
        Statement statement = getStatement(connection);

        editDataBase(query, connection, statement);
    }

    public ArrayList<Quest> getAllQuests(){
        String query = "SELECT * FROM quests";
        Connection connection = this.openDataBase();

        List<Quest> questsList = new ArrayList<Quest>();

        try {
            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                questsList.add(new Quest(rs.getInt("id"),rs.getString("firstname"),rs.getString("category"),rs.getInt("price")));
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return (ArrayList<Quest>) questsList;
    }
}
