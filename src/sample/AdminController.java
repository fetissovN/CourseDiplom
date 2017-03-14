package sample;

import WebService.Project;
import WebService.User;
import WebService.WebService;
import WebService.HistoryWebService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;


import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;


public class AdminController implements EventHandler<ActionEvent> {
    WebService webService = new WebService();
    ObservableList<Project> listPr = FXCollections.observableArrayList();
    ObservableList<User> listU = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Project, Integer> numberCol;
    @FXML
    private TableColumn<Project, String> nameCol;
    @FXML
    private TableColumn<Project, String> descCol;
    @FXML
    private TableColumn<Project, Integer> statusCol;
    @FXML
    private TableColumn<Project, Integer> priceCol;
    @FXML
    private TableView<Project> tableViewP;

    @FXML
    private TableColumn<User, Integer> idUCol;
    @FXML
    private TableColumn<User, String> nameUserCol;
    @FXML
    private TableColumn<User, Integer> procCol;
    @FXML
    private TableColumn<User, Integer> busy;
    @FXML
    private TableView<User> tableViewU;
    @FXML
    private ChoiceBox<String> choiceBox1;
    @FXML
    private ChoiceBox<String> choiceBox2;
    @FXML
    private ChoiceBox<String> choiceBox3;
    @FXML
    private ChoiceBox<String> choiceBox4;
    @FXML
    private ChoiceBox<String> choiceBox5;
    @FXML
    private ChoiceBox<String> choiceBox6;

    @FXML
    private TextField tf1;
    @FXML
    private TextField tf2;
    @FXML
    private TextField tf3;
    @FXML
    private TextField tf4;
    @FXML
    private TextField tf5;
    @FXML
    private TextField tf6;

    @FXML
    private TextField tfName;
    @FXML
    private TextField tfDesc;
    @FXML
    private Label label;
    @FXML
    private TextField tfName1;
    @FXML
    private TextField tfDesc1;
    @FXML
    private ChoiceBox<String> choiceBox11;
    @FXML
    private ChoiceBox<String> choiceBox21;
    @FXML
    private ChoiceBox<String> choiceBox31;
    @FXML
    private ChoiceBox<String> choiceBox41;
    @FXML
    private ChoiceBox<String> choiceBox51;
    @FXML
    private ChoiceBox<String> choiceBox61;

    @FXML
    private TextField tf11;
    @FXML
    private TextField tf21;
    @FXML
    private TextField tf31;
    @FXML
    private TextField tf41;
    @FXML
    private TextField tf51;
    @FXML
    private TextField tf61;

    @FXML
    private Slider slider;

    @FXML
    private Pane addPPane;
    @FXML
    private TextField addPName;
    @FXML
    private TextField addPLogin;
    @FXML
    private TextField addPPassword;
    @FXML
    private TextField addPProcentage;
    @FXML
    private CheckBox cbAddProjecting;
    @FXML
    private CheckBox cbAddDesign;
    @FXML
    private CheckBox cbAddLayout;
    @FXML
    private CheckBox cbAddBack;
    @FXML
    private CheckBox cbAddTesting;
    @FXML
    private CheckBox cbAddPromo;
    @FXML
    private Button editPerson;
    @FXML
    private Pane EditPPane1;
    @FXML
    private TextField addPName1;
    @FXML
    private TextField addPLogin1;
    @FXML
    private TextField addPPassword1;
    @FXML
    private TextField addPProcentage1;
    @FXML
    private CheckBox cbAddProjecting1;
    @FXML
    private CheckBox cbAddDesign1;
    @FXML
    private CheckBox cbAddLayout1;
    @FXML
    private CheckBox cbAddBack1;
    @FXML
    private CheckBox cbAddTesting1;
    @FXML
    private CheckBox cbAddPromo1;
    @FXML
    private Button showPrBtn;
    @FXML
    private Label busyL;


    public AdminController() {
        WebService webService = new WebService();
        webService.getListOfProgectsDB();
        webService.getListOfUsersDB();
        listPr.addAll(webService.getListOfProgectsDB());
        listU.addAll(webService.getListOfUsersDB());
    }

    @FXML
    public void initialize() {

        numberCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("project_name"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("project_desc"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        idUCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameUserCol.setCellValueFactory(new PropertyValueFactory<>("user_name"));
        procCol.setCellValueFactory(new PropertyValueFactory<>("procentage"));
        busy.setCellValueFactory(new PropertyValueFactory<>("busy"));

//        updateCountLabel();
//        listN.addListener((ListChangeListener<Computers>) c -> updateCountLabel());
//        choiceBox1.set;
        tableViewP.setItems(listPr);
        tableViewU.setItems(listU);

        setCh();
    }

    @FXML
    private Pane paneAdd;
    @FXML
    private Pane paneObs;

    public void addP(ActionEvent actionEvent) {
        paneAdd.setVisible(true);
        paneObs.setVisible(false);
        addPPane.setVisible(false);
        EditPPane1.setVisible(false);
        choiceBox1.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> updatePriceLabel());
        choiceBox2.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> updatePriceLabel());
        choiceBox3.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> updatePriceLabel());
        choiceBox4.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> updatePriceLabel());
        choiceBox5.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> updatePriceLabel());
        choiceBox6.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> updatePriceLabel());
        tf1.textProperty().addListener(observable -> updatePriceLabel());
        tf2.textProperty().addListener(observable -> updatePriceLabel());
        tf3.textProperty().addListener(observable -> updatePriceLabel());
        tf4.textProperty().addListener(observable -> updatePriceLabel());
        tf5.textProperty().addListener(observable -> updatePriceLabel());
        tf6.textProperty().addListener(observable -> updatePriceLabel());
    }

    public void addPClose() {
        paneAdd.setVisible(false);
    }

    public void addObs() {
        Project project = tableViewP.getSelectionModel().getSelectedItem();
        if (project!=null){
            paneObs.setVisible(true);
            paneAdd.setVisible(false);
            addPPane.setVisible(false);
            EditPPane1.setVisible(false);
            projectEditObs();
        }
    }

    public void editClose() {
        paneObs.setVisible(false);
    }

    public void addObsClose() {
        paneObs.setVisible(false);
    }


    public void addNewPr() {

        try {
            int price = 0;
            HashMap<Integer, User> mapUsers = new HashMap<Integer, User>();
            HashMap<Integer, Integer> mapStageTime = new HashMap<Integer, Integer>();
            if (!(choiceBox1.getValue() == null) && !(tf1.getText() == null)) {
                price = price + (webService.getPriceByIdStage(1)) * Integer.parseInt(tf1.getText());
                mapStageTime.put(1, Integer.parseInt(tf1.getText()));
                mapUsers.put(1, returnUserByName(choiceBox1.getValue()));
            }
            if (!(choiceBox2.getValue() == null) && !(tf2.getText() == null)) {
                price = price + (webService.getPriceByIdStage(2)) * Integer.parseInt(tf2.getText());
                mapStageTime.put(2, Integer.parseInt(tf2.getText()));
                mapUsers.put(2, returnUserByName(choiceBox2.getValue()));
            }
            if (!(choiceBox3.getValue() == null) && !(tf3.getText() == null)) {
                price = price + (webService.getPriceByIdStage(3)) * Integer.parseInt(tf3.getText());
                mapStageTime.put(3, Integer.parseInt(tf3.getText()));
                mapUsers.put(3, returnUserByName(choiceBox3.getValue()));
            }
            if (!(choiceBox4.getValue() == null) && !(tf4.getText() == null)) {
                price = price + (webService.getPriceByIdStage(4)) * Integer.parseInt(tf4.getText());
                mapStageTime.put(4, Integer.parseInt(tf4.getText()));
                mapUsers.put(4, returnUserByName(choiceBox4.getValue()));
            }
            if (!(choiceBox5.getValue() == null) && !(tf5.getText() == null)) {
                price = price + (webService.getPriceByIdStage(5)) * Integer.parseInt(tf5.getText());
                mapStageTime.put(5, Integer.parseInt(tf5.getText()));
                mapUsers.put(5, returnUserByName(choiceBox5.getValue()));
            }
            if (!(choiceBox6.getValue() == null) && !(tf6.getText() == null)) {
                price = price + (webService.getPriceByIdStage(6)) * Integer.parseInt(tf6.getText());
                mapStageTime.put(6, Integer.parseInt(tf6.getText()));
                mapUsers.put(6, returnUserByName(choiceBox6.getValue()));
            }
            if (!(tfName.getText().isEmpty()) && !(tfDesc.getText().isEmpty())) {
                webService.createNewPr(tfName.getText(), tfDesc.getText(), price);
                webService.createNewPrData(mapUsers, mapStageTime);
                listPr.remove(0, listPr.size());
                listPr.addAll(webService.getListOfProgectsDB());
                webService.setIsBusyDb();
                listU.remove(0,listU.size());
                listU.addAll(webService.getListOfUsersDB());
                addPClose();
            } else {
                label.setText("Enter name and desc!");
            }
        } catch (NumberFormatException e) {
            label.setText("Check input!");
        }
    }

    public void updatePriceLabel(){
        try {
            int stage1=0;
            int stage2=0;
            int stage3=0;
            int stage4=0;
            int stage5=0;
            int stage6=0;

            if (!(tf1.getText().isEmpty())&&choiceBox1.getSelectionModel().getSelectedItem()!=null){
                stage1 = webService.getPriceByIdStage(1)*Integer.parseInt(tf1.getText());
            }
            if (!(tf2.getText().isEmpty())&&choiceBox2.getSelectionModel().getSelectedItem()!=null){
                stage2 = webService.getPriceByIdStage(2)*Integer.parseInt(tf2.getText());
            }
            if (!(tf3.getText().isEmpty())&&choiceBox3.getSelectionModel().getSelectedItem()!=null){
                stage3 = webService.getPriceByIdStage(3)*Integer.parseInt(tf3.getText());
            }
            if (!(tf4.getText().isEmpty())&&choiceBox4.getSelectionModel().getSelectedItem()!=null){
                stage4 = webService.getPriceByIdStage(4)*Integer.parseInt(tf4.getText());
            }
            if (!(tf5.getText().isEmpty())&&choiceBox5.getSelectionModel().getSelectedItem()!=null){
                stage5 = webService.getPriceByIdStage(5)*Integer.parseInt(tf5.getText());
            }
            if (!(tf6.getText().isEmpty())&&choiceBox6.getSelectionModel().getSelectedItem()!=null){
                stage6 = webService.getPriceByIdStage(6)*Integer.parseInt(tf6.getText());
            }

            if (!(tf11.getText().isEmpty())&&choiceBox11.getSelectionModel().getSelectedItem()!=null){
                stage1 = webService.getPriceByIdStage(1)*Integer.parseInt(tf11.getText());
            }
            if (!(tf21.getText().isEmpty())&&choiceBox21.getSelectionModel().getSelectedItem()!=null){
                stage2 = webService.getPriceByIdStage(2)*Integer.parseInt(tf21.getText());
            }
            if (!(tf31.getText().isEmpty())&&choiceBox31.getSelectionModel().getSelectedItem()!=null){
                stage3 = webService.getPriceByIdStage(3)*Integer.parseInt(tf31.getText());
            }
            if (!(tf41.getText().isEmpty())&&choiceBox41.getSelectionModel().getSelectedItem()!=null){
                stage4 = webService.getPriceByIdStage(4)*Integer.parseInt(tf41.getText());
            }
            if (!(tf51.getText().isEmpty())&&choiceBox51.getSelectionModel().getSelectedItem()!=null){
                stage5 = webService.getPriceByIdStage(5)*Integer.parseInt(tf51.getText());
            }
            if (!(tf61.getText().isEmpty())&&choiceBox61.getSelectionModel().getSelectedItem()!=null){
                stage6 = webService.getPriceByIdStage(6)*Integer.parseInt(tf61.getText());
            }
            label.setText(String.valueOf(stage1+stage2+stage3+stage4+stage5+stage6));
            stageLabelEdit.setText(String.valueOf(stage1+stage2+stage3+stage4+stage5+stage6));
        } catch (NumberFormatException e) {
            label.setText("Wrong data!!!");
            stageLabelEdit.setText("Wrong data!!!");
        }
    }

    public void projectEditObs() {

        if (!(tableViewP.getSelectionModel().getSelectedItem() == null)) {
            choiceBox11.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> updatePriceLabel());
            choiceBox21.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> updatePriceLabel());
            choiceBox31.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> updatePriceLabel());
            choiceBox41.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> updatePriceLabel());
            choiceBox51.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> updatePriceLabel());
            choiceBox61.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> updatePriceLabel());
            tf11.textProperty().addListener(observable -> updatePriceLabel());
            tf21.textProperty().addListener(observable -> updatePriceLabel());
            tf31.textProperty().addListener(observable -> updatePriceLabel());
            tf41.textProperty().addListener(observable -> updatePriceLabel());
            tf51.textProperty().addListener(observable -> updatePriceLabel());
            tf61.textProperty().addListener(observable -> updatePriceLabel());
            tf11.setText("");
            tf21.setText("");
            tf31.setText("");
            tf41.setText("");
            tf51.setText("");
            tf61.setText("");

            String name1 = null;
            String name2 = null;
            String name3 = null;
            String name4 = null;
            String name5 = null;
            String name6 = null;
            Project project = tableViewP.getSelectionModel().getSelectedItem();
            tfName1.setText(project.getProject_name());
            tfDesc1.setText(project.getProject_desc());
            HashMap[] h = webService.getProjData(project.getId());
            HashMap<Integer, Integer> hashMap = h[0];
            HashMap<Integer, Integer> hashMap2 = h[1];
            for (Map.Entry<Integer, Integer> entry1 : hashMap.entrySet()) {
                if (entry1.getKey() == 1) {
                    for (User u : listU) {
                        if (u.getId() == entry1.getValue()) {
                            name1 = u.getUser_name();
                        }
                    }
                    tf11.setText(String.valueOf(hashMap2.get(1)));
                }
                if (entry1.getKey() == 2) {
                    for (User u : listU) {
                        if (u.getId() == entry1.getValue()) {
                            name2 = u.getUser_name();
                        }
                    }
                    tf21.setText(String.valueOf(hashMap2.get(2)));
                }
                if (entry1.getKey() == 3) {
                    for (User u : listU) {
                        if (u.getId() == entry1.getValue()) {
                            name3 = u.getUser_name();
                        }
                    }
                    tf31.setText(String.valueOf(hashMap2.get(3)));
                }
                if (entry1.getKey() == 4) {
                    for (User u : listU) {
                        if (u.getId() == entry1.getValue()) {
                            name4 = u.getUser_name();
                        }
                    }
                    tf41.setText(String.valueOf(hashMap2.get(4)));
                }
                if (entry1.getKey() == 5) {
                    for (User u : listU) {
                        if (u.getId() == entry1.getValue()) {
                            name5 = u.getUser_name();
                        }
                    }
                    tf51.setText(String.valueOf(hashMap2.get(5)));
                }
                if (entry1.getKey() == 6) {
                    for (User u : listU) {
                        if (u.getId() == entry1.getValue()) {
                            name6 = u.getUser_name();
                        }
                    }
                    tf61.setText(String.valueOf(hashMap2.get(6)));
                }
            }
            choiceBox11.getSelectionModel().select(name1);
            choiceBox21.getSelectionModel().select(name2);
            choiceBox31.getSelectionModel().select(name3);
            choiceBox41.getSelectionModel().select(name4);
            choiceBox51.getSelectionModel().select(name5);
            choiceBox61.getSelectionModel().select(name6);

            if (project.getStatus() == 7) {
                push.setVisible(false);
            }
            push.setVisible(true);
            stageLabelEdit.setText("");
            addPPane.setVisible(false);
            EditPPane1.setVisible(false);
            if (project.getStatus()==0){
                slider.setVisible(false);
            }else {
                slider.setVisible(true);
                slider.setMin(1);
                slider.setMax(6);
                slider.setValue(6-(project.getStatus())+1);
            }

        }
    }

    public void editPrObsSave() {
        try{
            Project project = tableViewP.getSelectionModel().getSelectedItem();
            int p = project.getId();
            int price = 0;
            HashMap<Integer, User> mapUsers = new HashMap<Integer, User>();
            HashMap<Integer, Integer> mapStageTime = new HashMap<Integer, Integer>();
            if (!(choiceBox11.getValue() == null) && !(tf11.getText() == null)) {
                price = price + (webService.getPriceByIdStage(1)) * Integer.parseInt(tf11.getText());
                mapStageTime.put(1, Integer.parseInt(tf11.getText()));
                mapUsers.put(1, returnUserByName(choiceBox11.getValue()));
            }
            if (!(choiceBox21.getValue() == null) && !(tf21.getText() == null)) {
                price = price + (webService.getPriceByIdStage(2)) * Integer.parseInt(tf21.getText());
                mapStageTime.put(2, Integer.parseInt(tf21.getText()));
                mapUsers.put(2, returnUserByName(choiceBox21.getValue()));
            }
            if (!(choiceBox31.getValue() == null) && !(tf31.getText() == null)) {
                price = price + (webService.getPriceByIdStage(3)) * Integer.parseInt(tf31.getText());
                mapStageTime.put(3, Integer.parseInt(tf31.getText()));
                mapUsers.put(3, returnUserByName(choiceBox31.getValue()));
            }
            if (!(choiceBox41.getValue() == null) && !(tf41.getText() == null)) {
                price = price + (webService.getPriceByIdStage(4)) * Integer.parseInt(tf41.getText());
                mapStageTime.put(4, Integer.parseInt(tf41.getText()));
                mapUsers.put(4, returnUserByName(choiceBox41.getValue()));
            }
            if (!(choiceBox51.getValue() == null) && !(tf51.getText() == null)) {
                price = price + (webService.getPriceByIdStage(5)) * Integer.parseInt(tf51.getText());
                mapStageTime.put(5, Integer.parseInt(tf51.getText()));
                mapUsers.put(5, returnUserByName(choiceBox51.getValue()));
            }
            if (!(choiceBox61.getValue() == null) && !(tf61.getText() == null)) {
                price = price + (webService.getPriceByIdStage(6)) * Integer.parseInt(tf61.getText());
                mapStageTime.put(6, Integer.parseInt(tf61.getText()));
                mapUsers.put(6, returnUserByName(choiceBox61.getValue()));
            }
            if (!(tfName1 == null) && !(tfDesc1 == null)) {
                webService.editPr(tfName1.getText(), tfDesc1.getText(), price, p, mapUsers, mapStageTime, project.getStatus());
//            webService.createNewPrData(mapUsers,mapStageTime);
                listPr.remove(0, listPr.size());
                listPr.addAll(webService.getListOfProgectsDB());
                webService.setIsBusyDb();
                listU.remove(0,listU.size());
                listU.addAll(webService.getListOfUsersDB());
                editClose();
            } else {
                label.setText("Enter name and desc!");
            }
        }catch (NumberFormatException e){
            System.out.println("Fail");
            stageLabelEdit.setText("Check input!");
        }


    }

    public User returnUserByName(String uName) {
        User user = null;
        for (User u : listU) {
            if (u.getUser_name().equals(uName)) {
                user = u;
            }
        }
        return user;
    }

    @FXML
    private Button push;
    @FXML
    private Label stageLabelEdit;

    public void stageIncr() {
        int n = 1;
        slider.setVisible(true);
        slider.setMin(1);
        slider.setMax(6);
        Project project = tableViewP.getSelectionModel().getSelectedItem();
        int stage = webService.getStageById(project.getId());
        Set<Integer> list = webService.getStagesById(project.getId());
        if (stage == (Integer) list.toArray()[list.size() - 1]) {
            webService.setStageToDb(project.getId(), 7);
            HistoryWebService historyWebService = new HistoryWebService();
            historyWebService.savePrToHistory(project);
            historyWebService.saveUsersWorkToHistory(project.getId());
            historyWebService.saveUserTimeToHistory(project.getId());
            historyWebService.removeUserWorkFromMain(project.getId());
            historyWebService.removeUserTimeToHistory(project.getId());
            listU.remove(0,listU.size());
            listU.addAll(webService.getListOfUsersDB());

        } else if (stage < 7) {
            for (int i = 0; i < list.size(); i++) {
                if (stage < (Integer) list.toArray()[i]) {
                    webService.setStageToDb(project.getId(), (Integer) list.toArray()[i]);
                    slider.setValue(6-(webService.getStageById(project.getId()))+1);
                    break;
                }
            }
        }
        listPr.remove(0, listPr.size());
        listPr.addAll(webService.getListOfProgectsDB());
        push.setVisible(false);
        if (stage == 7) {
            stageLabelEdit.setText("Project finished");
        } else {
            stageLabelEdit.setText("Done!");
        }
    }

    public void showAddPersonPane() {
        addPPane.setVisible(true);
        paneAdd.setVisible(false);
        paneObs.setVisible(false);
        EditPPane1.setVisible(false);

    }

    public void addPerson() {
        try {
            ArrayList<Integer> listSkills = new ArrayList<>();
            if (cbAddProjecting.isSelected()) {
                listSkills.add(1);
            }
            if (cbAddDesign.isSelected()) {
                listSkills.add(2);
            }
            if (cbAddLayout.isSelected()) {
                listSkills.add(3);
            }
            if (cbAddBack.isSelected()) {
                listSkills.add(4);
            }
            if (cbAddTesting.isSelected()) {
                listSkills.add(5);
            }
            if (cbAddPromo.isSelected()) {
                listSkills.add(6);
            }
            if (!(addPName.getText() == null) && !(addPLogin.getText() == null) && !(addPPassword.getText() == null) && !(addPProcentage.getText() == null)) {
                webService.saveNewPerson(addPName.getText(), addPLogin.getText(), addPPassword.getText(), Integer.parseInt(addPProcentage.getText()), listSkills);
                listU.remove(0, listU.size());
                listU.addAll(webService.getListOfUsersDB());
                setCh();
            }
        } catch (NumberFormatException e) {
            AlertBox alertBox = new AlertBox();
            alertBox.display("alert","Wrong procentage");
        }
    }

    @FXML
    private Button addPButton;
    public void setCh(){
        choiceBox1.setItems(webService.getListUsersForChoicebox(1));
        choiceBox2.setItems(webService.getListUsersForChoicebox(2));
        choiceBox3.setItems(webService.getListUsersForChoicebox(3));
        choiceBox4.setItems(webService.getListUsersForChoicebox(4));
        choiceBox5.setItems(webService.getListUsersForChoicebox(5));
        choiceBox6.setItems(webService.getListUsersForChoicebox(6));
        choiceBox11.setItems(webService.getListUsersForChoicebox(1));
        choiceBox21.setItems(webService.getListUsersForChoicebox(2));
        choiceBox31.setItems(webService.getListUsersForChoicebox(3));
        choiceBox41.setItems(webService.getListUsersForChoicebox(4));
        choiceBox51.setItems(webService.getListUsersForChoicebox(5));
        choiceBox61.setItems(webService.getListUsersForChoicebox(6));
    }

    public void showEditPersonPane(){
        cbAddProjecting1.setSelected(false);
        cbAddDesign1.setSelected(false);
        cbAddLayout1.setSelected(false);
        cbAddBack1.setSelected(false);
        cbAddTesting1.setSelected(false);
        cbAddPromo1.setSelected(false);


        User user = tableViewU.getSelectionModel().getSelectedItem();
        if (!(user==null)){
            EditPPane1.setVisible(true);
            paneAdd.setVisible(false);
            paneObs.setVisible(false);
            addPPane.setVisible(false);
            if (user.getBusy()){
                showPrBtn.setVisible(true);
            }else {
                showPrBtn.setVisible(false);
            }
            addPName1.setText(user.getUser_name());
            addPLogin1.setText(user.getUser_login());
            addPProcentage1.setText(String.valueOf(user.getProcentage()));
            ArrayList<Integer> listSkills = webService.getSkillsUserById(user.getId());
            for (Integer i : listSkills){
                if (i==1){
                    cbAddProjecting1.setSelected(true);
                }
                if (i==2){
                    cbAddDesign1.setSelected(true);
                }
                if (i==3){
                    cbAddLayout1.setSelected(true);
                }
                if (i==4){
                    cbAddBack1.setSelected(true);
                }
                if (i==5){
                    cbAddTesting1.setSelected(true);
                }
                if (i==6){
                    cbAddPromo1.setSelected(true);
                }
            }
        }else {

        }
    }

    public void saveEditPerson(){
        try {
            User user = tableViewU.getSelectionModel().getSelectedItem();
            ArrayList<Integer> list = new ArrayList<>();
            if (cbAddProjecting1.isSelected()) {
                list.add(1);
            }
            if (cbAddDesign1.isSelected()) {
                list.add(2);
            }
            if (cbAddLayout1.isSelected()) {
                list.add(3);
            }
            if (cbAddBack1.isSelected()) {
                list.add(4);
            }
            if (cbAddTesting1.isSelected()) {
                list.add(5);
            }
            if (cbAddPromo1.isSelected()) {
                list.add(6);
            }
            webService.saveEditPerson(addPName1.getText(),addPLogin1.getText(),addPPassword1.getText(),Integer.parseInt(addPProcentage1.getText()),list,user.getId());
            listU.remove(0, listU.size());
            listU.addAll(webService.getListOfUsersDB());
            setCh();
        } catch (NumberFormatException e) {
            AlertBox alertBox = new AlertBox();
            alertBox.display("alert","Wrong procentage");
        }
    }

    public void deleteU(){
        User user = tableViewU.getSelectionModel().getSelectedItem();
        if (!(user==null)){
            if (user.getBusy()){
            AlertBox alertBox = new AlertBox();
                alertBox.display("alert","User is working!");
            }
            HashSet<Integer> list = webService.deleteUserIfPossible(user.getId());
            listU.remove(0, listU.size());
            listU.addAll(webService.getListOfUsersDB());
            setCh();
        }
    }

    public void showHistory(){
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("history.fxml"));
            stage.setTitle("history");
//            stage.setResizable(false);
            stage.setScene(new Scene(root,900,390));
//            stage.initModality(Modality.WINDOW_MODAL);
//            stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void logOut() {
        try {
            Stage stagePr = (Stage) tableViewP.getScene().getWindow() ;
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


    @Override
    public void handle(ActionEvent event) {

    }

    public void stageMenuShow(ActionEvent actionEvent) {
        try {
            Stage stage1 = new Stage();
            Parent root1 = FXMLLoader.load(getClass().getResource("stage.fxml"));
            stage1.setTitle("Stages");
            stage1.setResizable(false);
            stage1.setScene(new Scene(root1,340,320));
            stage1.initModality(Modality.APPLICATION_MODAL);
//            stage.initOwner((Window) actionEvent.getSource());
            stage1.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showPrBtnF(ActionEvent actionEvent) {
        User user = tableViewU.getSelectionModel().getSelectedItem();
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load(getClass().getResource("listParticipatingPr.fxml").openStream());
            ListPrController listPrController = (ListPrController) loader.getController();
            listPrController.initialize(user);
            stage.setTitle("Projects");
            stage.setResizable(false);
            stage.setScene(new Scene(root,340,320));
//            stage.initModality(Modality.APPLICATION_MODAL);
//            stage.initOwner((Window) actionEvent.getSource());
            stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
