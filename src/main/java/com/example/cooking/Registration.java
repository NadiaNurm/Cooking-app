package com.example.cooking;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.cooking.dataBase.UserDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Registration {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button registrationBack;

    @FXML
    private TextField registrationLogin;

    @FXML
    private TextField registrationName;

    @FXML
    private PasswordField registrationPassword;

    @FXML
    private PasswordField registrationPasswordCheck;

    @FXML
    private Button registrationRegister;

    @FXML
    private Label regLabel;


    @FXML
    void initialize() {
        UserDAO userDAO = new UserDAO();
        registrationRegister.setOnAction(x -> {
            try {
                if(registrationPassword.getText().equals(registrationPasswordCheck.getText())){
                    User user = new User(registrationName.getText(),registrationLogin.getText(),registrationPassword.getText());
                    if(userDAO.checkLogin(user.getLogin())){
                        userDAO.save(user);
                        regLabel.setText("Вы зарегистрированы");
                    }
                    else {regLabel.setText("Такой пользователь существует");}
                }
                else {
                    regLabel.setText("Пароли не совпадают");
                }
            }
            catch (SQLException e) {
                    throw new RuntimeException(e);
                }
        });
        registrationBack.setOnAction(x ->{
            try {
                openPage("hello-view.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    public void openPage(String s) throws IOException {
        registrationBack.getScene().getWindow().hide();
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
