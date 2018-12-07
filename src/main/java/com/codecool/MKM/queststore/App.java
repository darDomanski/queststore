package com.codecool.MKM.queststore;


import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;

public class App
{
    public static void main( String[] args ) throws Exception {

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        server.createContext("/login", new Login());
        server.createContext("/static", new Static());
        server.createContext("/quest_store", new questStore());
        server.createContext("/quest_store_quests", new questStoreQuests());
        server.createContext("/student_profile", new StudentProfile());
        server.setExecutor(null); // creates a default executor

        server.start();


    }
}
