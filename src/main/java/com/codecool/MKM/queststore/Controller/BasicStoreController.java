package com.codecool.MKM.queststore.Controller;

import com.codecool.MKM.queststore.DAO.ArtifactsSingleDAO;
import com.codecool.MKM.queststore.DAO.ArtifactsSingleDAOpostgress;
import com.codecool.MKM.queststore.DAO.QuestDAO;
import com.codecool.MKM.queststore.DAO.QuestDAOpostgress;
import com.codecool.MKM.queststore.Model.Item;
import com.codecool.MKM.queststore.View.Viewer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BasicStoreController implements StoreController {

    Viewer view;
    QuestDAO quest;
    ArtifactsSingleDAO artifacts;
    public BasicStoreController() {
        view = new Viewer();
        quest = new QuestDAOpostgress();
        artifacts = new ArtifactsSingleDAOpostgress();
    }

    public List<Item> getAllQuests(){
        ArrayList<Item> itemList = quest.getAllQuests();
        return itemList;
    }

    public List<String> getAllCategories(List<Item> itemList){
        List<String> categories = new ArrayList<>();

        for(int i = 0; i< itemList.size(); i++){
            if(!categories.contains(itemList.get(i).getCategory())) {
                categories.add(itemList.get(i).getCategory());
            }
        }

        return categories;
    }

    @Override
    public Map<String, String> getQuestsPictures() {
        return null;
    }

    @Override
    public Map<String, String> getQuestsDescriptions() {
        return null;
    }

    @Override
    public Map<String, String> getArtefactsDescriptions() {
        return null;
    }

    @Override
    public Map<String, String> getArtefactsPictures() {
        return null;
    }

    @Override
    public String getProfilePicture(String name) {
        return null;
    }

    public List<Item> getAllArtifacts(){

        List<Item> artifactsList = artifacts.getAllArtifacts();

        return artifactsList;

    }

}
