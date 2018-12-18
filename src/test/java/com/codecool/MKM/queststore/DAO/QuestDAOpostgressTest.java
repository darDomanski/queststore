package com.codecool.MKM.queststore.DAO;

import com.codecool.MKM.queststore.Model.Item;
import com.codecool.MKM.queststore.Model.Student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class QuestDAOpostgressTest {

    QuestDAOpostgress questDAOpostgress;


    @Mock
    StudentDAO student;

    @BeforeEach
    public void initialize()  {
        questDAOpostgress = new QuestDAOpostgress();
        student = mock(StudentDAO.class);
    }

//    @Test
//    public void getUserQuestsTest(){
//        ArrayList<Item> items = ArrayList<Item>();
//        assertEquals( 0, questDAOpostgress.getUserQuests("x").size());
//    }
//
//    @Test
//    public void getQuestFromDataBaseTest(){
//        Item item = questDAOpostgress.getQuestFromDataBase(String query);
//    }

    @Test
    public void getQuestsByIdTest(){

        int id =1;
        String name = "Tom";
        String category = "basic";
        int price = 100;
        Item item1 = new Item(id, name, category, price);
        int[] questsId = {0,1,2};

        ArrayList<Item> items = new ArrayList<>();
        items.add(item1);

        String query = "SELECT * FROM quests WHERE id=" + questsId[0] + ";";

        when(questDAOpostgress.getQuestFromDataBase(query)).thenReturn( item1 );

        assertEquals( items, questDAOpostgress.getQuestsById( questsId ));

    }
}