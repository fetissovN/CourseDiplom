package WebService;

import sample.DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class UserWebService {
    DB db = new DB();

    public ArrayList<Project> getListOfMyPr(User user){
        ArrayList<Project> list = new ArrayList<>();

        try {
            db.connect();
            PreparedStatement preparedStatement = db.getConnection().prepareStatement("SELECT DISTINCT project_id FROM user_work WHERE id_user=?");
            preparedStatement.setInt(1,user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){

                PreparedStatement preparedStatement1 = db.getConnection().prepareStatement("SELECT * FROM projects WHERE project_id=? AND status<7");
                preparedStatement1.setInt(1,resultSet.getInt("project_id"));
                ResultSet resultSet1 = preparedStatement1.executeQuery();
                while (resultSet1.next()){
                    list.add(new Project(resultSet1.getInt("project_id"),resultSet1.getString("project_name"),resultSet1.getString("project_desc"),resultSet1.getInt("status"),resultSet1.getInt("price")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Project p: list){
            System.out.println(p);
        }
        return list;
    }

    public ArrayList<Project> getListOfOldPr(User user){
        ArrayList<Project> list = new ArrayList<>();

        try {
            db.connect();
            PreparedStatement preparedStatement = db.getConnection().prepareStatement("SELECT DISTINCT project_id FROM user_work WHERE id_user=?");
            preparedStatement.setInt(1,user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){

                PreparedStatement preparedStatement1 = db.getConnection().prepareStatement("SELECT * FROM projects WHERE project_id=? AND status=7");
                preparedStatement1.setInt(1,resultSet.getInt("project_id"));
                ResultSet resultSet1 = preparedStatement1.executeQuery();
                while (resultSet1.next()){
                    list.add(new Project(resultSet1.getInt("project_id"),resultSet1.getString("project_name"),resultSet1.getString("project_desc"),resultSet1.getInt("status"),resultSet1.getInt("price")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Project p: list){
            System.out.println(p);
        }
        return list;
    }

    public HashMap[] sdfsdf(int id){
        WebService webService = new WebService();
        HashMap[] hashMap = webService.getProjData(id);
        return hashMap;
    }
}
