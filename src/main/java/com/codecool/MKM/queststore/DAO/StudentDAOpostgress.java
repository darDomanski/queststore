package com.codecool.MKM.queststore.DAO;

import com.codecool.MKM.queststore.Model.Mentor;
import com.codecool.MKM.queststore.Model.Student;
import com.codecool.MKM.queststore.Model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOpostgress extends DAO implements StudentDAO {


    public void addStudentToDataBase(Student student) {

        String query = "INSERT INTO students VALUES(DEFAULT,'" + student.getFirstName() +
                "','" + student.getNickname() +"',"+ student.getPhone() +"','"+ student.getEmail() +
                "',"  + student.getQuests() + "," +
                student.getArtifacts() + "," + student.getGroupArtifacts() + ",'" +
                student.getGroup() + "'," + student.getWallet() + "," +
                student.getExperience() + ");";

        Connection connection = this.openDataBase();
        Statement statement = getStatement(connection);

        editDataBase(query, connection, statement);
    }


    public void addStudentToGroup(int studentId, String newGroup) {
        String query = "Update students SET classroom='" + newGroup +"' WHERE id=" + String.valueOf(studentId) + ";";

        Connection connection = this.openDataBase();
        Statement statement = getStatement(connection);

        editDataBase(query, connection, statement);
    }

    ///// todo "Mark done Quests by Student",
    //  todo  "Mark bought Artifacts by student", "Check student wallet";

    


}
