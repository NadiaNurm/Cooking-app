package com.example.cooking;

import com.example.cooking.dataBase.UserDAO;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;

public class InitData {
    File file;

    InitData(){
    }

    public void openFile(File file) throws SQLException, FileNotFoundException {
        UserDAO userDAO = new UserDAO();
        Connection conn = userDAO.connectToBase();
        ScriptRunner sr = new ScriptRunner(conn);
        //Creating a reader object
        Reader reader = new BufferedReader(new FileReader(file));
        //Running the script
        sr.runScript(reader);
    }
}
