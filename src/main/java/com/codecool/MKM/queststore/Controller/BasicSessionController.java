package com.codecool.MKM.queststore.Controller;

import com.codecool.MKM.queststore.DAO.SessionDAO;
import com.codecool.MKM.queststore.DAO.SessionDAOpostgress;

public class BasicSessionController implements SessionController{
    SessionDAO sessionDAO = new SessionDAOpostgress();
    @Override
    public void addNewSessionToDB(String login, String sessionID,  String userType){
            sessionDAO.addNewSession(login, sessionID, userType);
            System.out.println("added!");
    }

    @Override
    public void deleteSessionFromDB(String sessionId) {
            sessionDAO.deleteSession(sessionId);
            System.out.println("deleted");
    }

    @Override
    public boolean isSessionActive(String sessionId) {
            boolean isActive = sessionDAO.checkSessionStatus(sessionId);

            return isActive;
    }
}
