package com.example.cooking;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Set;

import com.example.cooking.dataBase.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PersonalController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button personal_allRec;

    @FXML
    private Button personal_exit;

    @FXML
    private Text personal_favorite;

    @FXML
    private TableView<Recepts> personal_favoriteRec;

    @FXML
    private TableColumn<Recepts, Image> personal_imageCol;

    @FXML
    private TableColumn<Recepts, String> personal_nameCol;

    @FXML
    private Button personal_settings;

    @FXML
    private TableColumn<Recepts,String> personal_timeCol;
    @FXML
    private TableColumn<Recepts, String> personal_producns;
    @FXML
    private TableColumn<Recepts, String> personal_instruction;
    @FXML
    void initialize() throws SQLException, FileNotFoundException {
        UserDAO userDAO = new UserDAO();
        ArrayList<Recepts> receptsList = userDAO.getFavRec(Autorization.currentUserId);
        //ArrayList<Recepts> receptsList = userDAO.getFavRec(6);
        ObservableList<Recepts> recepts = FXCollections.observableArrayList(receptsList);
        personal_nameCol.setCellValueFactory(new PropertyValueFactory<Recepts, String>("name"));
        personal_timeCol.setCellValueFactory(new PropertyValueFactory<Recepts, String>("time"));
        personal_instruction.setCellValueFactory(new PropertyValueFactory<Recepts, String>("instruction"));
        personal_imageCol.setCellValueFactory(new PropertyValueFactory<>("photo"));
        personal_producns.setCellValueFactory(new PropertyValueFactory<>("prodList"));
        personal_favoriteRec.setItems(recepts);
        personal_allRec.setOnAction(x->{
            try {
                openPage("allRecepts.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        personal_exit.setOnAction(x->{
            try {
                openPage("hello-view.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        personal_settings.setOnAction(x->{
            try {
                openPage("settings.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void openPage(String s) throws IOException {
        personal_settings.getScene().getWindow().hide();
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
