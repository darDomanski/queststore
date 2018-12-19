package com.codecool.MKM.queststore.DAO;

import com.codecool.MKM.queststore.DAO.DBConnector.DBConnector;
import com.codecool.MKM.queststore.Model.Mentor;
import com.codecool.MKM.queststore.Model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MentorDAOpostgress extends DAO implements MentorDAO {

    public MentorDAOpostgress(DBConnector connector) {
        super(connector);
    }

    public List<User> getAllMentors() {
        String query = "select * from mentors;";
        return getMentorsListFromDataBase(query);
    }

    public List<User> getAllMentorsSortedByGroup() {
        String query = "select * from mentors order by classroom;";
        return getMentorsListFromDataBase(query);
    }


    private List<User> getMentorsListFromDataBase(String query) {

        Connection connection = this.openDataBase();
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            statement = connection.prepareStatement(query);

            result = askDataBaseForData(query, statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<User> mentorsList = new ArrayList<User>();

        try {
            while (result.next()) {
                List<String> recordsPropertiesList = new ArrayList<String>();

                int id = result.getInt(1);

                for (int i = 1; i<=6; i++) {
                    recordsPropertiesList.add(result.getString(i));
                }

                mentorsList.add(new Mentor(id,
                        recordsPropertiesList.get(1),
                        recordsPropertiesList.get(2),
                        recordsPropertiesList.get(3),
                        recordsPropertiesList.get(4),
                        recordsPropertiesList.get(5)));
            }
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Data base error - check Your internet connection or try later!");

        }
        closeStatementAndConnection(connection,statement);
        return mentorsList;
    }


    public void addMentorToDataBase(Mentor mentor) {
        String query = "INSERT INTO mentors VALUES(DEFAULT,'" + mentor.getFirstName() +
                "','" + mentor.getNickname() +"','"+ mentor.getPhone() +"','"+ mentor.getEmail() +
                "','" + mentor.getGroup() +  "');";

        Connection connection = this.openDataBase();

        editDataBase(connection, query);
    }


    public void addMentorToGroup(String newGroup, int mentorId) {
        String query = "Update mentors SET classroom='" + newGroup +"' WHERE id=" + String.valueOf(mentorId) + ";";

        Connection connection = this.openDataBase();

        editDataBase(connection, query);
    }


    public List<User> getMentorById(int id){
        String query = "select * from mentors where id=" + id + ";";
        return getMentorsListFromDataBase(query);

    }

    public void editMentor(int mentorId, String[] newProperties ) {
        String query = "Update mentors SET firstname='" + newProperties[0] +
                "',nickname='" + newProperties[1] + "',phone='" + newProperties[2] +
                "',email='" + newProperties[3] + "',classroom='" +
                newProperties[4] + "' where id=" + mentorId + ";";

        Connection connection = this.openDataBase();

        editDataBase(connection, query);
    }

}
