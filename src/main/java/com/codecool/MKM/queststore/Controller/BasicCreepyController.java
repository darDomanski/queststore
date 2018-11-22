package com.codecool.MKM.queststore.Controller;

import com.codecool.MKM.queststore.DAO.MentorDAO;
import com.codecool.MKM.queststore.DAO.MentorDAOpostgress;
import com.codecool.MKM.queststore.Model.Mentor;
import com.codecool.MKM.queststore.Model.User;
import com.codecool.MKM.queststore.View.Viewer;

import java.util.ArrayList;
import java.util.List;

public class BasicCreepyController implements CreepyController {

    Viewer view;
    MentorDAO dao;


    public BasicCreepyController() {
        view = new Viewer();
        dao = new MentorDAOpostgress();
    }


    public void addNewMentor() {
        String name = view.getStringFromUser("Enter mentor's name: ");
        String nickname = view.getStringFromUser("Enter mentor's nickname: ");
        String phone = view.getStringFromUser("Enter mentor's phone: ");
        String email = view.getStringFromUser("Enter mentor's email: ");
        String group =view.getStringFromUser("Enter mentor's group: ");

        dao.addMentorToDataBase(new Mentor(0, name, nickname, phone, email, group));
    }


    public void addMentorToGroup() {

        showAllMentorsSortedByGroup();

        int id = view.getIntegerInputFromUser("Enter id of mentor's which You want add to group: ");
        String group = view.getStringFromUser("Enter group name to add mentor: ");
        dao.addMentorToGroup(group, id);

    }


    private void showAllMentorsSortedByGroup() {

        List<User> listOfMentors = dao.getAllMentorsSortedByGroup();

        List<List<String>> listOfRecords = new ArrayList<List<String>>();

        for(User mentor : listOfMentors) {
            List<String> propertiesList = new ArrayList<String>();
            propertiesList.add(String.valueOf(mentor.getId()));
            propertiesList.add(mentor.getFirstName());
            propertiesList.add(mentor.getGroup());
            listOfRecords.add(propertiesList);
        }

        List<String> headerList = new ArrayList<String>();
        headerList.add("id:");
        headerList.add("name: ");
        headerList.add("group:");
        view.printTable(listOfRecords, headerList);
    }
}
