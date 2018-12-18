package com.codecool.MKM.queststore;

import com.codecool.MKM.queststore.Controller.*;
import com.codecool.MKM.queststore.Helpers.CookieHelper;
import com.codecool.MKM.queststore.Model.Item;
import com.codecool.MKM.queststore.Model.Student;
import com.sun.net.httpserver.HttpExchange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;

import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;

class QuestStoreQuestsTest {

    String SESSION_COOKIE_NAME = "tom";
    int idItem = 1;
    String nameItem = "cart1";
    String categoryItem = "basic";
    int priceItem = 100;

    @Mock
    HttpExchange httpExchange;
    CookieHelper cookieHelper;
    Optional<HttpCookie> cookie;
    SessionController session;
    StoreController questStore;
    StudentController studentController;
    ParseData parseData;
    QuestStoreQuests questStoreQuests_;
    Item item;
    Student student;

    @BeforeEach
    public void initialize()  {
        httpExchange = mock(HttpExchange.class);
        cookieHelper = mock(CookieHelper.class);
        cookie = mock(Optional.class);
        session = mock(BasicSessionController.class);
        questStore = mock(BasicStoreController.class);
        studentController = mock( BasicStudentController.class);
        parseData = mock( ParseData.class);
        questStoreQuests_ = new QuestStoreQuests();
        item = new Item(idItem, nameItem, categoryItem, priceItem);
//        student = new Student(      );


//        Student(int idStudent, String firstNameStudent, String nicknameStudent,
//                String phoneStudent, String emailStudent, String groupStudent,
//        int[] questsStudent, int[] artifactsStudent,int[] groupArtifactsStudent,
//        int walletStudent, int experienceStudent )

    }

//    @Test
//    public void handleTest( ){
//
//        SESSION_COOKIE_NAME = "tom";
//        when(httpExchange.getRequestMethod()).thenReturn("GET");
//        when(cookieHelper.getSessionIdCookie(httpExchange, SESSION_COOKIE_NAME)).thenReturn(cookie);
//        when(cookie.isPresent()).thenReturn(true);
//
//        String sessionId = cookie.get().getValue().replace("\"", "");
//
//        when(session.isSessionActive(sessionId)).thenReturn(true);
//
//        when(session.getUserLogin("xxxxx")).thenReturn("yyyyyy");
//
//        String login = session.getUserLogin("xxxxx");
//
//        List<Item> quests = new ArrayList<Item>();
//        quests.add(item);
//
//        when(questStore.getAllQuests()).thenReturn(quests);
//
//        List<String> categories = questStore.getAllCategories(quests);
//
//        when(questStore.getAllCategories(quests)).thenReturn(categories);
//        when(studentController.getStudentByName(login)).thenReturn ( student );
//
//
//
//    }


//    @Test
//    public void testWordsContainingSubString() throws IOException {
//        String temp = "cf bf af ff ab";
//        List<String> resultA = Arrays.asList("af","ab");
//        when(filePartReader.readLines()).thenReturn(temp);
//        assertEquals( 2, fileWordAnalyzer.wordsContainingSubString("a").size());
//        assertEquals( resultA, fileWordAnalyzer.wordsContainingSubString("a"));
//        assertEquals( 4, fileWordAnalyzer.wordsContainingSubString("f").size());
//        assertEquals( 0, fileWordAnalyzer.wordsContainingSubString("x").size());
//    }


//      mockEntityRepository.Verify(
//    m => m.GetName(It.IsAny<int>()), Times.Never);

}