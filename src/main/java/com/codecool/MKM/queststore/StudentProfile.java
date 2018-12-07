package com.codecool.MKM.queststore;

import com.codecool.MKM.queststore.Helpers.CookieHelper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class StudentProfile implements HttpHandler {


    CookieHelper cookieHelper = new CookieHelper();

    private final String SESSION_COOKIE_NAME = "sessionId";


    public void handle(HttpExchange httpExchange) throws IOException {

        System.out.println("chodzi!");
    }
}
