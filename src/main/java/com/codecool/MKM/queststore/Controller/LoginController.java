package com.codecool.MKM.queststore.Controller;

public interface LoginController {
    String userVerification(String login, String password);
    String getUserType(String login);
}
