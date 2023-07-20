package com.example.cooking;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

import com.example.cooking.dataBase.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

public class AdminController {
    static Set<String> productsList = new HashSet<>();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Product> admin_addProducts;

    @FXML
    private Button admin_addRecept;

    @FXML
    private Button admin_exit;

    @FXML
    private TextField admin_fieldRecept;

    @FXML
    private TextField admin_instruction;

    @FXML
    private Label admin_labelNameRecept;

    @FXML
    private Button admin_listReceptes;

    @FXML
    private TextField admin_time;

    @FXML
    private TableColumn<Product,String> admin_tableProduct;
    @FXML
    private Button admin_addImage;
    @FXML
    void initialize() throws SQLException {
        UserDAO userDAO = new UserDAO();
        Set<Product> productSet = userDAO.getProducts();
        //admin_addProducts.getColumns().addAll(admin_tableProduct,admin_tableChoose);
        //productSet.iterator().forEachRemaining(System.out::println);
        ObservableList<Product> products = FXCollections.observableArrayList(productSet);
        admin_tableProduct.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        addButtonToTable();
      // admin_tableProduct.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
        admin_addProducts.setItems(products);
        final String[] path1 = {""};
        // добавляем картинку
        final FileChooser[] fileChooser = {new FileChooser()};
        admin_addImage.setOnAction(
                new EventHandler<ActionEvent>() {
                    String path = "";
                    @Override
                    public void handle(final ActionEvent e) {
                        Stage stage = (Stage)admin_addImage.getScene().getWindow();
                        File file = fileChooser[0].showOpenDialog(stage);
                        if (file != null) {;
                            System.out.println(file);
                            path = file.getAbsolutePath();
                            path1[0] = path;
                        }
                    }
                });
        admin_addRecept.setOnAction(x -> {
            String nameRec = admin_fieldRecept.getText();
            String timeRec =  admin_time.getText();
            String instructionRec = admin_instruction.getText();
            Recepts recept = new Recepts(nameRec,timeRec,instructionRec, path1[0]);
            //System.out.println(nameRec+" "+timeRec+" "+instructionRec);
            //System.out.println(productArrayList.size());
            try {
                userDAO.setRecept(productsList,recept);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            productsList.clear();
        });
        admin_exit.setOnAction(x ->{
            try {
                openPage("hello-view.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        admin_listReceptes.setOnAction(x->{
            try {
                openPage("allRecepts.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


    }

    private void addButtonToTable() {
        TableColumn<Product, Void> colBtn = new TableColumn("Button Column");
        Callback<TableColumn<Product, Void>, TableCell<Product, Void>> cellFactory = new Callback<TableColumn<Product, Void>, TableCell<Product, Void>>() {
            @Override
            public TableCell<Product, Void> call(final TableColumn<Product, Void> param) {
                final TableCell<Product, Void> cell = new TableCell<Product, Void>() {

                    private final Button btn = new Button("Add");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Product product = getTableView().getItems().get(getIndex());
                            productsList.add(product.getName());
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
        admin_addProducts.getColumns().add(colBtn);
    }

    public void openPage(String s) throws IOException {
        admin_exit.getScene().getWindow().hide();
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







