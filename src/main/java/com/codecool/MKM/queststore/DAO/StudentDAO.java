package com.codecool.MKM.queststore.DAO;

import com.codecool.MKM.queststore.Model.Student;

public interface StudentDAO {

    public void addStudentToDataBase(Student student);

    public void addStudentToGroup(int studentId, String newGroup);
}
