package com.codecool.MKM.queststore.DAO;

import com.codecool.MKM.queststore.Model.Mentor;
import com.codecool.MKM.queststore.Model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MentorDAOpostgress extends DAO implements MentorDAO {

    public List<User> getAllMentors() {
        String query = "select * from mentors;";
        return getMentorsListFromDataBase(query);
    }


    private List<User> getMentorsListFromDataBase(String query) {

        Connection connection = this.openDataBase();

        Statement statement = getStatement(connection);

        ResultSet result = askDataBaseForData(query, connection, statement);

        List<User> mentorsList = new ArrayList<User>();

        try {
            while (result.next()) {
                List<String> recordsPropertiesList = new ArrayList<String>();

                for (int i = 1; i<=7; i++) {
                    recordsPropertiesList.add(result.getString(i));
                }

                mentorsList.add(new Mentor(recordsPropertiesList.get(0), recordsPropertiesList.get(1),recordsPropertiesList.get(2),recordsPropertiesList.get(3),recordsPropertiesList.get(4)));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        closeStatementAndConnection(connection,statement);
        return mentorsList;
    }
}
