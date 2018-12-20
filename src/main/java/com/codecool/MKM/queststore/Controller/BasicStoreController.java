package com.codecool.MKM.queststore.Controller;

import com.codecool.MKM.queststore.DAO.*;
import com.codecool.MKM.queststore.DAO.DBConnector.DBConnector;
import com.codecool.MKM.queststore.Model.Item;
import com.codecool.MKM.queststore.View.Viewer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BasicStoreController implements StoreController {

    Viewer view;
    QuestDAO quest;
    ArtifactsSingleDAO artifacts;
    MediaDaoPostgress mediaDao;

    public BasicStoreController() {

        DBConnector connector = DBConnector.getInstance();

        view = new Viewer();
        quest = new QuestDAOpostgress(connector);
        artifacts = new ArtifactsSingleDAOpostgress(connector);
        mediaDao = new MediaDaoPostgress(connector);
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
        return mediaDao.getMedia("photoquest");
    }

    @Override
    public Map<String, String> getQuestsDescriptions() {
        return mediaDao.getMedia("describequest");
    }

    @Override
    public Map<String, String> getArtefactsDescriptions() {
        return mediaDao.getMedia("describeartifact");
    }

    @Override
    public Map<String, String> getArtefactsPictures() {
        return mediaDao.getMedia("photoartifact");
    }

    @Override
    public String getProfilePicture(String name) {
        return mediaDao.getMedia("photouser").get(name);
    }

    public List<Item> getAllArtifacts(){

        List<Item> artifactsList = artifacts.getAllArtifacts();

        return artifactsList;

    }

}
