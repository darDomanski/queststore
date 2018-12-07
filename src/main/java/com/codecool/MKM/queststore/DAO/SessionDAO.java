package com.codecool.MKM.queststore.DAO;

import com.codecool.MKM.queststore.Model.Session;

import java.util.Optional;

public interface SessionDAO {
    void addNewSession(String login, String sessionID,  String userType);
    void deleteSession(String sessionId);
    boolean checkSessionStatus(String sessionId);
    Optional<Session> getSession(String sessionId);

    void deleteSessionBylogin(String login);
}
