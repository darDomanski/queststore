package com.codecool.MKM.queststore.Controller;

public interface LoginController {
    boolean areCredentialsCorrect(String login, String password);
    String getUserType(String login);
}
