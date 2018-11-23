package com.codecool.MKM.queststore.DAO;

import com.codecool.MKM.queststore.Model.Item;

import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QuestDAOpostgress extends DAO implements QuestDAO {

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

    public ArrayList<Item> getUserQuests(){

    }
}
