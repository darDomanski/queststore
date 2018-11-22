package com.codecool.MKM.queststore.Controller;

import com.codecool.MKM.queststore.DAO.QuestDAO;
import com.codecool.MKM.queststore.DAO.QuestDAOpostgress;
import com.codecool.MKM.queststore.Model.Quest;
import com.codecool.MKM.queststore.View.Viewer;

import java.util.ArrayList;
import java.util.Arrays;

public class BasicStoreController implements StoreController {

    Viewer view;
    QuestDAO quest;

    public BasicStoreController() {
        view = new Viewer();
        quest = new QuestDAOpostgress();
    }

    public void getAllQuests(){
        System.out.println("i'm in get all quests");
        ArrayList<Quest> questList = quest.getAllQuests();
        for(int i=0; i<questList.size();i++){
            System.out.println(questList.get(i).getName()ch);
        }
    }

    public void getAllArtifacts(){
        System.out.println("to be done");
    }
}
