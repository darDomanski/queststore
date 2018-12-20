package com.codecool.MKM.queststore.DAO;

import com.codecool.MKM.queststore.DAO.DBConnector.DBConnector;
import com.codecool.MKM.queststore.Model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class StudentDAOpostgressTest {

    private StudentDAOpostgress studentDAOpostgressSpy;

    @Mock
    Student student;
    DBConnector connector;
    Connection connection;
    PreparedStatement statement;
    ResultSet resultSet;

    @BeforeEach
    public void setUp() throws SQLException {

        this.student = mock(Student.class);
        this.connector = mock(DBConnector.class);
        this.connection = mock(Connection.class);
        this.statement = mock(PreparedStatement.class);
        this.resultSet = mock(ResultSet.class);
        this.studentDAOpostgressSpy = spy(new StudentDAOpostgress(this.connector));
    }

    @Test
    public void testAddStudentToDataBase() throws SQLException {

        arrangeMocks();

        studentDAOpostgressSpy.addStudentToDataBase(student);

        verify(studentDAOpostgressSpy).openDataBase();
        verify(studentDAOpostgressSpy).editDataBase(anyObject(), anyString());
    }

    @Test
    public void testAddStudentToGroup() throws SQLException {

        arrangeMocks();

        studentDAOpostgressSpy.addStudentToGroup(anyInt(), anyString());

        verify(studentDAOpostgressSpy).openDataBase();
        verify(studentDAOpostgressSpy).editDataBase(anyObject(), anyString());
    }

    @Test
    public void testMarkDoneQuestByStudent() throws SQLException {

        arrangeMocks();

        studentDAOpostgressSpy.markDoneQuestByStudent(anyInt(), anyObject());

        verify(studentDAOpostgressSpy).openDataBase();
        verify(studentDAOpostgressSpy).editDataBase(anyObject(), anyString());
    }

    @Test
    public void testMarkBougthArtifactsByStudent() throws SQLException {

        arrangeMocks();

        studentDAOpostgressSpy.markBougthArtifactsByStudent(anyInt(), anyObject());

        verify(studentDAOpostgressSpy).openDataBase();
        verify(studentDAOpostgressSpy).editDataBase(anyObject(), anyString());
    }

    @Test
    public void testBuyArtifactsWithOtherStudents() throws SQLException {

        arrangeMocks();

        studentDAOpostgressSpy.buyArtifactsWithOtherStudents(anyInt(), anyObject());

        verify(studentDAOpostgressSpy).openDataBase();
        verify(studentDAOpostgressSpy).editDataBase(anyObject(), anyString());
    }

    @Test
    public void testGetStudentById() throws SQLException {

        arrangeMocks();
        when(this.resultSet.next()).thenReturn(true, false);
        List<Student> listWithDefaultStudent = new ArrayList<>();
        listWithDefaultStudent.add(this.student);

        List<Student> listWithStudentFound =  studentDAOpostgressSpy.getStudentById(1);

        assertEquals(listWithDefaultStudent.get(0).getId(), listWithStudentFound.get(0).getId());
    }

    @Test
    public void testGetStudentByNickName() throws SQLException {

        arrangeMocks();
        when(this.resultSet.next()).thenReturn(true, false);
        List<Student> listWithDefaultStudent = new ArrayList<>();
        listWithDefaultStudent.add(this.student);
        Optional<Student> dafaultStudent = Optional.ofNullable(listWithDefaultStudent.get(0));

        Optional<Student> studentFound =  studentDAOpostgressSpy.getStudentByNickName("Puchatek");

        assertEquals(dafaultStudent.get().getNickname(), studentFound.get().getNickname());
    }

    @Test
    public void testGetAllStudentsSortedByGroup() throws SQLException {

        arrangeMocks();
        when(this.resultSet.next()).thenReturn(true, true, true, false);
        List<Student> listWithDefaultStudents = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            listWithDefaultStudents.add(student);
        }
        
        List<Student> listWithStudentsFound = studentDAOpostgressSpy.getAllStudentsSortedByGroup();

        assertEquals(listWithDefaultStudents.get(0).getGroup(), listWithStudentsFound.get(0).getGroup());
        assertEquals(listWithDefaultStudents.get(1).getGroup(), listWithStudentsFound.get(1).getGroup());
        assertEquals(listWithDefaultStudents.get(2).getGroup(), listWithStudentsFound.get(2).getGroup());
    }



    private void arrangeMocks() throws SQLException {

        when(this.connector.getConnection()).thenReturn(this.connection);

        when(this.student.getId()).thenReturn(1);
        when(this.student.getQuests()).thenReturn(new int[] {1, 44, 3, 6, 7, 54});
        when(this.student.getArtifacts()).thenReturn(new  int[] {61, 3, 4, 6, 5, 44});
        when(this.student.getGroupArtifacts()).thenReturn(new int[] {51, 24, 3, 6, 7, 43});
        when(this.student.getFirstName()).thenReturn("Krzys", "Ania", "Kasia");
        when(this.student.getNickname()).thenReturn("Puchatek");
        when(this.student.getPhone()).thenReturn("444-333-222");
        when(this.student.getEmail()).thenReturn("mis@oe.pl");
        when(this.student.getGroup()).thenReturn("B1", "A2", "C3");
        when(this.student.getWallet()).thenReturn(100);
        when(this.student.getExperience()).thenReturn(280);

        when(this.connection.prepareStatement(anyString())).thenReturn(this.statement);
        when(this.statement.executeUpdate()).thenReturn(0);
        when(this.statement.executeQuery()).thenReturn(this.resultSet);

        when(this.resultSet.getInt(1)).thenReturn(1);
        when(this.resultSet.getString(2)).thenReturn("Krzys", "Ania", "Kasia");
        when(this.resultSet.getString(3)).thenReturn("Puchatek");
        when(this.resultSet.getString(4)).thenReturn("444-333-222");
        when(this.resultSet.getString(5)).thenReturn("mis@oe.pl");
        when(this.resultSet.getString(6)).thenReturn("{1,44,3,6,7,54}");
        when(this.resultSet.getString(7)).thenReturn("{61,3,4,6,5,44}");
        when(this.resultSet.getString(8)).thenReturn("{51,24,3,6,7,43}");
        when(this.resultSet.getString(9)).thenReturn("B1", "A2", "C3");
        when(this.resultSet.getInt(10)).thenReturn(100);
        when(this.resultSet.getInt(11)).thenReturn(280);
    }

}