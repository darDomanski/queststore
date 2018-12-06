package com.codecool.MKM.queststore.Controller;

import com.codecool.MKM.queststore.DAO.LoginDAO;
import com.codecool.MKM.queststore.DAO.LoginDAOpostgress;

import java.util.List;

public class BasicLoginController implements LoginController {

    LoginDAO loginDAO = new LoginDAOpostgress();

    @Override
    public String getUserType(String login) {
        List<List<String>> listOfUsers = loginDAO.getUsersListFromDataBase();

        String typeOfUser = "";


        for (List<String> user : listOfUsers) {
            if (user.get(0).equals(login)) {
                typeOfUser = user.get(2);
            }
        }
        return typeOfUser;
    }

    @Override
    public boolean areCredentialsCorrect(String login, String password) {
        List<List<String>> listOfUsers = loginDAO.getUsersListFromDataBase();

        Boolean isCorrect = false;


        for (List<String> user : listOfUsers) {
            if (user.get(0).equals(login) && user.get(1).equals(password)) {
                isCorrect = true;
            }
        }
        return isCorrect;
    }
}

