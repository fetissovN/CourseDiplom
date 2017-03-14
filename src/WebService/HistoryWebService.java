package WebService;

import sample.DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class HistoryWebService {
    DB db = new DB();
    public ArrayList<Project> getHistoryProgectsDB(){
        ArrayList<Project> listProjects = new ArrayList<>();
        try {
            db.connect();
            Statement statement = db.getSt();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM projects WHERE status=7");
            while(resultSet.next()){
                Project progect = new Project(resultSet.getInt("project_id"),resultSet.getString("project_name"),resultSet.getString("project_desc"),resultSet.getInt("status"),resultSet.getInt("price"));
                listProjects.add(progect);
                System.out.println(progect);
            }
            System.out.println(listProjects.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listProjects;
    }

    public void savePrToHistory(Project project) {
        try {
            db.connect();
            PreparedStatement preparedStatement = db.getConnection().prepareStatement("INSERT INTO projects_history (project_id_history,project_name_history,project_desc_history,projects_price) VALUES (?,?,?,?)");
            preparedStatement.setInt(1,project.getId());
            preparedStatement.setString(2,project.getProject_name());
            preparedStatement.setString(3,project.getProject_desc());
            preparedStatement.setInt(4,project.getPrice());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUsersWorkToHistory(int id){
        try {
            db.connect();
            PreparedStatement preparedStatement1 = db.getConnection().prepareStatement("SELECT * FROM user_work WHERE project_id=?");
            preparedStatement1.setInt(1,id);
            ResultSet resultSet = preparedStatement1.executeQuery();
            while (resultSet.next()){
                PreparedStatement preparedStatement = db.getConnection().prepareStatement("INSERT INTO user_work_history (id_user_history,id_stage_history,project_id_history) VALUES (?,?,?)");
                preparedStatement.setInt(1,resultSet.getInt("id_user"));
                preparedStatement.setInt(2,resultSet.getInt("id_stage"));
                preparedStatement.setInt(3,id);
                preparedStatement.execute();
                saveUserToHistory(resultSet.getInt("id_user"));
            }


        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void saveUserToHistory(int id_user){
        try {
            db.connect();
            PreparedStatement preparedStatement1 = db.getConnection().prepareStatement("SELECT * FROM users WHERE id_user=?");
            preparedStatement1.setInt(1,id_user);
            ResultSet resultSet2 = preparedStatement1.executeQuery();
            if (resultSet2.next()){
                PreparedStatement preparedStatement = db.getConnection().prepareStatement("INSERT INTO users_history (id_user,user_name,user_login,procentage) VALUES (?,?,?,?)");
                preparedStatement.setInt(1,resultSet2.getInt("id_user"));
                preparedStatement.setString(2,resultSet2.getString("user_name"));
                preparedStatement.setString(3,resultSet2.getString("user_login"));
                preparedStatement.setInt(4,resultSet2.getInt("procentage"));
                preparedStatement.execute();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void saveUserTimeToHistory(int id){
        try {
            db.connect();
            PreparedStatement preparedStatement1 = db.getConnection().prepareStatement("SELECT * FROM project_stages WHERE project_id=?");
            preparedStatement1.setInt(1,id);
            ResultSet resultSet2 = preparedStatement1.executeQuery();
            while(resultSet2.next()){
                PreparedStatement preparedStatement = db.getConnection().prepareStatement("INSERT INTO project_stages_history (project_id_h,id_stage_h,time_h) VALUES (?,?,?)");
                preparedStatement.setInt(1,resultSet2.getInt("project_id"));
                preparedStatement.setInt(2,resultSet2.getInt("id_stage"));
                preparedStatement.setInt(3,resultSet2.getInt("time"));
                preparedStatement.execute();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public HashMap<User, Integer> getListOfWorkByProjectId(int id_project){
        HashMap<User, Integer> mapUserStage = new HashMap<>();

            try {
                db.connect();
                PreparedStatement preparedStatement1 = db.getConnection().prepareStatement("SELECT * FROM user_work_history WHERE project_id_history=?");
                preparedStatement1.setInt(1,id_project);
                ResultSet resultSet = preparedStatement1.executeQuery();

                while (resultSet.next()){
                    PreparedStatement preparedStatement = db.getConnection().prepareStatement("SELECT * FROM users_history WHERE id_user=?");
                    preparedStatement.setInt(1,resultSet.getInt("id_user_history"));
                    ResultSet resultSet2 = preparedStatement.executeQuery();
                    if (resultSet2.next()){
                        mapUserStage.put(new User(resultSet2.getInt("id_user"),resultSet2.getString("user_name"),resultSet2.getString("user_login"),resultSet2.getInt("procentage")),resultSet.getInt("id_stage_history"));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return mapUserStage;
    }

    public HashMap<Integer,Integer> getListStageTime(int id){
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        try {
            db.connect();
            PreparedStatement preparedStatement1 = db.getConnection().prepareStatement("SELECT * FROM project_stages_history WHERE project_id_h=?");
            preparedStatement1.setInt(1,id);
            ResultSet resultSet = preparedStatement1.executeQuery();

            while (resultSet.next()){
                hashMap.put(resultSet.getInt("id_stage_h"),resultSet.getInt("time_h"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hashMap;
    }

    public void removeUserWorkFromMain(int id) {
        try {
            db.connect();
            PreparedStatement ps2 = db.getConnection().prepareStatement("DELETE FROM user_work WHERE project_id=?");
            ps2.setInt(1,id);
            ps2.execute();
            db.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserTimeToHistory(int id) {
        try {
            db.connect();
            PreparedStatement ps = db.getConnection().prepareStatement("DELETE FROM project_stages WHERE project_id=?");
            ps.setInt(1,id);
            ps.execute();
            db.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
