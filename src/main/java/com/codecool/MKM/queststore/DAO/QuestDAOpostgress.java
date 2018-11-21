package com.codecool.MKM.queststore.DAO;

import com.codecool.MKM.queststore.Model.Quest;

import java.sql.Connection;
import java.sql.Statement;

public class QuestDAOpostgress extends DAO implements QuestDAO {

    public void addNewQuest(Quest questToAdd) {
        String query = "INSERT INTO quests VALUES(DEFAULT,'" + questToAdd.getName() + "'," +
                questToAdd.getCategory() + "'," + questToAdd.getPrice() + ");";

        Connection connection = this.openDataBase();
        Statement statement = getStatement(connection);

        editDataBase(query, connection, statement);
    }
}
