package com.codecool.MKM.queststore.Model;

import sun.awt.geom.AreaOp;

public abstract class StoreItems {

    private String name;
    private String category;
    int price;

    public StoreItems(){

    }

    public StoreItems(String name, String category, int price) {
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
