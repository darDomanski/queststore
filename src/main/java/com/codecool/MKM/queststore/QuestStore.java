package com.codecool.MKM.queststore;

import com.codecool.MKM.queststore.Controller.*;
import com.codecool.MKM.queststore.DAO.DBConnector.DBConnector;
import com.codecool.MKM.queststore.Helpers.CookieHelper;
import com.codecool.MKM.queststore.Model.Item;
import com.codecool.MKM.queststore.Model.Student;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import com.codecool.MKM.queststore.DAO.SessionDAOpostgress;

import java.io.*;
import java.net.HttpCookie;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class QuestStore implements HttpHandler {
    private final String SESSION_COOKIE_NAME = "sessionId";
    private CookieHelper cookieHelper;
    private SessionController session;
    private StoreController questStore;
    private StudentController studentController;
    private DBConnector connector;

    public QuestStore(DBConnector connector) {
        this.connector = connector;
        this.cookieHelper = new CookieHelper();
        this.session = new BasicSessionController(this.connector);
        this.questStore = new BasicStoreController(this.connector);
        this.studentController = new BasicStudentController(this.connector);
    }


    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String response = "";
        String method = httpExchange.getRequestMethod();

        Optional<HttpCookie> cookie = cookieHelper.getSessionIdCookie(httpExchange, SESSION_COOKIE_NAME);


        String sessionId = "fakeid";

        if(cookie.isPresent()){
            sessionId = cookie.get().getValue().replace("\"", "");
        }

        JtwigTemplate template;
        JtwigModel model = JtwigModel.newModel();

        if(method.equals("GET")){
            if(session.isSessionActive(sessionId)){
                String login = session.getUserLogin(sessionId);
                List<Item> artifacts = questStore.getAllArtifacts();

                List<String> categories = questStore.getAllCategories(artifacts);
                Map<String, String> artifactsPictures = questStore.getArtefactsPictures();

                Optional<Student> student = studentController.getStudentByName(login);
                int coolcoins = student.get().getWallet();
                System.out.println(artifactsPictures.toString());

                Map<String, String> artifactsDescriptions = questStore.getArtefactsDescriptions();
                String profilePicture = questStore.getProfilePicture(login);

                model.with("categories", categories);
                model.with("artifactPictures", artifactsPictures);
                model.with("artifactDescriptions", artifactsDescriptions);
                model.with("cards", artifacts);
                model.with("profilePicture", profilePicture);
                model.with("coolcoins", coolcoins);

                template = JtwigTemplate.classpathTemplate("templates/store/questStore.twig");
                response = template.render(model);
                httpExchange.sendResponseHeaders(303, 0);
            } else {
                httpExchange.getResponseHeaders().add("Location", "/login");
                httpExchange.sendResponseHeaders(303, 0);
            }
        }

        if(method.equals("POST")) {
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();
            Map inputs = parseFormData(formData);
            System.out.println("guzik dziala");
            if (inputs.containsKey("logOut")) {

               SessionDAOpostgress dao = new SessionDAOpostgress(connector);

               dao.deleteSession(sessionId);

            }
            httpExchange.getResponseHeaders().add("Location", "/login");
            httpExchange.sendResponseHeaders(303, 0);
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
