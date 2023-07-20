package com.example.cooking.dataBase;

import java.sql.*;
import java.util.Collection;
import java.util.Objects;

public class DataBaseConnection {
    private static String url = "jdbc:mysql://127.0.0.1:3306/cooking";
    private static String name = "root";
    private static String password = "200815Slava";


    public static void main(String[] args) throws SQLException {

    }

    public static Connection connectToBase() throws SQLException {
        Connection conn = DriverManager.getConnection(url, name, password);
        return conn;
    }

    public static void addUserToBase(String name, String login, String password,String passwordCheck) throws SQLException {
        Connection conn = connectToBase();
        PreparedStatement statement = conn.prepareStatement("INSERT into cooking.users(name,login,password) values(?,?,?)");
        statement.setString(1,name);
        if(checkLogin(login)){
            statement.setString(2,login);
        }
        else{
            throw new SQLException();
        }

        if(checkPassword(password,passwordCheck)){
            statement.setString(3,password);
        }
        statement.executeUpdate();
    }

    public static boolean checkLogin(String login) throws SQLException {
        // String expression = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Connection conn = connectToBase();
        PreparedStatement statement = conn.prepareStatement("SELECT count(*) FROM cooking.users where login = ?;");
        statement.setString(1,login);
        ResultSet resultSet = statement.executeQuery();
        int count = resultSet.getInt(1);
        return count == 0;
    }
    public static boolean checkPassword(String password,String passwordCheck){
        return password.equals(passwordCheck);
    }

}
