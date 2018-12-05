package com.codecool.MKM.queststore.Controller;

import com.codecool.MKM.queststore.DAO.*;
import com.codecool.MKM.queststore.Model.Creepy;
import com.codecool.MKM.queststore.Model.User;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasicLoginController implements HttpHandler {

    private LoginDAO loginDAO;
    private User loggedUser;




    public void handle(HttpExchange httpExchange) throws IOException {

        loginDAO = new LoginDAOpostgress();

        String response = "";
        String method = httpExchange.getRequestMethod();

        if(method.equals("GET")){
            response = getStringFromHtml("/home/k/codecool/Web/Quest Store/queststore/views/index.html");
        }

        if(method.equals("POST")){

            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();

            Map inputs = parseFormData(formData);

            String name = inputs.get("name").toString();
            String password = inputs.get("password").toString();

            List<List<String>> listOfUsers = loginDAO.getUsersListFromDataBase();

            String typeOfUser = "";


            for (List<String> user : listOfUsers) {
                if (user.get(0).equals(name) && user.get(1).equals(password)) {
                    typeOfUser = user.get(2);
                    loggedUser = createUserObject(user.get(0), user.get(2));
                }
            }

            switch (typeOfUser) {
                case "":
                    response = getStringFromHtml("/home/k/codecool/Web/Quest Store/queststore/views/index.html");
                    break;

                case "mentors":
                    response = getStringFromHtml("/home/k/codecool/Web/Quest Store/queststore/views/managmentSystem/mentor.html");
                    break;

                case "creepy":
                    response = getStringFromHtml("/home/k/codecool/Web/Quest Store/queststore/views/managmentSystem/creepy.html");
                    break;

                case "student":
                    response = getStringFromHtml("/home/k/codecool/Web/Quest Store/queststore/views/managmentSystem/student_profile.html");
                    break;
            }
        }
        try {
            sendResponse(httpExchange, response);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error! Check Your internet connection!");
        }
    }


    private void sendResponse(HttpExchange httpExchange, String response) throws IOException {
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }


    private String getStringFromHtml(String pathFile) {
        String content = "";
        try {
            BufferedReader in = new BufferedReader(new FileReader(pathFile));
            String html;
            while ((html = in.readLine()) != null) {
                content +=html;
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error while loading page file - check file location on the server");
        }
        return content;
    }


    private static Map<String, String> parseFormData(String formData) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<>();
        String[] pairs = formData.split("&");
        for(String pair : pairs){
            String[] keyValue = pair.split("=");
            // We have to decode the value because it's urlencoded. see: https://en.wikipedia.org/wiki/POST_(HTTP)#Use_for_submitting_web_forms
            String value = new URLDecoder().decode(keyValue[1], "UTF-8");
            map.put(keyValue[0], value);
        }
        return map;
    }



    public User createUserObject(String userType, String nickname) {
        if (userType.equals("mentors")) {
            MentorDAO dao = new MentorDAOpostgress();
            return dao.getMentorByNickName(nickname).get(0);
        }
        else if (userType.equals("student")) {
            StudentDAO dao = new StudentDAOpostgress();
            return dao.getStudentByNickName(nickname).get(0);
        }
        else return new Creepy(nickname);
    }


}
