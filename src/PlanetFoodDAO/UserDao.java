/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanetFoodDAO;

import PlanetFoodDbUtil.DbConnection;
import PlanetFoodPOJO.Employees;
import PlanetFoodPOJO.User;
import PlanetFoodPOJO.UserProfile;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Rita
 */
public class UserDao {
    public static String validateUser(User user)throws SQLException
    {
        Connection conn=DbConnection.getConnection();
        String qry="Select username from Users where userid=? and password=? and usertype=?";
        PreparedStatement ps=conn.prepareStatement(qry);
        ps.setString(1 , user.getUserId());
        ps.setString(2 , user.getPassword());
        ps.setString(3 , user.getUserType());
        ResultSet rs=ps.executeQuery();
        String username=null; 
        if(rs.next())
           {
               username=rs.getString(1);
           }
    return username;
 }
    
    public static boolean AddCashier(User user,UserProfile userProfile,Employees emp)throws SQLException
    {
        Connection conn=DbConnection.getConnection();
        String qry="insert into users values(?,?,?,?,?)";
        PreparedStatement ps=conn.prepareStatement(qry);
        ps.setString(1,user.getUserId());
        ps.setString(2,emp.geteName());
        ps.setString(3,user.getPassword());
        ps.setString(4,emp.getEmpId());
        ps.setString(5,user.getUserType());
        int x=ps.executeUpdate();
        if(x>0)
            return true;
        else
            return false;
    }

    public static ArrayList<User> getAllDataById(String userId) throws SQLException {
         Connection conn = DbConnection.getConnection();
       PreparedStatement ps = conn.prepareStatement("Select * from users where USERID=?");
       ps.setString(1, userId);
       ArrayList<User> userList = new ArrayList<>();
       ResultSet rs = ps.executeQuery();
       while(rs.next()){
       User e = new User();
       e.setUserId(rs.getString("USERID"));
       e.setUserName(rs.getString("USERNAME"));
       e.setEmpId(rs.getString("EMPID"));
       e.setPassword(rs.getString("PASSWORD"));
       e.setUserType(rs.getString("USERTYPE"));
       userList.add(e);
       }
       return userList;
    }

    public static boolean deleteUser(String userId) throws SQLException {
        Connection conn = DbConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("delete from users where USERID=?");
        ps.setString(1, userId);
        if(ps.executeUpdate()>0)
            return true;
        else
            return false;
    }

}