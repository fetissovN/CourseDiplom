package sample;


import WebService.Project;
import WebService.User;
import WebService.UserWebService;
import WebService.WebService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserController {
    UserWebService userWebService = new UserWebService();

    User user = null;
    ObservableList<Project> list = FXCollections.observableArrayList();

    @FXML
    private Label labelName;
    @FXML
    private Label labelLogin;
    @FXML
    private Label labelProc;
    @FXML
    private TableView<Project> tableView;
    @FXML
    private TableColumn<Project, String> nameCol;
    @FXML
    private TableColumn<Project, String> descCol;
    @FXML
    private TableColumn<Project, Integer> statusCol;
    @FXML
    private Label labelProjecting;
    @FXML
    private Label labelDesign;
    @FXML
    private Label labelLayout;
    @FXML
    private Label labelBack;
    @FXML
    private Label labelTesting;
    @FXML
    private Label labelProm;

    @FXML
    private Label labelProjecting1;
    @FXML
    private Label labelDesign1;
    @FXML
    private Label labelLayout1;
    @FXML
    private Label labelBack1;
    @FXML
    private Label labelTesting1;
    @FXML
    private Label labelProm1;



    public UserController() {

    }

    @FXML
    public void initialize(){
        nameCol.setCellValueFactory(new PropertyValueFactory<>("project_name"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("project_desc"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));


    }

    public void show(User userIn) {
        this.user = userIn;
        labelName.setText(user.getUser_name());
        labelLogin.setText(user.getUser_login());
        labelProc.setText(String.valueOf(user.getProcentage()));

    }

    public void showCurrentPr(){
        list.remove(0,list.size());
        list.addAll(userWebService.getListOfMyPr(user));
        tableView.setItems(list);
    }

    public void showOldPr(){
        list.remove(0,list.size());
        list.addAll(userWebService.getListOfOldPr(user));
        tableView.setItems(list);
    }

    public void onClickList(){
        labelProjecting.setText("Projecting : -");
        labelDesign.setText("Design : -");
        labelLayout.setText("Layout : -");
        labelBack.setText("Back-end : -");
        labelTesting.setText("Testing : -");
        labelProm.setText("Promotion : -");

        WebService webService = new WebService();
        ObservableList<User> listU = FXCollections.observableArrayList();
        listU.addAll(webService.getListOfUsersDB());
        Project project = tableView.getSelectionModel().getSelectedItem();
        HashMap[] h = userWebService.sdfsdf(project.getId());
        HashMap<Integer, Integer> hashMap = h[0];
        HashMap<Integer, Integer> hashMap2 = h[1];
        for (Map.Entry<Integer, Integer> entry1 : hashMap.entrySet()) {
            if (entry1.getKey() == 1) {
                for (User u : listU) {
                    if (u.getId() == entry1.getValue()) {
                        String name1 = u.getUser_name();
                        if (labelName.getText().equals(name1)){
                            labelProjecting.setText("Projecting : My job!");
                        }else {
                            labelProjecting.setText("Projecting : "+ name1 );
                        }
                    }
                }
                labelProjecting1.setText(String.valueOf(hashMap2.get(1))+" h");
            }
            if (entry1.getKey() == 2) {
                for (User u : listU) {
                    if (u.getId() == entry1.getValue()) {
                        String name2 = u.getUser_name();
                        if (labelName.getText().equals(name2)){
                            labelDesign.setText("Design : My job!");
                        }else {
                            labelDesign.setText("Design : "+ name2 );
                        }
                    }
                }
                labelDesign1.setText(String.valueOf(hashMap2.get(2))+" h");
            }
            if (entry1.getKey() == 3) {
                for (User u : listU) {
                    if (u.getId() == entry1.getValue()) {
                        String name3 = u.getUser_name();
                        if (labelName.getText().equals(name3)){
                            labelLayout.setText("Layout : My job!");
                        }else {
                            labelLayout.setText("Layout : "+ name3);
                        }
                    }
                }
                labelLayout1.setText(String.valueOf(hashMap2.get(3))+" h");
            }
            if (entry1.getKey() == 4) {
                for (User u : listU) {
                    if (u.getId() == entry1.getValue()) {
                        String name4 = u.getUser_name();
                        if (labelName.getText().equals(name4)){
                            labelBack.setText("Back-end : My job!");
                        }else {
                            labelBack.setText("Back-end : "+ name4);
                        }
                    }
                }
                labelBack1.setText(String.valueOf(hashMap2.get(4))+" h");
            }
            if (entry1.getKey() == 5) {
                for (User u : listU) {
                    if (u.getId() == entry1.getValue()) {
                        String name5 = u.getUser_name();
                        if (labelName.getText().equals(name5)){
                            labelTesting.setText("Testing : My job!");
                        }else {
                            labelTesting.setText("Testing : "+ name5);
                        }
                    }
                }
                labelTesting1.setText(String.valueOf(hashMap2.get(5))+" h");
            }
            if (entry1.getKey() == 6) {
                for (User u : listU) {
                    if (u.getId() == entry1.getValue()) {
                        String name6 = u.getUser_name();
                        if (labelName.getText().equals(name6)){
                            labelProm.setText("Promotion : My job!");
                        }else {
                            labelProm.setText("Promotion : "+ name6);
                        }
                    }
                }
                labelProm1.setText(String.valueOf(hashMap2.get(6))+" h");
            }
        }
    }
    public void logOut() {
        try {
            Stage stagePr = (Stage) tableView.getScene().getWindow() ;
            stagePr.close();
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            stage.setTitle("Log In Web Service");
            stage.setScene(new Scene(root, 300, 260));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

