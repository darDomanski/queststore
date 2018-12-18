package com.codecool.MKM.queststore;

import com.codecool.MKM.queststore.Controller.*;
import com.codecool.MKM.queststore.Helpers.CookieHelper;
import com.codecool.MKM.queststore.Model.Item;
import com.codecool.MKM.queststore.Model.Student;
import com.sun.net.httpserver.HttpExchange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;

class questStoreQuestsTest {

    HttpExchange httpExchange = "GET";

    @Mock
    CookieHelper cookieHelper;
    SessionController session;
    StoreController questStore;
    StudentController studentController;
    ParseData parseData;
    QuestStoreQuests questStoreQuests_;
    Item item;
    Student student;

    @BeforeEach
    public void initialize()  {
        cookieHelper = mock(CookieHelper.class);
        session = mock(BasicSessionController.class);
        questStore = mock(BasicStoreController.class);
        studentController = mock( BasicStudentController.class);
        parseData = mock( ParseData.class);
        questStoreQuests_ = new QuestStoreQuests();
        item = new Item();
        student = new Student();
    }

    @Test
    public handleTest( httpExchange ){

        when(session.getUserLogin("xxxxx")).thenReturn("yyyyyy");

    }


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