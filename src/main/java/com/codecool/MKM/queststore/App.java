package com.codecool.MKM.queststore;


import com.codecool.MKM.queststore.DAO.LoginDAOpostgress;

public class App
{
    public static void main( String[] args ) {


        LoginDAOpostgress DAO = new LoginDAOpostgress();
        DAO.getUsersListFromDataBase();
    }
}
