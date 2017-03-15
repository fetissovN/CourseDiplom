package sample;


import WebService.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Controller {
    @FXML
    private Button logIn;
    @FXML
    private TextField name;
    @FXML
    private PasswordField pass;
    @FXML
    private Label label;

    User user = null;

    public User getUser() {
        return user;
    }

    public void closeLogIn(){

    }

    public void showAdminWindow(){
        Stage stagePr = (Stage) logIn.getScene().getWindow();
        stagePr.close();
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("admin.fxml"));
            stage.setTitle("");
//            stage.setResizable(false);
            stage.setScene(new Scene(root,900,600));
//            stage.initModality(Modality.WINDOW_MODAL);
//            stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
             stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void checkLogin(){
        String nameS = name.getText();
        String passwordS = pass.getText();
        DB db = new DB();
        db.connect();
        ResultSet rs = db.getSetForPassByName(nameS);

//        String pw_hash = BCrypt.hashpw(nameS, BCrypt.gensalt());
//        System.out.println(pw_hash);
        try {
            if (rs.next()){
                if (BCrypt.checkpw(passwordS, rs.getString("user_password"))&& rs.getInt("procentage")==0){
                    System.out.println("It matches");
                    showAdminWindow();
                }else if (BCrypt.checkpw(passwordS, rs.getString("user_password"))&& rs.getInt("procentage")!=0){
                    System.out.println("It matches user");
                    showUserWindow(new User(rs.getInt("id_user"),rs.getString("user_name"),rs.getString("user_login"),rs.getInt("procentage"),rs.getBoolean("is_busy")));
//                    showUserWindow();
                }else{
                    System.out.println("It does not match");
                    label.setVisible(true);
                    pass.clear();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showUserWindow(User userIn){

        Stage stagePr = (Stage) logIn.getScene().getWindow();
        stagePr.close();
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load(getClass().getResource("user.fxml").openStream());
            UserController userController = (UserController)loader.getController();
            userController.show(userIn);
            stage.setTitle("");
//            stage.setResizable(false);
            stage.setScene(new Scene(root,450,450));
//            stage.initModality(Modality.WINDOW_MODAL);
//            stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            stage.show();
//            UserController userController = new UserController(user);
//            userController.initialize(user);

//            userController.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
