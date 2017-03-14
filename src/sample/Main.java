package sample;

import WebService.Password;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Log In Web Service");
        primaryStage.setScene(new Scene(root, 300, 260));
        primaryStage.show();
//        Password password = new Password();
//        String s = password.hashPassword("admin");
//        System.out.println(s);
    }


    public static void main(String[] args) {
        launch(args);
    }


}
