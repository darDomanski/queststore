package com.codecool.MKM.queststore.DAO;

import com.codecool.MKM.queststore.Model.Item;

import java.util.ArrayList;

public interface QuestDAO {

    public void addNewQuest(Item itemToAdd);
    public ArrayList<Item> getAllQuests();
    public ArrayList<Item> getUserQuests(int userId);
}
