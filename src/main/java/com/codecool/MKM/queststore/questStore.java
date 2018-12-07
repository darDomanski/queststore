package com.codecool.MKM.queststore;

import com.codecool.MKM.queststore.Controller.*;
import com.codecool.MKM.queststore.Helpers.CookieHelper;
import com.codecool.MKM.queststore.Model.Item;
import com.codecool.MKM.queststore.Model.Student;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpCookie;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class questStore implements HttpHandler {
    private final String SESSION_COOKIE_NAME = "sessionId";
    CookieHelper cookieHelper = new CookieHelper();
    SessionController session = new BasicSessionController();
    StoreController questStore = new BasicStoreController();
    StudentController studentController = new BasicStudentController();
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

        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

}
