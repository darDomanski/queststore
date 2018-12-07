package com.codecool.MKM.queststore.DAO;

import com.codecool.MKM.queststore.Model.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class DAO {

    public Connection openDataBase() {
        Connection c = null;
        try {
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/QuestStore",
                            "mihuadmin", "lokas777");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
        return c;
    }

    protected void closeStatementAndConnection(Connection connection, Statement statement) {
        try{
            connection.close();
            statement.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }


    protected Statement getStatement(Connection connection) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }



    protected ResultSet askDataBaseForData(String query, PreparedStatement statement) {

        ResultSet result = null;
        try {
            result = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    protected void editDataBase(Connection connection, String query) {

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();
            closeStatementAndConnection(connection, statement);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Data base error - check Your internet connection or try later!");
        }
    }

    protected ArrayList<Item> getAllItems(String itemsType){
        String query = "SELECT * FROM "+ itemsType;
        Connection connection = this.openDataBase();

        List<Item> itemsList = new ArrayList<Item>();

        try {
            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                itemsList.add(new Item(rs.getInt("id"),rs.getString("firstname"),rs.getString("category"),rs.getInt("price")));
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return (ArrayList<Item>) itemsList;
    }

    protected void executeQuery(String query) {
        Connection connection = openDataBase();

        try {

            PreparedStatement statement = connection.prepareStatement(query);

            statement.executeUpdate();

            closeStatementAndConnection(connection, statement);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
