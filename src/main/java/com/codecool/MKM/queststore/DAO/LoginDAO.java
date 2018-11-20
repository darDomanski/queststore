package com.codecool.MKM.queststore.DAO;

import java.util.List;

public interface LoginDAO {

    public void removeUserFromDataBaseByLogin(String login);

    public void editUserFromDataBaseByLogin(String login);

    public List<List<String>> getUsersListFromDataBase();
}
