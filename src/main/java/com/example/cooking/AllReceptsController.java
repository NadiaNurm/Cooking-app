package com.example.cooking;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Set;

import com.example.cooking.dataBase.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Callback;

public class AllReceptsController {
        @FXML
        private Button allRecBack;
        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;
        @FXML
        private Label allRecInfo;

        @FXML
        private TableColumn<Recepts, String> allRecNameCol;

        @FXML
        private TableView<Recepts> allRecTable;

        @FXML
        private TableColumn<Recepts, String> allRecTimeCol;
        @FXML
        private TableColumn<Recepts, Image> allRecImageCol;
        @FXML
        private TableColumn<Recepts, Integer> allRecId;
        public static Recepts r;

        @FXML
        void initialize() throws SQLException, IOException {
                UserDAO userDAO = new UserDAO();
                Set<Recepts> receptsSet = userDAO.getAllRecepts();
                ObservableList<Recepts> recepts = FXCollections.observableArrayList(receptsSet);
                allRecId.setCellValueFactory(new PropertyValueFactory<Recepts, Integer>("id"));
                allRecId.setVisible(false);
                allRecNameCol.setCellValueFactory(new PropertyValueFactory<Recepts, String>("name"));
                allRecTimeCol.setCellValueFactory(new PropertyValueFactory<Recepts, String>("time"));
                allRecImageCol.setPrefWidth(220);
                allRecImageCol.setCellValueFactory(new PropertyValueFactory<>("photo"));
                addButtonToTable();
                allRecTable.setItems(recepts);
                openButtonToTable();
                allRecBack.setOnAction(x -> {
                        try {
                                if(userDAO.checkAdmin(Autorization.currentUserId)){
                                        openPage("admin.fxml");
                                }
                                else {
                                        openPage("personal_cabinet.fxml");
                                }
                        } catch (SQLException e) {
                                throw new RuntimeException(e);
                        } catch (IOException e) {
                                throw new RuntimeException(e);
                        }
                });




        }
        private void addButtonToTable() {
                TableColumn<Recepts, Void> colBtn = new TableColumn("Избранное");
                Callback<TableColumn<Recepts, Void>, TableCell<Recepts, Void>> cellFactory = new Callback<TableColumn<Recepts, Void>, TableCell<Recepts, Void>>() {
                        @Override
                        public TableCell<Recepts, Void> call(final TableColumn<Recepts, Void> param) {
                                final TableCell<Recepts, Void> cell = new TableCell<Recepts, Void>() {

                                        private final Button btn = new Button("Add");

                                        {
                                                btn.setOnAction((ActionEvent event) -> {
                                                        Recepts rec = getTableView().getItems().get(getIndex());
                                                        UserDAO userDAO = new UserDAO();
                                                        int user_id=Autorization.currentUserId;
                                                        int recept_id = rec.id;
                                                        try {
                                                                if(userDAO.setFavorite(user_id,recept_id)){
                                                                        allRecInfo.setText("Рецепт добавлен в избранное!");
                                                                }
                                                                else {
                                                                        allRecInfo.setText("Рецепт уже есть в избранном!");
                                                                }
                                                        } catch (SQLException e) {
                                                                throw new RuntimeException(e);
                                                        }
                                                });
                                        }
                                        @Override
                                        public void updateItem(Void item, boolean empty) {
                                                super.updateItem(item, empty);
                                                if (empty) {
                                                        setGraphic(null);
                                                } else {
                                                        setGraphic(btn);
                                                }
                                        }
                                };
                                return cell;
                        }
                };
                colBtn.setCellFactory(cellFactory);
                allRecTable.getColumns().add(colBtn);
        }
        private void openButtonToTable() {
                TableColumn<Recepts, Void> colBtn = new TableColumn("Развернуть");
                Callback<TableColumn<Recepts, Void>, TableCell<Recepts, Void>> cellFactory = new Callback<TableColumn<Recepts, Void>, TableCell<Recepts, Void>>() {
                        @Override
                        public TableCell<Recepts, Void> call(final TableColumn<Recepts, Void> param) {
                                final TableCell<Recepts, Void> cell = new TableCell<Recepts, Void>() {
                                        private final Button btn = new Button("Open");
                                        {
                                                btn.setOnAction((ActionEvent event) -> {
                                                        Recepts rec = getTableView().getItems().get(getIndex());
                                                        r = rec;
                                                        try {
                                                                openPage("recept.fxml");

                                                        } catch (IOException e) {
                                                                throw new RuntimeException(e);
                                                        }
                                                });
                                        }
                                        @Override
                                        public void updateItem(Void item, boolean empty) {
                                                super.updateItem(item, empty);
                                                if (empty) {
                                                        setGraphic(null);
                                                } else {
                                                        setGraphic(btn);
                                                }
                                        }
                                };
                                return cell;
                        }
                };
                colBtn.setCellFactory(cellFactory);
                allRecTable.getColumns().add(colBtn);
        }
        public void openPage(String s) throws IOException {
                allRecBack.getScene().getWindow().hide();
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





