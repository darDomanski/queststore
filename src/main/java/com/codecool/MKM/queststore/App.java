package com.codecool.MKM.queststore;


import com.codecool.MKM.queststore.DAO.DBConnector.DBConnector;
import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;

public class App
{
    public static void main( String[] args ) throws Exception {

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        DBConnector connector = new DBConnector();

        server.createContext("/login", new Login());
        server.createContext("/static", new Static());
        server.createContext("/quest_store", new QuestStore());
        server.createContext("/quest_store_quests", new QuestStoreQuests(connector ));
        server.createContext("/student_profile", new StudentProfile());
        server.setExecutor(null); // creates a default executor

        server.start();


    }
}
