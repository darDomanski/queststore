package com.codecool.MKM.queststore.DAO;

import com.codecool.MKM.queststore.Model.Student;
import com.codecool.MKM.queststore.Model.User;

import java.util.List;

public interface StudentDAO {

    public void addStudentToDataBase(Student student);

    public void addStudentToGroup(int studentId, String newGroup);

    public void markDoneQuestByStudent(int studentId, int[] doneQuests);

    public void markBougthArtifactsByStudent(int studentId, int[] bougthArtifacts);

    public List<User> getStudentById(int studentId);

    public List<User> getAllStudentsSortedByGroup();

    public void buyArtifactsWithOtherStudents(int studentId, int[] bougthGroupArtifacts);

    public List<User> getStudentByNickName(String nickname);
}
