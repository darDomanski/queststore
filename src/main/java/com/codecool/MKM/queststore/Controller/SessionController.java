package com.codecool.MKM.queststore.Controller;

public interface SessionController {
    void addNewSessionToDB(String login, String sessionID,  String userType);
    void deleteSessionFromDB(String sessionId);
    boolean isSessionActive(String sessionId);
}
