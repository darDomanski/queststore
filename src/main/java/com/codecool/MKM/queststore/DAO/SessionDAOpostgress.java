package com.codecool.MKM.queststore.DAO;

import com.codecool.MKM.queststore.DAO.DBConnector.DBConnector;
import com.codecool.MKM.queststore.Model.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class SessionDAOpostgress extends DAO implements SessionDAO{
    public SessionDAOpostgress(DBConnector connector) {
        super(connector);
    }

    public void addNewSession(String login, String sessionID, String userType) {
        String query = "INSERT INTO active_sessions (sessionid, userlogin, usertype) " +
                "VALUES('" + sessionID + "', '" + login + "', '" + userType +"');";
        executeQuery(query);
    }

    @Override
    public void deleteSessionBylogin(String login) {
        String query = "DELETE FROM active_sessions " +
                "WHERE userlogin LIKE '" + login + "'";
        executeQuery(query);
    }

    public void deleteSession(String sessionId) {
        String query = "DELETE FROM active_sessions " +
                "WHERE sessionid LIKE '" + sessionId + "'";
        executeQuery(query);
    }

    @Override
    public boolean checkSessionStatus(String sessionId) {
        boolean isActive = false;

        String query = "SELECT * FROM active_sessions " +
                "WHERE sessionid LIKE '" + sessionId + "';";
        Connection connection = openDataBase();

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = askDataBaseForData(query, statement);

            if(result.next()){
                isActive = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isActive;

    }


    @Override
    public Optional<Session> getSession(String sessionId) {
        String query = "SELECT * FROM active_sessions " +
                "WHERE sessionid LIKE '"+ sessionId +"'";
        Connection connection = openDataBase();
        Optional<Session> activity = Optional.empty();

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = askDataBaseForData(query, statement);

            if(result.next()){
                String login = result.getString("userlogin");
                String userType = result.getString("usertype");
                activity = Optional.of(new Session(login, sessionId, userType));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return activity;
    }

}