package com.codecool.MKM.queststore.DAO;

import com.codecool.MKM.queststore.Model.Student;
import com.codecool.MKM.queststore.Model.User;

import java.util.List;

public interface StudentDAO {

    public void addStudentToDataBase(Student student);

    public void addStudentToGroup(int studentId, String newGroup);

    public void markDoneQuestByStudent(int studentId, int[] doneQuests);

    public void markBougthArtifactsByStudent(int studentId, int[] bougthArtifacts);

    public List<Student> getStudentById(int studentId);

    public List<Student> getAllStudentsSortedByGroup();

    public void buyArtifactsWithOtherStudents(int studentId, int[] bougthGroupArtifacts);
}
