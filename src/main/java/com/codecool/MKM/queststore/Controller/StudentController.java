package com.codecool.MKM.queststore.Controller;

import com.codecool.MKM.queststore.Model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentController {

    List<String> seeVallet(Integer id);
    void buyArtifactSingle();
    void buyArtifactGroup();
    Integer checkLevelOfExp(Integer id);
    Optional<Student> getStudentByName(String name);

}