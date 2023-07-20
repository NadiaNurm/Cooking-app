package com.example.cooking;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.CheckBox;

public class Product {
    private int id;
    private String id_recept;
    private String name;


    public Product(String id_recept, String name) {
        this.id_recept = id_recept;
        this.name = name;

    }

    public Product(String name){
        this.name = name;

    }


    public int getId() {
        return id;
    }


    public String getId_recept() {
        return id_recept;
    }

    public void setId_recept(String id_recept) {
        this.id_recept = id_recept;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
