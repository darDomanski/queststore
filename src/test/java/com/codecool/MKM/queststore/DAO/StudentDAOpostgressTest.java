package com.codecool.MKM.queststore.DAO;

import com.codecool.MKM.queststore.DAO.DBConnector.DBConnector;
import com.codecool.MKM.queststore.Model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.sql.Connection;
import static org.mockito.Mockito.*;


class StudentDAOpostgressTest {
    private StudentDAOpostgress studentDAOpostgress;

    @Mock
    Student student;
    DAO dao;
    DBConnector connector;
    Connection connection;

    @BeforeEach
    public void setUp() {

        this.student = mock(Student.class);
        this.dao = mock(DAO.class);
        this.connector = mock(DBConnector.class);
        this.studentDAOpostgress = new StudentDAOpostgress(this.connector);
    }

    @Test
    public void testAddStudentToDataBase() {

        when(student.getQuests()).thenReturn(new int[] {1, 44, 3, 6, 7, 54});
        when(student.getArtifacts()).thenReturn(new  int[] {61, 3, 4, 6, 5, 44});
        when(student.getGroupArtifacts()).thenReturn(new int[] {51, 24, 3, 6, 7, 43});
        when(student.getFirstName()).thenReturn("Krzys");
        when(student.getNickname()).thenReturn("Puchatek");
        when(student.getPhone()).thenReturn("444-333-222");
        when(student.getEmail()).thenReturn("mis@oe.pl");
        when(student.getGroup()).thenReturn("B1");
        when(student.getWallet()).thenReturn(100);
        when(student.getExperience()).thenReturn(280);

        //when(this.connector.getConnection()).thenReturn(this.connection);

        //when(studentDAOpostgress.openDataBase()).thenReturn(this.connection);
        //when(dao.editDataBase(connection, anyString())).thenReturn(true);
        // verify(dao.editDataBase(connection, anyString()));

        when(this.dao.openDataBase()).thenReturn(this.connection);

        this.dao.openDataBase();

        verify(this.dao, times(1)).openDataBase();

    }

}