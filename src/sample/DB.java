package sample;

import java.sql.*;

public class DB {
    final String url = "jdbc:mysql://localhost/web_service?autoReconnect=true&useSSL=false";
    String userName = "root";
    String password = "root";
    Connection conn = null;
    Statement st = null;

    public DB() {

    }

    public void connect(){
        try {
            conn = DriverManager.getConnection(url,userName,password);
            st = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void closeConnection() throws SQLException{
        st.close();
        conn.close();
    }

    public Statement getSt() {
        return st;
    }

    public Connection getConnection(){
        return conn;
    }

    public ResultSet getSetForPassByName(String name) {
        ResultSet rs = null;
        try {
            String selectionSQL = "SELECT * FROM web_service.users WHERE user_login=?";
            PreparedStatement ps = conn.prepareStatement(selectionSQL);
            ps.setString(1,name);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        CallableStatement myCall = null;
//        try {
//            String s = "{call get_hach_by_name(?) }";
//            myCall = conn.prepareCall(s);
//            myCall.setString(1,name);
//            myCall.execute();
//            return myCall;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return myCall;
        return rs;
    }



//    public void getCompFromDBAL(){
//        try {
////            Connection connection = DriverManager.getConnection(url,user,password);
//            Statement st = connection.createStatement();
//            ResultSet resultSet = st.executeQuery("SELECT * FROM shop");
//
////            int k = st.executeUpdate("UPDATE auto SET price=25000 WHERE speed>200");
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }
}
