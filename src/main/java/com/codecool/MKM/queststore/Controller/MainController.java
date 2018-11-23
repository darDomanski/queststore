package com.codecool.MKM.queststore.Controller;

import com.codecool.MKM.queststore.DAO.LoginDAO;
import com.codecool.MKM.queststore.DAO.LoginDAOpostgress;
import com.codecool.MKM.queststore.DAO.StudentDAO;
import com.codecool.MKM.queststore.DAO.StudentDAOpostgress;
import com.codecool.MKM.queststore.Model.Creepy;
import com.codecool.MKM.queststore.Model.Mentor;
import com.codecool.MKM.queststore.Model.Student;
import com.codecool.MKM.queststore.Model.User;
import com.codecool.MKM.queststore.View.Viewer;

import java.util.List;

public class MainController {

    private MentorController mentorController;
    private StudentController studentController;
    private StudentDAO studentDAO;
    private CreepyController creepyController;
    private LoginDAO loginDAO;
    private Viewer view;


    private final String[] menuStart = {"Log in", "Exit"};

    private final String[] mentorMenu = {"Create Student,", "Add student to group", "Add new Quest", "Devide Quest into category",
            "Devide Artifacts into category", "Add artifacts", "Edit Quests", "Edit Artifacts", "Mark done Quests by Student",
            "Mark bought Artifacts by student", "Check student wallet"};

    private final String[] studentMenu = {"See wallet", "Buy Artifacts", "Buy artifacts with other students", "See level of experience"};



    public MainController() {
        mentorController = new BasicMentorController();
        studentController = new BasicStudentController();
        creepyController = new BasicCreepyController();
        loginDAO = new LoginDAOpostgress();
        studentDAO = new StudentDAOpostgress();
        view = new Viewer();

        run();

    }

    public void run() {

//        mentorController.createStudent();
//        mentorController.addQuest();
//        mentorController.addStudentToGroup();
//        studentController.seeVallet(3);

        boolean appRun = true;
        while(appRun) {

        view.printMenu(menuStart);
        int chooice = view.getIntegerInputFromUser("Enter a number of option: ");

        if (chooice == 1) {
            String login = view.getStringFromUser("Enter login: ");
            String password = view.getStringFromUser("Enter password: ");
            String type = logVerification(login, password);
            if (!type.equals("Bad")) {
                createUser(login,password,type);
            }
            else{ view.printMessage("Bad login or password!");}
        }
        else if(chooice == 2) {
            appRun = false;
        }
        else{view.printMessage("No such option!");}

        }
    }

    private String logVerification(String login, String password) {

        List<List<String>> listOfUsers = loginDAO.getUsersListFromDataBase();

        for (List<String> userData : listOfUsers) {
            String userPassword = userData.get(1);
            String userLogin = userData.get(0);

            if (password.equals(userPassword) && login.equals(userLogin)) {
                return userData.get(2);
            }
        }
        return "Bad";
    }

    private void createUser(String login, String password, String type) {
        if (type.equals("creepy")) {
            creepyController.runCreepy();
        }
        else if(type.equals("mentor")) {

//            User mentor = new Mentor()
        }
        else if (type.equals("student")) {
            List<User> listWithStudent = studentDAO.getStudentByNickName(login);
//            User student = new Student();
            /// get student from base
        }

    }





}
