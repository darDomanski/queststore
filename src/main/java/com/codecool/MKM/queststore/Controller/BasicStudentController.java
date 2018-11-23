package com.codecool.MKM.queststore.Controller;

import com.codecool.MKM.queststore.DAO.StudentDAO;
import com.codecool.MKM.queststore.DAO.StudentDAOpostgress;
import com.codecool.MKM.queststore.Model.Student;
import com.codecool.MKM.queststore.Model.User;
import com.codecool.MKM.queststore.View.Viewer;

import java.util.ArrayList;
import java.util.List;

public class BasicStudentController implements StudentController{

    private StudentDAO studentDAO;
    Viewer view;

    public BasicStudentController(){
        this.studentDAO = new StudentDAOpostgress();
        view = new Viewer();
    }


    public void seeVallet(int id) {
        List<User> studentList =  studentDAO.getStudentById(id);
        Student student = (Student) studentList.get(0);

        List<String>  wallet = new ArrayList<String>();
        String coolCoins = String.valueOf(student.getWallet());
        wallet.add(student.getFirstName());
        wallet.add(coolCoins);
        List<List<String>> listOfRecords = new ArrayList<List<String>>();
        listOfRecords.add(wallet);
        List<String> header = new ArrayList<String>();
        header.add("Name:");
        header.add("CoolCoins: ");
        view.printTable(listOfRecords, header);

    }

    public void buyArtifactSingle() {

    }

    public void buyArtifactGroup() {

    }

    public Integer checkLevelOfExp(Integer id) {
        return null;
    }
}
