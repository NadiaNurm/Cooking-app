package com.example.cooking.dataBase;

import com.example.cooking.Roles;
import com.example.cooking.Product;
import com.example.cooking.Recepts;
import com.example.cooking.User;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.*;

public class UserDAO implements Dao<User>{

    public static Connection connectToBase() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cooking", "root", "200815Slava");
    }
    public static Connection connectToBase(String path, String name, String password) throws SQLException {
        return DriverManager.getConnection(path, name, password);
    }
    @Override
    public Optional<User> get(long id) throws SQLException {
        Connection conn = connectToBase();
        PreparedStatement statement = conn.prepareStatement("select name,login,password from cooking.users where id = ?");
        statement.setInt(1, (int) id);
        ResultSet result = statement.executeQuery();
        User u = new User();
        while (result.next()){
            String name = result.getString(1);
            String login = result.getString(2);
            String password = result.getString(3);
            u.setId(String.valueOf(id));
            u.setName(name);
            u.setLogin(login);
            u.setPassword(password);
    }
        return Optional.of(u);
    }


    @Override
    public List<User> getAll() throws SQLException {
        List<User> users = new ArrayList<>();
        Connection conn = connectToBase();
        PreparedStatement statement = conn.prepareStatement("select name from users");
        ResultSet result = statement.executeQuery();
        while (result.next()){
            String r1 = result.getString(1);
            String r2 = result.getString(2);
            String r3 = result.getString(3);
            String r4 = result.getString(4);
            users.add(new User(r1,r2,r3,r4));
        }
        return users;
    }

    @Override
    public void save(User user) throws SQLException {
        Connection conn = connectToBase();
        PreparedStatement statement = conn.prepareStatement("INSERT into cooking.users(name,login,password,role) values(?,?,?,?)");
        if (checkLogin(user.getLogin())){
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.setString(4, "user");
            statement.executeUpdate();
        }
    }

    public static boolean checkLogin(String login) throws SQLException {
        // String expression = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Connection conn = connectToBase();
        PreparedStatement statement = conn.prepareStatement("SELECT count(*) FROM cooking.users where login = ?;");
        statement.setString(1, login);
        ResultSet resultSet = statement.executeQuery();
        int count = -1;
        while (resultSet.next()) {
            count = resultSet.getInt(1);
        }
        return count == 0;
    }

    @Override
    public void update(User user, String[] params) throws SQLException {
        Connection conn = connectToBase();
        PreparedStatement statement = conn.prepareStatement("update cooking.users set name = ?, login = ? , password = ? where id = ?;");
        statement.setString(1, params[0]);
        statement.setString(2, params[1]);
        statement.setString(3, params[2]);
      //  statement.setInt(4, Integer.parseInt(user.getId()));
        statement.setString(4, user.getId());
        statement.executeUpdate();
    }

    @Override
    public void delete(User user) throws SQLException {
        Connection conn = connectToBase();
        PreparedStatement statement = conn.prepareStatement("delete from users where id = ?");
        statement.setString(1,user.getId());
        statement.executeUpdate();
    }

    public Roles getRole(String login, String password) throws SQLException {
        Connection conn = connectToBase();
        PreparedStatement statement = conn.prepareStatement("select role from users where login=? and password =?");
        statement.setString(1, login);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();
        String r = null;
        while (resultSet.next()) {
            String s = resultSet.getString(1);
            System.out.println(s);
            r = resultSet.getString(1);
        }
        if(r.equals("user")) return Roles.USER;
        if(r.equals("admin")) return Roles.ADMIN;
        return null;
    }

    public boolean checkAdmin(int id) throws SQLException {
        Connection conn = connectToBase();
        PreparedStatement statement = conn.prepareStatement("select count(*) from cooking.users where id=? and role=\"admin\"");
        statement.setInt(1,id);
        ResultSet resultSet = statement.executeQuery();
        int c = -1;
        while (resultSet.next()) {
            c=resultSet.getInt(1);
            if(c==1){
                return true;
            }
        }
        return false;
    }

    public boolean checkUser(String login) throws SQLException {
        Connection conn = connectToBase();
        PreparedStatement statement = conn.prepareStatement("select * from users where login=?");
        statement.setString(1, login);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            if(resultSet.getString(1)!=null){
                return true;
            }
        }
        return false;
    }

    public boolean checkPassword(String login, String password) throws SQLException {
        Connection conn = connectToBase();
        PreparedStatement statement = conn.prepareStatement("select * from users where login=? and password=?");
        statement.setString(1, login);
        statement.setString(2, password);
        ResultSet result = statement.executeQuery();
        while (result.next()){
            if (!result.getString(1).isEmpty()){
                return true;
            }
        }
        return false;
    }

    public Set<Product> getProducts() throws SQLException {
        ArrayList<Product> products = new ArrayList<>();
        Connection conn = connectToBase();
        PreparedStatement statement = conn.prepareStatement("SELECT distinct name FROM products;");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            String name = resultSet.getString(1);
            products.add(new Product(name));
        }
        return new HashSet<>(products);
    }

    public void setRecept(Set<String> products, Recepts recept) throws SQLException {
        Connection conn = connectToBase();
        PreparedStatement statement1 = conn.prepareStatement("insert recept(name,time,instruction,path) values(?,  ?, ?, ?);" );
        statement1.setString(1, recept.getName());
        statement1.setString(2, recept.getTime());
        statement1.setString(3, recept.getInstruction());
        statement1.setString(4, recept.getPath());
        statement1.executeUpdate();
        PreparedStatement statement2 = conn.prepareStatement("SELECT LAST_INSERT_ID();");
        ResultSet resultSet1 = statement2.executeQuery();
        int id_recept = 0;
        while (resultSet1.next()){
            id_recept = resultSet1.getInt(1);
        }
        for (String s : products ){
            PreparedStatement statement3 = conn.prepareStatement("insert products(id_recept,name) values(?,?);");
            statement3.setInt(1,id_recept);
            statement3.setString(2,s);
            statement3.executeUpdate();
        }
    }
    public Recepts getRecept(String name) throws SQLException, FileNotFoundException {
        Connection conn = connectToBase();
        PreparedStatement statement = conn.prepareStatement("select * from recept where name=?;" );
        statement.setString(1,name);
        ResultSet resultSet = statement.executeQuery();
        Recepts recept = new Recepts();
        while(resultSet.next()){
            String time = resultSet.getString(3);
            String instruction = resultSet.getString(4);
            String path = resultSet.getString(5);
            try{
                ImageView imageView = new ImageView(new Image(new FileInputStream(path)));
                imageView.setFitHeight(200);
                imageView.setFitWidth(200);
                recept.setPhoto(imageView);
            } catch (Exception e) {
                ImageView imageView = new ImageView(new Image(new FileInputStream("C:\\Users\\User\\IdeaProjects\\cooking\\src\\main\\java\\com\\example\\cooking\\1.jpg")));
                imageView.setFitHeight(200);
                imageView.setFitWidth(200);
                recept.setPhoto(imageView);
            }
            recept.setName(name);
            recept.setTime(time);
            recept.setInstruction(instruction);
        }
        return recept;
    }

    public Set<Product> getProductsRecept(String name) throws SQLException {
        ArrayList<Product> products = new ArrayList<>();
        Connection conn = connectToBase();
        PreparedStatement statement = conn.prepareStatement("select p.name from recept r join products p on r.id = p.id_recept where p.id_recept = (select id from recept where name = ?);");
        statement.setString(1,name);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            products.add(new Product(resultSet.getString(1)));
        }
        return new HashSet<>(products);
    }

    public Set<Recepts> getAllRecepts() throws SQLException, FileNotFoundException {
        ArrayList<Recepts> receptes = new ArrayList<>();
        Connection conn = connectToBase();
        PreparedStatement statement = conn.prepareStatement("select id,name,time,path from recept;" );
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()){
            Recepts recept = new Recepts();
            String id = resultSet.getString("id");
            String name = resultSet.getString("name");
            String time = resultSet.getString("time");
            String path = resultSet.getString("path");
            recept.setId(Integer.parseInt(id));
            recept.setName(name);
            recept.setTime(time);
            try{
                ImageView imageView = new ImageView(new Image(new FileInputStream(path)));
                imageView.setFitHeight(200);
                imageView.setFitWidth(200);
                recept.setPhoto(imageView);
            } catch (Exception e) {
                ImageView imageView = new ImageView(new Image(new FileInputStream("C:\\Users\\User\\IdeaProjects\\cooking\\src\\main\\java\\com\\example\\cooking\\1.jpg")));
                imageView.setFitHeight(200);
                imageView.setFitWidth(200);
                recept.setPhoto(imageView);
            }
            recept.setPath(path);
            receptes.add(recept);
        }
        return new HashSet<>(receptes);
    }

    public boolean setFavorite(int user_id,int recept_id) throws SQLException {
        Connection conn = connectToBase();
        PreparedStatement statement1 = conn.prepareStatement("select count(*) from cooking.user_recepts where user_id=? and recept_id=?;");
        statement1.setInt(1,user_id);
        statement1.setInt(2,recept_id);
        ResultSet resultSet = statement1.executeQuery();
        int counter=-1;
        while (resultSet.next()){
            counter=resultSet.getInt(1);
        }
        if(counter==0){
            PreparedStatement statement2 = conn.prepareStatement("insert into cooking.user_recepts values(?,?);");
            statement2.setInt(1,user_id);
            statement2.setInt(2,recept_id);
            statement2.executeUpdate();
            return true;
        }
        return false;
    }

    public int  getUserId (String login, String password) throws SQLException {
        Connection conn = connectToBase();
        PreparedStatement statement = conn.prepareStatement("select id from users where login=? and password=?");
        statement.setString(1, login);
        statement.setString(2, password);
        ResultSet result = statement.executeQuery();
        int id = -1;
        while (result.next()) {
            id =  result.getInt(1);
        }
        return id;
    }

    public ArrayList<Recepts> getFavRec(int user_id) throws SQLException, FileNotFoundException {
        Connection conn = connectToBase();
        PreparedStatement statement = conn.prepareStatement("select r.id,r.name,r.time,r.instruction,r.path,p.name from users u join user_recepts ur on u.id=ur.user_id join recept r on ur.recept_id=r.id join products p on r.id = p.id_recept where u.id=?;");
        statement.setInt(1, user_id);
        ResultSet resultSet = statement.executeQuery();
        ArrayList<Recepts> receptsList = new ArrayList<>();
        while (resultSet.next()){
            int recept_id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String time = resultSet.getString(3);
            String instruction = resultSet.getString(4);
            String path = resultSet.getString(5);
            String product = resultSet.getString(6);
            if(receptsList.stream().noneMatch(x -> x.getId()==recept_id)){
                    Recepts r = new Recepts();
                    r.setId(recept_id);
                    r.setName(name);
                    r.setTime(time);
                    r.setInstruction(instruction);
                    try{
                    ImageView imageView = new ImageView(new Image(new FileInputStream(path)));
                    imageView.setFitHeight(200);
                    imageView.setFitWidth(200);
                    r.setPhoto(imageView);
                    } catch (Exception e) {
                    ImageView imageView = new ImageView(new Image(new FileInputStream("C:\\Users\\User\\IdeaProjects\\cooking\\src\\main\\java\\com\\example\\cooking\\1.jpg")));
                    imageView.setFitHeight(200);
                    imageView.setFitWidth(200);
                    r.setPhoto(imageView);
                    }
                    r.setPath(path);
                    r.setProdList(product);
                    receptsList.add(r);
            }
            else {
                Recepts r2 = receptsList.stream().filter(x -> x.getId()==recept_id).findFirst().get();
                int index = receptsList.indexOf(r2);
                r2.setProdList(product);
                receptsList.set(index,r2);
            }
        }
        return receptsList;
    }

}
