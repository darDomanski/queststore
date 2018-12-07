package com.codecool.MKM.queststore.Controller;

import com.codecool.MKM.queststore.Model.Item;

import java.util.List;
import java.util.Map;

public interface StoreController {
    List<Item> getAllQuests();
    List<Item> getAllArtifacts();
    List<String> getAllCategories(List<Item> itemsList);
    Map<String, String> getQuestsPictures();
    Map<String, String> getQuestsDescriptions();
    Map<String, String> getArtefactsDescriptions();
    Map<String, String> getArtefactsPictures();
    String getProfilePicture(String name);

}
