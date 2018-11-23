package com.codecool.MKM.queststore.DAO;

import com.codecool.MKM.queststore.Model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

public class ArtifactsSingleDAOpostgress extends DAO implements ArtifactsGroupsDAO {

    public void addNewArtifact(Item itemToAdd) {
        String query = "INSERT INTO artifacts VALUES(DEFAULT,'" + itemToAdd.getName() + "'," +
                itemToAdd.getCategory() + "'," + itemToAdd.getPrice() + ");";

        Connection connection = this.openDataBase();

        editDataBase(connection, query);
    }

    public ArrayList<Item> getAllArtifacts(){
        String itemsType = "artifacts";
        return getAllItems(itemsType);
    }
}
