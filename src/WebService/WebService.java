package WebService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.mindrot.jbcrypt.BCrypt;
import sample.AdminController;
import sample.DB;

import java.sql.*;
import java.util.*;

public class WebService{


    DB db = new DB();


    public ArrayList<Project> getListOfProgectsDB(){
        ArrayList<Project> listProjects = new ArrayList<>();
        try {
            db.connect();
            Statement statement = db.getSt();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM projects WHERE status<7");
            while(resultSet.next()){
                Project progect = new Project(resultSet.getInt("project_id"),resultSet.getString("project_name"),resultSet.getString("project_desc"),resultSet.getInt("status"),resultSet.getInt("price"));
                listProjects.add(progect);
//                System.out.println(progect);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                db.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listProjects;
    }

    public ArrayList<User>  getListOfUsersDB(){
        ArrayList<User> listUsers = new ArrayList<>();
        try {
            db.connect();
            Statement statement = db.getSt();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while(resultSet.next()){
                User user= new User(resultSet.getInt("id_user"),resultSet.getString("user_name"),resultSet.getString("user_login"),resultSet.getInt("procentage"),resultSet.getBoolean("is_busy"));
                listUsers.add(user);
//                System.out.println(user);
            }
//            db.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                db.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listUsers;
    }


    public ObservableList<String> getListUsersForChoicebox(int stage){

        ObservableList<String> list = FXCollections.observableArrayList();
        try {
            db.connect();
            PreparedStatement preparedStatement = db.getConnection().prepareStatement("SELECT * FROM users WHERE id_user IN (SELECT id_user FROM skills WHERE skill=?)");
            preparedStatement.setInt(1,stage);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                list.add(resultSet.getString("user_name"));
            }
//            db.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            try {
                db.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public Integer getPriceByIdStage(int stage){
        int price = 0;

        try {
            db.connect();
            PreparedStatement preparedStatement = db.getConnection().prepareStatement("SELECT price_hour FROM stages WHERE id_stage=?");
            preparedStatement.setInt(1,stage);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
               price=price+resultSet.getInt("price_hour");
//                System.out.println(price);
            }
            db.closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return price;
    }

    public void createNewPr(String name,String desc, int price){
        try {
            db.connect();
            PreparedStatement preparedStatement = db.getConnection().prepareStatement("INSERT INTO projects (project_name,project_desc,status,price) VALUES (?,?,?,?)");
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,desc);
            preparedStatement.setInt(3,0);
            preparedStatement.setInt(4,price);
            preparedStatement.execute();
            db.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createNewPrData(HashMap<Integer,User> userMap, HashMap<Integer, Integer> stageTimeMap){
        HashMap<Integer, Integer> mapTime = stageTimeMap;
        Set<Map.Entry<Integer, Integer>> setTime = mapTime.entrySet();
        for (Map.Entry<Integer, Integer> me : setTime) {
            try {
                db.connect();
                ResultSet resultSet = db.getSt().executeQuery("SELECT project_id FROM projects ORDER BY project_id DESC LIMIT 1");
                if (resultSet.next()){
                    PreparedStatement preparedStatement = db.getConnection().prepareStatement("INSERT INTO project_stages (project_id,id_stage,time) VALUES (?,?,?)");
                    preparedStatement.setInt(1,resultSet.getInt("project_id"));
                    preparedStatement.setInt(2,me.getKey());
                    preparedStatement.setInt(3,me.getValue());
                    preparedStatement.execute();
                }
                db.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        HashMap<Integer, User> mapStage = userMap;
        Set<Map.Entry<Integer, User>> setStage = mapStage.entrySet();
        for (Map.Entry<Integer, User> me : setStage) {
            try {
                db.connect();
                ResultSet resultSet = db.getSt().executeQuery("SELECT project_id FROM projects ORDER BY project_id DESC LIMIT 1");
                if (resultSet.next()) {
                    PreparedStatement preparedStatement = db.getConnection().prepareStatement("INSERT INTO user_work (id_user,id_stage,project_id) VALUES (?,?,?)");
                    preparedStatement.setInt(1, me.getValue().getId());
                    preparedStatement.setInt(2, me.getKey());
                    preparedStatement.setInt(3, resultSet.getInt("project_id"));
                    preparedStatement.execute();
                }
                db.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public HashMap[] getProjData(int n){
        HashMap<Integer, Integer> mapStageTime = new HashMap<>();
        HashMap<Integer, Integer> mapUser = new HashMap<>();
        try {
            db.connect();
            PreparedStatement preparedStatement = db.getConnection().prepareStatement("SELECT * FROM user_work WHERE project_id=?");
            preparedStatement.setInt(1,n);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                mapUser.put(resultSet.getInt("id_stage"),resultSet.getInt("id_user"));
            }
            PreparedStatement preparedStatement2 = db.getConnection().prepareStatement("SELECT * FROM project_stages WHERE project_id=?");
            preparedStatement2.setInt(1,n);
            ResultSet resultSet2 = preparedStatement2.executeQuery();
            while(resultSet2.next()){
                mapStageTime.put(resultSet2.getInt("id_stage"),resultSet2.getInt("time"));
            }
            db.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new HashMap[] {mapUser,mapStageTime};

    }

    public void editPr(String name,String desc, int price, int id, HashMap<Integer,User> userMap, HashMap<Integer, Integer> stageTimeMap, int stage){
        try {
            db.connect();
            PreparedStatement preparedStatement = db.getConnection().prepareStatement("UPDATE projects SET project_name=?,project_desc=?,status=?,price=? WHERE project_id=?");
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,desc);
            preparedStatement.setInt(3,stage);
            preparedStatement.setInt(4,price);
            preparedStatement.setInt(5,id);
            preparedStatement.execute();
            db.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            db.connect();
            PreparedStatement ps = db.getConnection().prepareStatement("DELETE FROM project_stages WHERE project_id=?");
            ps.setInt(1,id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        HashMap<Integer, Integer> mapTime = stageTimeMap;
        Set<Map.Entry<Integer, Integer>> setTime = mapTime.entrySet();
        for (Map.Entry<Integer, Integer> me : setTime) {
            try {
                db.connect();
                PreparedStatement preparedStatement = db.getConnection().prepareStatement("INSERT INTO project_stages (id_stage,time,project_id) VALUES (?,?,?)");
                    preparedStatement.setInt(1,me.getKey());
                    preparedStatement.setInt(2,me.getValue());
                    preparedStatement.setInt(3,id);
                    preparedStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        try {
            PreparedStatement ps2 = db.getConnection().prepareStatement("DELETE FROM user_work WHERE project_id=?");
            ps2.setInt(1,id);
            ps2.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        HashMap<Integer, User> mapStage = userMap;
        Set<Map.Entry<Integer, User>> setStage = mapStage.entrySet();
        for (Map.Entry<Integer, User> me : setStage) {
            try {
                db.connect();
                    PreparedStatement preparedStatement = db.getConnection().prepareStatement("INSERT INTO user_work (id_user,id_stage,project_id) VALUES (?,?,?)");
                    preparedStatement.setInt(1, me.getValue().getId());
                    preparedStatement.setInt(2, me.getKey());
                    preparedStatement.setInt(3, id);
                    preparedStatement.execute();
                    db.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public Integer getStageById(int n){
        int stage=0;
        try {
            db.connect();
            PreparedStatement preparedStatement = db.getConnection().prepareStatement("SELECT status FROM projects WHERE project_id=?");
            preparedStatement.setInt(1,n);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                stage = stage+resultSet.getInt("status");
            }
            db.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stage;
    }
    public Set<Integer> getStagesById(int n){
        TreeSet<Integer> list = new TreeSet<>();
        try {
            db.connect();
            PreparedStatement preparedStatement = db.getConnection().prepareStatement("SELECT id_stage FROM project_stages WHERE project_id=?");
            preparedStatement.setInt(1,n);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                list.add(resultSet.getInt("id_stage"));
            }
            db.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void setStageToDb(int progect_id,int stage){
        try {
            db.connect();
            PreparedStatement preparedStatement = db.getConnection().prepareStatement("UPDATE projects SET status=? WHERE project_id=?");
            preparedStatement.setInt(1,stage);
            preparedStatement.setInt(2,progect_id);
            preparedStatement.execute();
            db.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveNewPerson(String name,String login,String password,int procentage, ArrayList<Integer> listSkills){
        String pw_hash = BCrypt.hashpw(password, BCrypt.gensalt());
        try {db.connect();
            PreparedStatement preparedStatement = db.getConnection().prepareStatement("INSERT INTO users (user_name,user_login,user_password,procentage) VALUES (?,?,?,?)");
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,login);
            preparedStatement.setString(3,pw_hash);
            preparedStatement.setInt(4,procentage);
            preparedStatement.execute();
            db.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (!(listSkills==null)){
            try {
                db.connect();
                ResultSet resultSet = db.getSt().executeQuery("SELECT id_user FROM users ORDER BY id_user DESC LIMIT 1");
                if (resultSet.next()){
                    for (Integer i : listSkills){
                        PreparedStatement preparedStatement2 = db.getConnection().prepareStatement("INSERT INTO skills (skill,id_user) VALUES (?,?)");
                        preparedStatement2.setInt(1,i);
                        preparedStatement2.setInt(2,resultSet.getInt("id_user"));
                        preparedStatement2.execute();
                    }
                }
                db.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Integer> getSkillsUserById(int id){
        ArrayList<Integer> list = new ArrayList<>();
        try {
            db.connect();
            PreparedStatement preparedStatement = db.getConnection().prepareStatement("SELECT skill FROM skills WHERE id_user=?");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                list.add(resultSet.getInt("skill"));
            }
            db.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void saveEditPerson(String name,String login,String password,int procentage, ArrayList<Integer> listSkills, int id){


        try {
            db.connect();
            String pw_hash = BCrypt.hashpw(password, BCrypt.gensalt());
            if (password==null||password.length()<4){
                System.out.println("Password was not changed");
                String sql = "UPDATE users SET user_name=?,user_login=?,procentage=? WHERE id_user=?";
                PreparedStatement preparedStatement = db.getConnection().prepareStatement(sql);
                preparedStatement.setString(1,name);
                preparedStatement.setString(2,login);
                preparedStatement.setInt(3,procentage);
                preparedStatement.setInt(4,id);
                preparedStatement.execute();

            }else {
                System.out.println("Password was changed");
                String sql = "UPDATE users SET user_name=?,user_login=?,user_password=?,procentage=? WHERE id_user=?";
                PreparedStatement preparedStatement = db.getConnection().prepareStatement(sql);
                preparedStatement.setString(1,name);
                preparedStatement.setString(2,login);
                preparedStatement.setString(3,pw_hash);
                preparedStatement.setInt(4,procentage);
                preparedStatement.setInt(5,id);
                preparedStatement.execute();
            }
            db.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (!(listSkills==null)){
            try {
                db.connect();
                    PreparedStatement preparedStatement = db.getConnection().prepareStatement("DELETE FROM skills WHERE id_user=?");
                    preparedStatement.setInt(1,id);
                    preparedStatement.execute();
                    for (Integer i : listSkills){
                        PreparedStatement preparedStatement2 = db.getConnection().prepareStatement("INSERT INTO skills (skill,id_user) VALUES (?,?)");
                        preparedStatement2.setInt(1,i);
                        preparedStatement2.setInt(2,id);
                        preparedStatement2.execute();
                    }
                db.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public HashSet<Integer> deleteUserIfPossible(int id_user){
        HashSet<Integer> listPrPartisipating = new HashSet<>();
        try {db.connect();
            PreparedStatement preparedStatement = db.getConnection().prepareStatement("SELECT * FROM user_work WHERE id_user=?");
            preparedStatement.setInt(1,id_user);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                listPrPartisipating.add(resultSet.getInt("project_id"));
            }
            if (listPrPartisipating==null){
                PreparedStatement preparedStatement4 = db.getConnection().prepareStatement("DELETE FROM user_work WHERE id_user=?");
                preparedStatement4.setInt(1,id_user);
                preparedStatement4.execute();
                PreparedStatement preparedStatement2 = db.getConnection().prepareStatement("DELETE FROM skills WHERE id_user=?");
                preparedStatement2.setInt(1,id_user);
                preparedStatement2.execute();
                PreparedStatement preparedStatement3 = db.getConnection().prepareStatement("DELETE FROM users WHERE id_user=?");
                preparedStatement3.setInt(1,id_user);
                preparedStatement3.execute();
            }
            db.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listPrPartisipating;
    }
    public void setPriceByIdSatgeToDb(int price, int stage){
        try {
            db.connect();
            PreparedStatement preparedStatement = db.getConnection().prepareStatement("UPDATE stages SET price_hour=? WHERE id_stage=?");
            preparedStatement.setInt(1,price);
            preparedStatement.setInt(2,stage);
            preparedStatement.execute();
            db.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setIsBusyDb(){

        try {
            db.connect();
            ArrayList<Integer> listBusyU = new ArrayList<>();

            Statement statement = db.getSt();
            ResultSet resultSet1 = statement.executeQuery("SELECT DISTINCT id_user FROM user_work");
            while (resultSet1.next()){
                listBusyU.add(resultSet1.getInt("id_user"));
            }
            Statement statement1 = db.getConnection().createStatement();
            statement1.execute("UPDATE users SET is_busy=0");
            PreparedStatement preparedStatement = db.getConnection().prepareStatement("UPDATE users SET is_busy=? WHERE id_user=?");
            for (Integer i: listBusyU){
                preparedStatement.setInt(1,1);
                preparedStatement.setInt(2,i);
                preparedStatement.execute();
            }
            db.closeConnection();
//            System.out.println(listProjects.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }





}
