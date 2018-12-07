package com.codecool.MKM.queststore;

import com.codecool.MKM.queststore.Controller.BasicSessionController;
import com.codecool.MKM.queststore.Controller.BasicStoreController;
import com.codecool.MKM.queststore.Controller.SessionController;
import com.codecool.MKM.queststore.Controller.StoreController;
import com.codecool.MKM.queststore.Helpers.CookieHelper;
import com.codecool.MKM.queststore.Model.Item;
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

public class questStoreQuests implements HttpHandler {

    private final String SESSION_COOKIE_NAME = "sessionId";
    CookieHelper cookieHelper = new CookieHelper();
    SessionController session = new BasicSessionController();
    StoreController questStore = new BasicStoreController();

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
                List<Item> quests = questStore.getAllQuests();

                List<String> categories = questStore.getAllCategories(quests);

                Map<String, String> questsPictures = questStore.getQuestsPictures();
                Map<String, String> questsDescriptions = questStore.getQuestsDescriptions();
                String profilePicture = questStore.getProfilePicture(login);

                model.with("categories", categories);
                model.with("questsPictures", questsPictures);
                model.with("questsDescriptions", questsDescriptions);
                model.with("cards", quests);
                model.with("profilePicture", profilePicture);

                template = JtwigTemplate.classpathTemplate("templates/store/quests.twig");
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

