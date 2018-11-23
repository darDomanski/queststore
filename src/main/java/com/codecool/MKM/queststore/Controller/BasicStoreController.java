package com.codecool.MKM.queststore.Controller;

import com.codecool.MKM.queststore.DAO.QuestDAO;
import com.codecool.MKM.queststore.DAO.QuestDAOpostgress;
import com.codecool.MKM.queststore.Model.Item;
import com.codecool.MKM.queststore.View.Viewer;

import java.util.ArrayList;

public class BasicStoreController implements StoreController {

    Viewer view;
    QuestDAO quest;

    public BasicStoreController() {
        view = new Viewer();
        quest = new QuestDAOpostgress();
    }

    public void getAllQuests(){
        ArrayList<Item> itemList = quest.getAllQuests();
        for(int i = 0; i< itemList.size(); i++){
            System.out.println(itemList.get(i).getName());
        }
    }

    public void getAllArtifacts(){
        System.out.println("to be done");
    }
}
