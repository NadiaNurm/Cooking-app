package com.example.cooking;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.cooking.dataBase.UserDAO;
import com.example.cooking.exceptions.WrongPassword;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Autorization {
    public static int currentUserId;// функция, которая запоминает юзера. Autorization.currentUserId//
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button mainPageEnterButton;

    @FXML
    private TextField mainPageLogin;

    @FXML
    private PasswordField mainPagePassword;

    @FXML
    private Button mainPageRegistrationButton;
    @FXML
    private Label main_error;

    @FXML
    void initialize() {
        mainPageEnterButton.setOnAction(x -> {
           UserDAO userDAO = new UserDAO();
           if(mainPageLogin.getText().isEmpty()){
               main_error.setText("Введите логин");
           }
           if(mainPagePassword.getText().isEmpty()){
                main_error.setText("Введите пароль");
            }
           try {
               if(!userDAO.checkUser(mainPageLogin.getText())){
                   main_error.setText("Пользователя не существует. Зарегистрируйтесь");
               }
               else if(!userDAO.checkPassword(mainPageLogin.getText(),mainPagePassword.getText())){
                   main_error.setText("Введен неверный пароль");
               }
               else if(userDAO.getRole(mainPageLogin.getText(),mainPagePassword.getText()) == Roles.USER){
                   currentUserId = userDAO.getUserId(mainPageLogin.getText(),mainPagePassword.getText());
                   //System.out.println(currentUserId);
                   //openPage("personal_cabinet.fxml");
                   openPage("personal_cabinet.fxml");

                }
                else if(userDAO.getRole(mainPageLogin.getText(),mainPagePassword.getText()) == Roles.ADMIN){
                        currentUserId = 11;
                        openPage("admin.fxml");
                    }

                } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            }
        });
        mainPageRegistrationButton.setOnAction(x -> {
            try {
                openPage("registration.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    public void openPage(String s) throws IOException {
        mainPageEnterButton.getScene().getWindow().hide();
        FXMLLoader fxmlLoader=new FXMLLoader();
        URL o = getClass().getResource(s);
        fxmlLoader.setLocation(o);
        fxmlLoader.load();//
        Parent root=fxmlLoader.getRoot();
        Stage stage=new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
