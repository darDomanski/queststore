package com.codecool.MKM.queststore.DAO;

import com.codecool.MKM.queststore.Model.Student;
import com.codecool.MKM.queststore.Model.User;

import java.util.List;

public interface MentorDAO {

    public List<User> getAllMentors();

    public void addStudentToDataBase(Student student);
}
