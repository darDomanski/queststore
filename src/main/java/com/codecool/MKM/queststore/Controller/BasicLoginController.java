package com.codecool.MKM.queststore.Controller;

import com.codecool.MKM.queststore.DAO.LoginDAO;
import com.codecool.MKM.queststore.DAO.LoginDAOpostgress;

import java.util.List;

public class BasicLoginController implements LoginController {

    LoginDAO loginDAO = new LoginDAOpostgress();

    @Override
    public String areCredentialsCorrect(String login, String password) {
        List<List<String>> listOfUsers = loginDAO.getUsersListFromDataBase();

        String typeOfUser = "";


        for (List<String> user : listOfUsers) {
            if (user.get(0).equals(login) && user.get(1).equals(password)) {
                typeOfUser = user.get(2);
            }
        }
        return typeOfUser;
    }

    @Override
    public String getUserType(String login) {
        return null;
    }
}

