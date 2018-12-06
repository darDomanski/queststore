package com.codecool.MKM.queststore;

import com.codecool.MKM.queststore.Controller.BasicLoginController;
import com.codecool.MKM.queststore.Controller.BasicSessionController;
import com.codecool.MKM.queststore.Controller.LoginController;
import com.codecool.MKM.queststore.Controller.SessionController;
import com.codecool.MKM.queststore.Helpers.CookieHelper;
import com.codecool.MKM.queststore.Helpers.SessionIdGenerator;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.HttpCookie;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;


public class Login implements HttpHandler {

    private final String SESSION_COOKIE_NAME = "sessionId";
    CookieHelper cookieHelper = new CookieHelper();
    SessionController session = new BasicSessionController();
    LoginController loginController = new BasicLoginController();
    SessionIdGenerator sessionIdGenerator = new SessionIdGenerator();

    public void handle(HttpExchange httpExchange) throws IOException {
        String response = "";
        String method = httpExchange.getRequestMethod();
        System.out.println("workin..");

        Optional<HttpCookie> cookie = cookieHelper.getSessionIdCookie(httpExchange, SESSION_COOKIE_NAME);

        System.out.println(cookie);

        String sessionId = "1";

        if(cookie.isPresent()){
            sessionId = cookie.get().getValue().replace("\"", "");
        }

        System.out.println("LOGIN SESSION ID AT START: " + sessionId);


        JtwigTemplate template;
        JtwigModel model = JtwigModel.newModel();

        if(method.equals("GET")){
            System.out.println("LOGIN: " + cookie);
            if(session.isSessionActive(sessionId)){
                template = JtwigTemplate.classpathTemplate("templates/store/inventory.html.twig");
                response = template.render(model);
                httpExchange.getResponseHeaders().add("Location", "/logout");
                httpExchange.sendResponseHeaders(303, response.length());
            } else {
                template = JtwigTemplate.classpathTemplate("templates/index.html.twig");
                response = template.render(model);
                httpExchange.sendResponseHeaders(200, response.length());
            }

        }

        if(method.equals("POST")){

            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();
            Map inputs = parseFormData(formData);

            String login = (String) inputs.get("login");
            String password = (String) inputs.get("password");

            if(!loginController.areCredentialsCorrect(login, password)){

                if (!session.isSessionActive(sessionId)) {
                    String userType = loginController.getUserType(login);
                    sessionId = sessionIdGenerator.getSessionId();
                    cookie = Optional.of(new HttpCookie(SESSION_COOKIE_NAME, sessionId));
                    httpExchange.getResponseHeaders().add("Set-Cookie", cookie.get().toString());
                    session.addNewSessionToDB(login, sessionId, userType);
                }

                response = "change_page";
                httpExchange.getResponseHeaders().add("Location", "/logout");
                httpExchange.sendResponseHeaders(303, response.length());
            } else {
                template = JtwigTemplate.classpathTemplate("templates/login.html.twig");
                response = template.render(model);
                httpExchange.sendResponseHeaders(200, response.length());
            }

        }


        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    public Map<String, String> parseFormData(String formData) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<String, String>();
        String[] pairs = formData.split("&");
        for(String pair : pairs){
            String[] keyValue = pair.split("=");
            // We have to decode the value because it's urlencoded. see: https://en.wikipedia.org/wiki/POST_(HTTP)#Use_for_submitting_web_forms
            String value = new URLDecoder().decode(keyValue[1], "UTF-8");
            map.put(keyValue[0], value);
        }
        return map;
    }

}