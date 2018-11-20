package com.codecool.MKM.queststore.Model;

public abstract class User {

    private String firstName;
    private String nickname;
    private String phone;
    private String email;
    private String group;


    public User() {

    }

    public User(String firstName, String nickname, String phone, String email, String group) {
        this.firstName = firstName;
        this.nickname = nickname;
        this.phone = phone;
        this.email = email;
        this.group = group;
    }

    public String getFirstName(){return this.firstName;}
    public void setFirstName(String firstName){this.firstName=firstName;}

    public String getNickname(){return this.nickname;}
    public void setNickname(String firstName){this.nickname=nickname;}

    public String getPhone(){return this.phone;}
    public void setPhone(String phone){this.phone=phone;}

    public String getEmail(){return this.email;}
    public void setEmail(String email){this.email=email;}

    public String getGroup(){return this.group;}
    public void setGroup(String group){this.group=group;}
}
