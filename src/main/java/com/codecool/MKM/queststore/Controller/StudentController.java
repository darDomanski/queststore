package com.codecool.MKM.queststore.Controller;

import java.util.List;

public interface StudentController {

    List<String> seeVallet(Integer id);
    void buyArtifactSingle();
    void buyArtifactGroup();
    Integer checkLevelOfExp(Integer id);

}