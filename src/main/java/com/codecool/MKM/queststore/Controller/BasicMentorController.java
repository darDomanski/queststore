package com.codecool.MKM.queststore.Controller;

import com.codecool.MKM.queststore.DAO.StudentDAO;
import com.codecool.MKM.queststore.DAO.StudentDAOpostgress;
import com.codecool.MKM.queststore.Model.Student;
import com.codecool.MKM.queststore.Model.User;
import com.codecool.MKM.queststore.View.Viewer;
import sun.rmi.log.LogInputStream;

import java.util.ArrayList;
import java.util.List;

public class BasicMentorController implements MentorController {

    Viewer view;
    StudentDAO studentDAO;

    public BasicMentorController() {
        this.view = new Viewer();
        this.studentDAO = new StudentDAOpostgress();
    }

    public void createStudent() {

        String name = view.getStringFromUser("Enter a name of new student: ");
        String nickname = view.getStringFromUser("Enter a nickname of new student: ");
        String phone = view.getStringFromUser("Enter a phone number of new student: ");
        String email= view.getStringFromUser("Enter an email of new student: ");
        String group = view.getStringFromUser("Enter a group of new student: ");

        int[] artifacts = new int[5];
        int[] groupArtifacts = new int[5];
        int[] quests = new int[5];


        Student student = new Student(0,name, nickname, phone, email, group, quests, artifacts, groupArtifacts, 0, 0);

        studentDAO.addStudentToDataBase(student);

        view.printMessage("Student succesfully added!");
    }


    public void addStudentToGroup() {
        showAllStudents();
        int id = view.getIntegerInputFromUser("Enter id of student's to add to group: ");
        String group = view.getStringFromUser("Enter a group for student: ");
        studentDAO.addStudentToGroup(id, group);
        view.printMessage("Student added to group!");
    }


    private void showAllStudents() {

        List<User> studentsList = studentDAO.getAllStudentsSortedByGroup();

        List<List<String>> listOfRecords = new ArrayList<List<String>>();
        for (User student : studentsList) {
            List<String> listOfProperties = new ArrayList<String>();
            listOfProperties.add(String.valueOf(student.getId()));
            listOfProperties.add(student.getFirstName());
            listOfProperties.add(student.getGroup());
            listOfRecords.add(listOfProperties);
        }

        List<String> header = new ArrayList<String>();
        String[] headerArray = {"id", "name", "group"};
        for (String item : headerArray) {
            header.add(item);
        }

        view.printTable(listOfRecords, header);
    }
}
