package com.example.cooking;

import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Recepts {
    int id;
    String name;
    //int id_product;
    String time;
    String instruction;
    String path;
    ImageView photo;
    ArrayList<User> users ;
    ArrayList<String> prodList;

    public Recepts(int id, String name, int id_product, String time, String instruction,String path,ImageView photo) {
        this.id = id;
        this.name = name;
       // this.id_product = id_product;
        this.time = time;
        this.instruction = instruction;
        this.path = path;
        this.users = new ArrayList<>();
        this.prodList = new ArrayList<>();
        this.photo = photo;
    }
    public Recepts(String name, String time, String instruction,String path,ImageView photo) {
        this.name = name;
        this.time = time;
        this.instruction = instruction;
        this.path = path;
        this.users = new ArrayList<>();
        this.prodList = new ArrayList<>();
        this.photo = photo;
    }

    public Recepts(String name,String time,String instruction,String path){
        this.name = name;
        this.time = time;
        this.instruction = instruction;
        this.path = path;
        this.users = new ArrayList<>();
        this.prodList = new ArrayList<>();
    }
    public Recepts(){
        this.users = new ArrayList<>();
        this.prodList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void addUser(User user){
        users.add(user);
    }

    public ImageView getPhoto() {
        return photo;
    }

    public void setPhoto(ImageView photo) {
        this.photo = photo;
    }

    public ArrayList<String> getProdList() {
        return prodList;
    }

    public void setProdList(String prod) {
        prodList.add(prod);
    }

    @Override
    public String toString() {
        return "Recepts{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", instruction='" + instruction + '\'' +
                ", path='" + path + '\'' +
                ", photo=" + photo +
                ", users=" + users +
                ", prodList=" + prodList +
                '}';
    }
}
