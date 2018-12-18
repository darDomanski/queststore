package com.codecool.MKM.queststore.DAO;

import com.codecool.MKM.queststore.DAO.DBConnector.DBConnector;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class LoginDAOpostgress extends DAO implements LoginDAO {

    private String getUsersQuery = "SELECT * FROM login;";

    public LoginDAOpostgress(DBConnector connector) {
        super(connector);
    }


    public List<List<String>> getUsersListFromDataBase() {
        Connection connection = this.openDataBase();
        ResultSet result = null;
        PreparedStatement statement = null;

        String query = getUsersQuery;

        try {
            statement = connection.prepareStatement(query);

            result = askDataBaseForData(getUsersQuery, statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<List<String>> usersList = new ArrayList<List<String>>();

        try {
            while (result.next()) {
                List<String> recordsPropertiesList = new ArrayList<String>();

                recordsPropertiesList.add(result.getString(1));
                recordsPropertiesList.add(result.getString(2));
                recordsPropertiesList.add(result.getString(3));
                usersList.add(recordsPropertiesList);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        closeStatementAndConnection(connection,statement);
        return usersList;
    }



    public void removeUserFromDataBaseByLogin(String login) {

        String query = createQueryToRemoveUserByLogin(login);
        Connection connection = this.openDataBase();

        try {

            PreparedStatement statement = connection.prepareStatement(query);
            askDataBaseForData(getUsersQuery, statement);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    private String createQueryToRemoveUserByLogin(String login) {
        return "DELETE * FROM login WHERE nickname=" + login + ";";
    }



    public void editUserFromDataBaseByLogin(String login) {
        //todo
    }

}
