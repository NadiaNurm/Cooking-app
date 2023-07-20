package com.example.cooking;

import com.example.cooking.Roles;

public class Admin {
    private String id;
    private String name;
    private String login;
    private String password;
    private Enum role;

    public Admin(String id, String name, String login, String password) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.role = Roles.ADMIN;
    }

    public Admin(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public String getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Enum getRole() {
        return role;
    }

}
