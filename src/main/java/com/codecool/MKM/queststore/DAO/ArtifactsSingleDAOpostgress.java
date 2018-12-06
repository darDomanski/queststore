package com.codecool.MKM.queststore.DAO;

import com.codecool.MKM.queststore.Model.ArtifactGroups;
import com.codecool.MKM.queststore.Model.Item;
import com.codecool.MKM.queststore.Model.StoreItems;
import com.codecool.MKM.queststore.Model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtifactsSingleDAOpostgress extends DAO implements ArtifactsGroupsDAO {

    StudentDAO student = new StudentDAOpostgress();

    public void addNewArtifact(Item itemToAdd) {
        String query = "INSERT INTO artifacts VALUES(DEFAULT,'" + itemToAdd.getName() + "'," +
                itemToAdd.getCategory() + "'," + itemToAdd.getPrice() + ");";

        Connection connection = this.openDataBase();

        editDataBase(connection, query);
    }

    public ArrayList<Item> getAllArtifacts(){
        String itemsType = "artifacts";
        return getAllItems(itemsType);
    }

    public void getUserArtifacts(int userId){
        List<Student> studentById = student.getStudentById(userId);
        Student studentModel = studentById.get(0);
        int[] studentArtifactsId = studentModel.getArtifacts();
        int[] studentGroupArtifactsId = studentModel.getGroupArtifacts();
        ArrayList<StoreItems> singleArtifacts = getArtifactsById(studentArtifactsId, "artifacts");
        ArrayList<StoreItems> groupArtifacts = getArtifactsById(studentGroupArtifactsId, "clasroom_artifacts");
    }

    public ArrayList<StoreItems> getArtifactsById(int[] artifactsId, String artifactType){
        ArrayList<StoreItems> artifactsList = new ArrayList<StoreItems>();

        for(int i=0; i<artifactsId.length; i++){
            StoreItems artifact = getArtifactFromDataBase(artifactType, artifactsId[i]);
            artifactsList.add(artifact);
        }
        return artifactsList;
    }

    public StoreItems getArtifactFromDataBase(String artifactType, int artifactId){
        String query = "SELECT * FROM "+ artifactType  +" WHERE id=" + artifactId + ";";
        StoreItems artifact = null;
        try {
            Connection connection = this.openDataBase();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = askDataBaseForData(query, statement);

            while (result.next()) {
                if(artifactType == "artifacts"){

                    artifact = new Item(result.getInt("id"), result.getString("firstname"), result.getString("category"), result.getInt("price"));

                } else if (artifactType == "clasroom_artifacts"){
                    artifact = new ArtifactGroups(result.getInt("id"), result.getString("firstname"), result.getString("category"), result.getInt("price"), createArrayPropertyFromString(result.getString("members")), result.getBoolean("payStatus"), result.getBoolean("activeStatus"));
                }
            }

        } catch(SQLException e){
            e.printStackTrace();
        }
        return artifact;
    }

    private String[] createArrayPropertyFromString(String arrayOfInt) {
        arrayOfInt =  arrayOfInt.replaceAll("\\{","");
        arrayOfInt = arrayOfInt.replaceAll("}","");
        System.out.printf(arrayOfInt);
        String[] arrayOfStrings = arrayOfInt.split(",");

        return arrayOfStrings;
    }
}
