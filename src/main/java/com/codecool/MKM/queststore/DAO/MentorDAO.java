package com.codecool.MKM.queststore.DAO;

import com.codecool.MKM.queststore.Model.Mentor;
import com.codecool.MKM.queststore.Model.Student;
import com.codecool.MKM.queststore.Model.User;

import java.util.List;

public interface MentorDAO {

    public List<User> getAllMentors();

    public void addMentorToDataBase(Mentor mentor);

    public void addMentorToGroup(String newGroup, int mentorId);

    public List<User> getMentorById(int id);

    public void editMentor(int mentorId, String[] newProperties );

    public List<User> getAllMentorsSortedByGroup();
}
