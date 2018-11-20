package com.codecool.MKM.queststore.DAO;

import java.sql.*;

public abstract class DAO {

    public Connection openDataBase() {
        Connection c = null;
        try {
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/QuestStore",
                            "Krzysztof", "jebacserwery");
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



    protected ResultSet askDataBaseForData(String query, Connection connection, Statement statement) {

        ResultSet result = null;
        try {
            result = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    protected void editDataBase(String query, Connection connection, Statement statement) {

        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
