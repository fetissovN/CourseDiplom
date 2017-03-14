package sample;

import WebService.WebService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class StageController {
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
    private Button saveBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private Label label;

    public StageController() {


    }

    @FXML
    public void initialize(){
        WebService webService = new WebService();
        tf1.setText(String.valueOf(webService.getPriceByIdStage(1)));
        tf2.setText(String.valueOf(webService.getPriceByIdStage(2)));
        tf3.setText(String.valueOf(webService.getPriceByIdStage(3)));
        tf4.setText(String.valueOf(webService.getPriceByIdStage(4)));
        tf5.setText(String.valueOf(webService.getPriceByIdStage(5)));
        tf6.setText(String.valueOf(webService.getPriceByIdStage(6)));
    }

    public void save(ActionEvent actionEvent) {
        try {
            WebService webService = new WebService();
            webService.setPriceByIdSatgeToDb(Integer.parseInt(tf1.getText()),1);
            webService.setPriceByIdSatgeToDb(Integer.parseInt(tf2.getText()),2);
            webService.setPriceByIdSatgeToDb(Integer.parseInt(tf3.getText()),3);
            webService.setPriceByIdSatgeToDb(Integer.parseInt(tf4.getText()),4);
            webService.setPriceByIdSatgeToDb(Integer.parseInt(tf5.getText()),5);
            webService.setPriceByIdSatgeToDb(Integer.parseInt(tf6.getText()),6);
            cancel(actionEvent);
        } catch (NumberFormatException e) {
            label.setText("Incorrect data");
        }
    }

    public void cancel(ActionEvent actionEvent) {
        Stage stagePr = (Stage) saveBtn.getScene().getWindow() ;
        stagePr.close();
    }
}
