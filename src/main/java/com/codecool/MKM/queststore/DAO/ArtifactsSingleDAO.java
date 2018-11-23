package com.codecool.MKM.queststore.DAO;

import com.codecool.MKM.queststore.Model.Item;

import java.util.ArrayList;

public interface ArtifactsSingleDAO {
    public void addNewArtifact(Item itemToAdd);
    public ArrayList<Item> getAllArtifacts();
    public ArrayList<Item> getUserArtifacts();
}
