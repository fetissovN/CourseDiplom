package sample;

import WebService.Project;
import WebService.User;
import WebService.UserWebService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ListPrController {
    ObservableList<Project> listP = FXCollections.observableArrayList();
    UserWebService userWebService = new UserWebService();

    @FXML
    private Button cancelBtn;
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

    public ListPrController() {

    }

    @FXML
    public void initialize(User user) {

        numberCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("project_name"));
//        descCol.setCellValueFactory(new PropertyValueFactory<>("project_desc"));
//        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
//        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        listP.remove(0,listP.size());
        listP.addAll(userWebService.getListOfMyPr(user));
        tableViewP.setItems(listP);
    }

    public void cancel(ActionEvent actionEvent) {
        Stage stagePr = (Stage) cancelBtn.getScene().getWindow() ;
        stagePr.close();
    }
}
