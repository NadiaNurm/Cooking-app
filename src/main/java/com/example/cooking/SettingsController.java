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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SettingsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField settingsLogin;

    @FXML
    private TextField settingsName;

    @FXML
    private TextField settingsPswd;

    @FXML
    private Button settingsSave;
    @FXML
    private Label settingsResult;

    @FXML
    private Button settingsBack;
    @FXML
    void initialize() throws SQLException {
        UserDAO userDAO = new UserDAO();
        User user = userDAO.get(Autorization.currentUserId).get();
        settingsName.setText(user.getName());
        settingsLogin.setText(user.getLogin());
        settingsPswd.setText(user.getPassword());
        settingsSave.setOnAction(x ->{
            String [] params = new String[]{settingsName.getText(),settingsLogin.getText(),settingsPswd.getText()};
            try {
                userDAO.update(user,params);
                settingsResult.setText("Данные сохранены");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        settingsBack.setOnAction(x ->{
            try {
                openPage("personal_cabinet.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    public void openPage(String s) throws IOException {
        settingsBack.getScene().getWindow().hide();
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
