/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanetFoodDAO;

import PlanetFoodDbUtil.DbConnection;
import PlanetFoodPOJO.OrderDetails;
import PlanetFoodPOJO.Orders;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Shahnaz
 */
public class OrderDetailsDao {
    
  //take_Order
    public static String getNewId()throws SQLException
    {
        Connection conn=DbConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("Select count(*) from orders");
       int id=101;
        if(rs.next())
        {
            id=id+rs.getInt(1);
            
        }
        return "ORD"+id;
    }
     
//Take_Orders
    public static boolean addOrders(Orders order,ArrayList<OrderDetails>orderList)throws SQLException,ParseException
    {
        Connection conn=DbConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("insert into orders values(?,?,?,?,?,?,?,?)");
        ps.setString(1,order.getOrdId());
        
        String dateStr=order.getOrdDate();
        SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy");
        java.util.Date d1= sdf.parse(dateStr);
        java.sql.Date d2=new java.sql.Date(d1.getTime());
        ps.setDate(2,d2);
        ps.setDouble(3,order.getGst());
        ps.setDouble(4,order.getGstAmount());
        ps.setDouble(5,order.getDiscount());
        ps.setDouble(6,order.getGrandTotal());
        ps.setString(7,order.getUserId());
        ps.setDouble(8,order.getOrdAmount());
        
        int x=ps.executeUpdate();
        
        PreparedStatement ps2=conn.prepareStatement("insert into order_details values(?,?,?,?)");
        
        int Count=0,y;
        for(OrderDetails details:orderList)
        {
            ps2.setString(1,details.getOrdId());
            ps2.setString(2,details.getProdId());
            ps2.setDouble(3,details.getQuantity());
            ps2.setDouble(4,details.getCost());
            
            y=ps2.executeUpdate();
            if(y>0)
            Count=Count+y;
            
             }
              if(x>0 && Count==orderList.size())
              {
                  return true;
              }
              else
              {
                      return false;
                      }
        
    }
    
public static HashMap<String , String> getOrdId() throws SQLException{
    Connection conn = DbConnection.getConnection();
    Statement st = conn.createStatement();
    ResultSet rs = st.executeQuery("Select ord_id , prod_id from order_details");
    HashMap<String,String> empIds = new HashMap<>();
    while(rs.next()){
        String ordId = rs.getString(1);
        String prod_id = rs.getString(2);
        empIds.put(ordId, prod_id);
    }
    return empIds;
    }

        public static OrderDetails searchOrd(String ordId) throws SQLException{
        Connection conn = DbConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("select * from order_details where ord_id =?");
        ps.setString(1, ordId);
        OrderDetails e =null;
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            e = new OrderDetails();
           e.setCost(rs.getDouble("Cost"));
           e.setQuantity(rs.getDouble("Quantity"));
           e.setProdId(rs.getString("Prod_id"));
       } 
        return e;
    }
}
