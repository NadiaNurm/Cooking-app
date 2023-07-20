
package com.example.cooking;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ReceptController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView recept_image;

    @FXML
    private Label recept_instruction;

    @FXML
    private Label recept_name;

    @FXML
    private TableView<Product> recept_products;

    @FXML
    private Label recept_time;

    @FXML
    private TableColumn<Product, String> column_product;
    @FXML
    private Button recept_back;

    @FXML
    void initialize() throws SQLException, FileNotFoundException {
        UserDAO userDAO = new UserDAO();
        String name = AllReceptsController.r.getName();
        Recepts r = userDAO.getRecept(name);
        recept_name.setText(name);
        recept_time.setText(r.getTime());
        recept_instruction.setText(r.getInstruction());
        Set<Product> productSet = userDAO.getProductsRecept(name);
        ObservableList<Product> products = FXCollections.observableArrayList(productSet);
        column_product.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        recept_image.setImage(r.getPhoto().getImage());
        recept_products.setItems(products);
       // Image image = new Image(getClass().getResourceAsStream("C:\\\\Users\\\\User\\\\IdeaProjects\\\\cooking\\\\src\\\\main\\\\java\\\\com\\\\example\\\\cooking\\\\1.jpg")); не работает
        //Image image1 = new Image(new FileInputStream("C:\\Users\\User\\IdeaProjects\\cooking\\src\\main\\java\\com\\example\\cooking\\1.jpg")); //работает,но сложно
        //recept_image.setImage(image1);
        recept_back.setOnAction(x -> {
            try {
                openPage("allRecepts.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    public void openPage(String s) throws IOException {
        recept_back.getScene().getWindow().hide();
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

