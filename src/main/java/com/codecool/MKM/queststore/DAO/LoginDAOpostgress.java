package com.codecool.MKM.queststore.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class LoginDAOpostgress extends DAO implements LoginDAO {

    private String getUsersQuery = "SELECT * FROM login;";




    public List<List<String>> getUsersListFromDataBase() {

        Connection connection = this.openDataBase();

        Statement statement = getStatement(connection);

        ResultSet result = askDataBaseForData(getUsersQuery, statement);

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

        Statement statement = getStatement(connection);

        askDataBaseForData(getUsersQuery, statement);
    }


    private String createQueryToRemoveUserByLogin(String login) {
        return "DELETE * FROM login WHERE nickname=" + login + ";";
    }



    public void editUserFromDataBaseByLogin(String login) {
        //todo
    }

}
