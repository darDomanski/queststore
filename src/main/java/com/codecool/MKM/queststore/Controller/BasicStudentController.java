package com.codecool.MKM.queststore.Controller;

import com.codecool.MKM.queststore.DAO.StudentDAO;
import com.codecool.MKM.queststore.DAO.StudentDAOpostgress;
import com.codecool.MKM.queststore.Model.Student;
import com.codecool.MKM.queststore.Model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BasicStudentController implements StudentController{

    private StudentDAO studentDAO;

    public BasicStudentController(){
        this.studentDAO = new StudentDAOpostgress();
    }


    public List<String> seeVallet(Integer id) {
        List<Student> studentList =  studentDAO.getStudentById(id);
        Student student = studentList.get(0);

        List<String>  wallet = new ArrayList<String>();
        String coolCoins = String.valueOf(student.getWallet());
        wallet.add(coolCoins);
        return wallet;
    }

    public Optional<Student> getStudentByName(String name){
        Optional<Student> student = studentDAO.getStudentByNickName(name);
        return student;
    }

    public void buyArtifactSingle() {

    }

    public void buyArtifactGroup() {

    }

    public Integer checkLevelOfExp(Integer id) {
        return null;
    }
}
