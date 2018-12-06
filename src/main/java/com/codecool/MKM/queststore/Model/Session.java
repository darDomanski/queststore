package com.codecool.MKM.queststore.Model;


public class Session {

    private String userLogin;
    private String sessionId;
    private String userType;

    public Session(String userLogin, String sessionId, String userType) {
        this.userLogin = userLogin;
        this.sessionId = sessionId;
        this.userType = userType;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
