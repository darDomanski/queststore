package com.codecool.MKM.queststore.DAO;

import com.codecool.MKM.queststore.Model.Item;
import com.codecool.MKM.queststore.Model.StoreItems;

import java.util.ArrayList;
import java.util.List;

public interface ArtifactsSingleDAO {
    public void addNewArtifact(Item itemToAdd);
    public List<Item> getAllArtifacts();
    public List<StoreItems> getUserArtifacts(int userId);
}
