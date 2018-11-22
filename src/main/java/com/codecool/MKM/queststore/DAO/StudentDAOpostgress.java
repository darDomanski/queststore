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
        String quests = createStringFromArrayInt(student.getQuests());
        String artifacts = createStringFromArrayInt(student.getArtifacts());
        String groupArtifacts = createStringFromArrayInt(student.getGroupArtifacts());

        String query = "INSERT INTO students VALUES(DEFAULT,'" + student.getFirstName() +
                "','" + student.getNickname() +"','"+ student.getPhone() +"','"+ student.getEmail() +
                "',"  + quests + "," +
                artifacts + "," + groupArtifacts + ",'" +
                student.getGroup() + "'," + student.getWallet() + "," +
                student.getExperience() + ");";
        System.out.println(query);
        Connection connection = this.openDataBase();
        Statement statement = getStatement(connection);

        editDataBase(query, connection, statement);
    }


    private String createStringFromArrayInt(int[] array) {
        StringBuilder sb = new StringBuilder();
        sb.append("'{");
        for (int i = 0; i < array.length; i++) {
            if (i+1 < array.length) {
                sb.append(array[i]);
                sb.append(",");
            }else{ sb.append(array[i]); sb.append("}'");}
        }
        return sb.toString();
    }

    public void addStudentToGroup(int studentId, String newGroup) {
        String query = "Update students SET classroom='" + newGroup +"' WHERE id=" + String.valueOf(studentId) + ";";

        Connection connection = this.openDataBase();
        Statement statement = getStatement(connection);

        editDataBase(query, connection, statement);
    }

    public void markDoneQuestByStudent(int studentId, int[] doneQuests) {
        String query = "Update students SET quests=" + doneQuests + " where id=" + studentId + ";";

        Connection connection = this.openDataBase();
        Statement statement = getStatement(connection);

        editDataBase(query, connection, statement);
    }

    public void markBougthArtifactsByStudent(int studentId, int[] bougthArtifacts) {
        String query = "Update students SET artifacts=" + bougthArtifacts + " where id=" + studentId + ";";

        Connection connection = this.openDataBase();
        Statement statement = getStatement(connection);

        editDataBase(query, connection, statement);
    }

    public void buyArtifactsWithOtherStudents(int studentId, int[] bougthGroupArtifacts) {
        String query = "Update students SET classroom_artifacts=" + bougthGroupArtifacts + " where id=" + studentId + ";";

        Connection connection = this.openDataBase();
        Statement statement = getStatement(connection);

        editDataBase(query, connection, statement);
    }


    public List<User> getStudentById(int studentId) {
        String query = "SELECT * FROM students WHERE id=" + studentId + ";";
        return getStudentsListFromDataBase(query);
    }

    public List<User> getAllStudentsSortedByGroup() {
        String query = "SELECT * FROM students ORDER BY classroom;";

        return getStudentsListFromDataBase(query);
    }


    private List<User> getStudentsListFromDataBase(String query) {

        Connection connection = this.openDataBase();

        Statement statement = getStatement(connection);

        ResultSet result = askDataBaseForData(query, connection, statement);

        List<User> studentsList = new ArrayList<User>();

        try {
            while (result.next()) {
                List<String> recordsPropertiesList = new ArrayList<String>();

                int id = result.getInt(1);

                for (int i = 2; i<=5; i++) {
                    recordsPropertiesList.add(result.getString(i));
                }

                int[] quests = createArrayPropertyFromString(result.getString(6));
                int[] artifacts = createArrayPropertyFromString(result.getString(7));
                int[] groupArtifacts = createArrayPropertyFromString(result.getString(8));
                recordsPropertiesList.add(result.getString(9));
                int wallet = result.getInt(10);
                int experience = result.getInt(11);

                studentsList.add(new Student(id,
                        recordsPropertiesList.get(0),
                        recordsPropertiesList.get(1),
                        recordsPropertiesList.get(2),
                        recordsPropertiesList.get(3),
                        recordsPropertiesList.get(4),
                quests, artifacts, groupArtifacts, wallet, experience));
            }
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Data base error - check Your internet connection or try later!");

        }
        closeStatementAndConnection(connection,statement);
        return studentsList;
    }


    private int[] createArrayPropertyFromString(String arrayOfInt) {
        arrayOfInt =  arrayOfInt.replaceAll("\\{","");
        arrayOfInt = arrayOfInt.replaceAll("}","");
        System.out.printf(arrayOfInt);
        String[] arrayOfReadyInt = arrayOfInt.split(",");
        int[] readyProperty = new int[arrayOfReadyInt.length];
        for (int i = 0; i < arrayOfReadyInt.length;i++) {
            readyProperty[i] = Integer.valueOf(arrayOfReadyInt[i]);
        }

        return readyProperty;
    }

}


