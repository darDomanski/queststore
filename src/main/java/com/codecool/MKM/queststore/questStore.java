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
import java.util.Optional;

public class questStore implements HttpHandler {
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

                List<Item> artifacts = questStore.getAllArtifacts();

                List<String> categories = questStore.getAllCategories(artifacts);

                model.with("categories", categories);

                model.with("cards", artifacts);
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
