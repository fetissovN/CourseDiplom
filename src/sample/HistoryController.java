package sample;

import WebService.WebService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import WebService.Project;
import WebService.HistoryWebService;
import WebService.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class HistoryController {
    ObservableList<Project> listPr = FXCollections.observableArrayList();
    HistoryWebService historyWebService = new HistoryWebService();
    @FXML
    private Button close;
    @FXML
    private ListView<Project> listView;
    @FXML
    private Label hName1;
    @FXML
    private Label hName2;
    @FXML
    private Label hName3;
    @FXML
    private Label hName4;
    @FXML
    private Label hName5;
    @FXML
    private Label hName6;
    @FXML
    private Label hHours1;
    @FXML
    private Label hHours2;
    @FXML
    private Label hHours3;
    @FXML
    private Label hHours4;
    @FXML
    private Label hHours5;
    @FXML
    private Label hHours6;
    @FXML
    private Label hProc1;
    @FXML
    private Label hProc2;
    @FXML
    private Label hProc3;
    @FXML
    private Label hProc4;
    @FXML
    private Label hProc5;
    @FXML
    private Label hProc6;
    @FXML
    private Label hPrice1;
    @FXML
    private Label hPrice2;
    @FXML
    private Label hPrice3;
    @FXML
    private Label hPrice4;
    @FXML
    private Label hPrice5;
    @FXML
    private Label hPrice6;


    public HistoryController() {
        HistoryWebService historyWebService = new HistoryWebService();
        listPr.addAll(historyWebService.getHistoryProgectsDB());
    }
    public void Close(){
        Stage stagePr = (Stage) close.getScene().getWindow();
        stagePr.close();
    }

    @FXML
    public void initialize() {

        listView.setItems(listPr);

    }

    public void showData(){
        hName1.setText("");
        hName2.setText("");
        hName3.setText("");
        hName4.setText("");
        hName5.setText("");
        hName6.setText("");
        hProc1.setText("");
        hProc2.setText("");
        hProc3.setText("");
        hProc4.setText("");
        hProc5.setText("");
        hProc6.setText("");
        hHours1.setText("");
        hHours2.setText("");
        hHours3.setText("");
        hHours4.setText("");
        hHours5.setText("");
        hHours6.setText("");

        Project project = listView.getSelectionModel().getSelectedItem();

        HashMap<User,Integer> mapUI = historyWebService.getListOfWorkByProjectId(project.getId());
        Set<Map.Entry<User, Integer>> setStage = mapUI.entrySet();
        for (Map.Entry<User, Integer> me : setStage) {
            if (me.getValue()==1){
                hName1.setText(me.getKey().getUser_name());
                hProc1.setText(String.valueOf(me.getKey().getProcentage()));
            }
            if (me.getValue()==2){
                hName2.setText(me.getKey().getUser_name());
                hProc2.setText(String.valueOf(me.getKey().getProcentage()));
            }
            if (me.getValue()==3){
                hName3.setText(me.getKey().getUser_name());
                hProc3.setText(String.valueOf(me.getKey().getProcentage()));
            }
            if (me.getValue()==4){
                hName4.setText(me.getKey().getUser_name());
                hProc4.setText(String.valueOf(me.getKey().getProcentage()));
            }
            if (me.getValue()==5){
                hName5.setText(me.getKey().getUser_name());
                hProc5.setText(String.valueOf(me.getKey().getProcentage()));
            }
            if (me.getValue()==6){
                hName6.setText(me.getKey().getUser_name());
                hProc6.setText(String.valueOf(me.getKey().getProcentage()));
            }
        }
        HashMap<Integer, Integer> mapStageTime = historyWebService.getListStageTime(project.getId());
        Set<Map.Entry<Integer, Integer>> set = mapStageTime.entrySet();
        for (Map.Entry<Integer,Integer> mapT: set){
            if (mapT.getKey()==1){
                hHours1.setText(String.valueOf(mapT.getValue()));
            }
            if (mapT.getKey()==2){
                hHours2.setText(String.valueOf(mapT.getValue()));
            }
            if (mapT.getKey()==3){
                hHours3.setText(String.valueOf(mapT.getValue()));
            }
            if (mapT.getKey()==4){
                hHours4.setText(String.valueOf(mapT.getValue()));
            }
            if (mapT.getKey()==5){
                hHours5.setText(String.valueOf(mapT.getValue()));
            }
            if (mapT.getKey()==6){
                hHours6.setText(String.valueOf(mapT.getValue()));
            }
        }
    }



}
