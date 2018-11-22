package com.codecool.MKM.queststore.Controller;

import java.util.List;

public interface StudentController {

    List<String> seeVallet(String nickName);
    void buyArtifactSingle();
    void buyArtifactGroup();
    Integer checkLevelOfExp(String nickName);

}
