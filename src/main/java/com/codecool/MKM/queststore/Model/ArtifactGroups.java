package com.codecool.MKM.queststore.Model;

public class ArtifactGroups extends StoreItems {

    int id;
    private String name;
    private String category;
    int price;
    private String[] members;
    private boolean payStatus;
    private boolean activeStatus;

    public ArtifactGroups(int id,String name, String category, int price, String[] members, boolean payStatus, boolean activeStatus){
        super();
        this.members = members;
        this.payStatus = payStatus;
        this.activeStatus = activeStatus;
    }

    public String[] getMembers() {
        return members;
    }

    public void setMembers(String[] members) {this.members = members;}

    public boolean getPayStatus() {return this.payStatus;}

    public void setPayStatus(boolean payStatus) {this.payStatus=payStatus;}

    public boolean getActiveStatus() {return this.activeStatus;}

    public void setActiveStatus(boolean activeStatus) {this.activeStatus = activeStatus;}
}
