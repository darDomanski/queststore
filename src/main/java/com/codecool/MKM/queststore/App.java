package com.codecool.MKM.queststore;


import com.codecool.MKM.queststore.Controller.BasicLoginController;
import com.codecool.MKM.queststore.Controller.MainController;
import com.codecool.MKM.queststore.DAO.LoginDAOpostgress;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class App
{
    public static void main( String[] args ) {


//        LoginDAOpostgress DAO = new LoginDAOpostgress();
//        DAO.getUsersListFromDataBase();

//        MainController main = new MainController();

        HttpServer server = null;
        try {
            server = HttpServer.create(new InetSocketAddress(7500), 0);

            server.createContext("/index.html", new BasicLoginController());
        }
        catch(IOException e) {

        }


        server.setExecutor(null);




        server.start();

    }
}
