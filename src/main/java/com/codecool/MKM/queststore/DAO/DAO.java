package com.codecool.MKM.queststore.DAO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public abstract class DAO {

    public Connection openDataBase() {
        Connection c = null;
        String[] logData = getDataBaseLogData("/home/k/codecool/Web/Quest Store/queststore/lib/dataBaseLoginData.csv");
        try {
            c = DriverManager
                    .getConnection(logData[0],
                            logData[1], logData[2]);
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
            System.out.println("Data base error - check Your internet connection or try later!");
        }
        closeStatementAndConnection(connection, statement);
    }

    private String[] getDataBaseLogData(String filePath) {

        FileReader fileReader = null;
        try {
            fileReader = new FileReader(filePath);
        } catch(FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("You havent't file with data for login to data base!");
        }
        BufferedReader bf = new BufferedReader(fileReader);
        String data = "";
        try{
            data = bf.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("File is incorrect!");
        }
        return data.split(",");
    }

}
