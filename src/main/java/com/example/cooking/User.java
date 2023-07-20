package com.example.cooking;

import com.example.cooking.Roles;
import com.example.cooking.exceptions.WrongPassword;

public class User {
    private String id;
    private String name;
    private String login;
    private String password;
    private Roles role;

    public User(String id, String name, String login, String password) {
        this.id = id;
        //Connection conn = UserDAO.connectToBase();
        //PreparedStatement statement = conn.prepareStatement("SELECT id FROM cooking.users WHERE id=(SELECT MAX(id) FROM cooking.users);");
        //ResultSet resultSet = statement.executeQuery();
        this.name = name;
        this.login = login;
        this.password = password;
        this.role = Roles.USER;
    }

    public User(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.role = Roles.USER;
    }
    public User(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
