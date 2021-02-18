/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanetFoodDAO;

import PlanetFoodDbUtil.DbConnection;
import PlanetFoodPOJO.Employees;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Rita
 */
public class EmployeesDao {
     //Add Employee in the database
    public static boolean addEmployee(Employees e)throws SQLException{
            Connection conn = DbConnection.getConnection();                                      
            PreparedStatement ps = conn.prepareStatement("insert into employees values (?,?,?,?)");
            ps.setString(1, e.getEmpId());
            ps.setString(2, e.geteName());
            ps.setString(3, e.getJob());
            ps.setDouble(4, e.getSal());
            if(ps.executeUpdate()>0)
                return true;
            else 
                return false;
    }
    
    public static HashMap<String , String> getAllEmpId() throws SQLException{
    Connection conn = DbConnection.getConnection();
    Statement st = conn.createStatement();
    ResultSet rs = st.executeQuery("Select EMPID , JOB from employees");
    HashMap<String,String> empIds = new HashMap<>();
    while(rs.next()){
        String empId = rs.getString(1);
        String job = rs.getString(2);
        empIds.put(empId, job);
    }
    return empIds;
    }

    public static ArrayList<Employees> getAllData()throws SQLException{
       Connection conn = DbConnection.getConnection();
       String qry = "Select * from employees";
       Statement st = conn.createStatement();
       ResultSet rs = st.executeQuery(qry);
       ArrayList<Employees> empList = new ArrayList<>();
       while(rs.next()){
       Employees e = new Employees();
       e.setEmpId(rs.getString("EMPID"));
       e.seteName(rs.getString("ENAME"));
       e.setJob(rs.getString("JOB"));
       e.setSal(rs.getDouble("SAL"));
       empList.add(e);
       }
       return empList;
   }

        public static Employees searchEmp(String empno) throws SQLException{
        Connection conn = DbConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("select * from employees where EMPID =?");
        ps.setString(1, empno);
        Employees e =null;
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            e = new Employees();
           e.seteName(rs.getString("ENAME"));
           e.setSal(rs.getDouble("SAL"));
           e.setJob(rs.getString("JOB"));
       } 
        return e;
    }
        
       
        public static boolean deleteEmp(String empno)throws SQLException{
        Connection conn = DbConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("delete from employees where EMPID=?");
        ps.setString(1, empno);
        if(ps.executeUpdate()>0)
            return true;
        else
            return false;
    }

    public static ArrayList<Employees> getAllDataById(String empNo) throws SQLException{
       Connection conn = DbConnection.getConnection();
       PreparedStatement ps = conn.prepareStatement("Select * from employees where EMPID=?");
       ps.setString(1, empNo);
       ArrayList<Employees> empList = new ArrayList<>();
       ResultSet rs = ps.executeQuery();
       while(rs.next()){
       Employees e = new Employees();
       e.setEmpId(rs.getString("EMPID"));
       e.seteName(rs.getString("ENAME"));
       e.setJob(rs.getString("JOB"));
       e.setSal(rs.getDouble("SAL"));
       empList.add(e);
       }
       return empList;
    }

    public static Employees getJob(String empId) throws SQLException{
        Connection conn = DbConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("select JOB from employees where EMPID =?");
        ps.setString(1, empId);
        Employees e =null;
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
           e = new Employees();
           e.setJob(rs.getString("JOB"));
       } 
        return e;
    }

        public static boolean updateEmp(Employees e , String eno)throws SQLException{
        Connection conn = DbConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("update employees set ENAME=?,SAL=?,JOB=? where EMPID=?");
       ps.setString(1,e.geteName());
       ps.setDouble(2,e.getSal());
       ps.setString(3,e.getJob());
       ps.setString(4,e.getEmpId());
           
            if(ps.executeUpdate()>0)
                return true;
            else 
                return false;
  }
    
        
        public static HashMap<String,String>getAllJobName()throws SQLException
    {
        Connection conn=DbConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("Select * from employees");
        HashMap<String,String> empList=new HashMap<>();
      
        while(rs.next())
        {
            String job=rs.getString(3);
            String Ename=rs.getString(2);
            empList.put(job,Ename);
        }
        return empList;
    }


}
