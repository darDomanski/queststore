package com.codecool.MKM.queststore.Controller;

import com.codecool.MKM.queststore.Model.Item;

import java.util.List;

public interface StoreController {
    List<Item> getAllQuests();
    List<Item> getAllArtifacts();
    List<String> getAllCategories(List<Item> itemsList);
}
