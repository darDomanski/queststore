package com.codecool.MKM.queststore.Model;

public abstract class StoreItems {

    private String name;
    private String category;
    int price;
    int id;

    public StoreItems(){

    }

    public StoreItems(int id,String name, String category, int price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }


    public String getName() {return this.name;}

    public void setName(String name) {this.name = name;}

    public String getCategory() {return this.category;}

    public void setCategory(String category) {this.category= category;}

    public int getPrice() {return this.price;}

    public void setPrice(int price) {this.price = price;}


}
