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

        List<User> listOfMentors = dao.getAllMentorsSortedByGroup();

        List<List<String>> listOfRecords = new ArrayList<List<String>>();

        for(User mentor : listOfMentors) {
            List<String> propertiesList = new ArrayList<String>();

         }


    }
}
