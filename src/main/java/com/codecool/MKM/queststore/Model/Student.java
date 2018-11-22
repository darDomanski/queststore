package com.codecool.MKM.queststore.Model;

public class Student extends User {

    private int[] quests;
    private int[] artifacts;
    private int[] groupArtifacts;
    private int wallet;
    private int experience;


    public Student(int id, String firstName, String nickname,
                   String phone, String email, String group,
                   int[] quests, int[] artifacts,int[] groupArtifacts,
                   int wallet, int experience) {
        super(id, firstName, nickname, phone, email, group);
        this.quests = quests;
        this.artifacts = artifacts;
        this.groupArtifacts = groupArtifacts;
        this.wallet = wallet;
        this.experience = experience;
    }

    public int[] getQuests(){return this.quests;}
    public void setQuests(int[] quests){this.quests=quests;}

    public int[] getArtifacts(){return this.artifacts;}
    public void setArtifacts(int[] artifacts){this.artifacts=artifacts;}

    public int[] getGroupArtifacts(){return this.groupArtifacts;}
    public void setGroupArtifacts(int[] groupArtifacts){this.groupArtifacts=groupArtifacts;}

    public int getWallet() {return this.wallet;}
    public void setWallet(int wallet) {this.wallet=wallet;}

    public int getExperience() {return this.experience;}
    public void setExperience(int experience) {this.experience=experience;}




}
