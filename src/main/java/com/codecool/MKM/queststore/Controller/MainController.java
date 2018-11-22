package com.codecool.MKM.queststore.Controller;

public class MainController {

    private MentorController mentorController;
    private StudentController studentController;
    private CreepyController creepyController;
    private StoreController storeController;

    public MainController() {
        mentorController = new BasicMentorController();
        studentController = new BasicStudentController();
        creepyController = new BasicCreepyController();
        storeController = new BasicStoreController();
        run();

    }

    public void run() {
//        creepyController.addNewMentor();
        storeController.getAllQuests();
    }


    /// add new mentor and add mentor to group are done

}
