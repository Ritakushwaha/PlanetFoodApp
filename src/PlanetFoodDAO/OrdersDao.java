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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Rita
 */
public class OrdersDao {
    
    public static ArrayList<Orders> getOrders()throws SQLException
    {Connection conn=DbConnection.getConnection();
    PreparedStatement ps=conn.prepareStatement("select * from Orders " );
     
     ArrayList <Orders> orderList=new ArrayList <Orders>();
     ResultSet rs=ps.executeQuery();
     while(rs.next())
     {
     Orders obj=new Orders();
     obj.setOrdId(rs.getString("ord_id"));
     SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
     java.sql.Date d=rs.getDate("ord_date");
     String dateStr=sdf.format(d);
     obj.setOrdDate(dateStr);
     obj.setOrdAmount(rs.getDouble("ord_amount"));
 obj.setGst(rs.getDouble("gst"));
 obj.setGstAmount(rs.getDouble("gst_amount"));
 obj.setGrandTotal(rs.getDouble("grand_total"));
 obj.setUserId(rs.getString("userid"));
 obj.setDiscount(rs.getDouble("discount"));
 orderList.add(obj);
 
     }
     return orderList;
            }
    
    public static ArrayList<Orders> getOrdersByDate(Date startDate,Date endDate)throws SQLException
    {
       Connection conn=DbConnection.getConnection();
       PreparedStatement ps=conn.prepareStatement("Select * from Orders where ord_date between ? and ?");
       long ms1=startDate.getTime();
       long ms2=endDate.getTime();
       java.sql.Date sdate=new java.sql.Date(ms1);
       java.sql.Date edate=new java.sql.Date(ms2);
       ps.setDate(1, sdate);
       ps.setDate(2, edate);
       ResultSet rs=ps.executeQuery();
       ArrayList<Orders> orderList=new ArrayList<Orders>();
       while(rs.next()){
               Orders obj=new Orders();
               obj.setOrdId(rs.getString("ord_id"));
               java.sql.Date d=rs.getDate("ord_date");
               SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy");
               String dateStr=sdf.format(d);
               obj.setOrdDate(dateStr);
               obj.setOrdAmount(rs.getDouble("ord_amount"));
               obj.setGst(rs.getDouble("gst"));
               obj.setGstAmount(rs.getDouble("gst_amount"));
               obj.setGrandTotal(rs.getDouble("grand_total"));
               obj.setDiscount(rs.getDouble("discount"));
               obj.setUserId(rs.getString("userid"));
               orderList.add(obj);  
          }
       return orderList;
         
    }
    public static ArrayList<Orders> getAllOrders() throws SQLException {
       Connection conn = DbConnection.getConnection();
       String qry = "Select * from orders";
       Statement st = conn.createStatement();
       ResultSet rs = st.executeQuery(qry);
       ArrayList<Orders> orderList = new ArrayList<>();
       while(rs.next()){
       Orders e = new Orders();
       e.setOrdId(rs.getString("ORD_ID"));
       e.setOrdDate(rs.getDate("ORD_DATE").toString());
       e.setDiscount(rs.getDouble("DISCOUNT"));
       e.setGrandTotal(rs.getDouble("GRAND_TOTAL"));
       e.setGst(rs.getDouble("GST"));
       e.setGstAmount(rs.getDouble("GST_AMOUNT"));
       e.setOrdAmount(rs.getDouble("ORD_AMOUNT"));
       orderList.add(e);
       }
       return orderList;
    }
    
    public static String getNewID()throws SQLException{
        Connection conn=DbConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("Select count(*) from Orders");
        int id=101;
        ResultSet rs=ps.executeQuery();
        if(rs.next())
        {
            id=id+rs.getInt(1);
         }
        return "OD"+id;
    }

    public static boolean addOrder(Orders order,ArrayList<OrderDetails> orderList)throws Exception{
        Connection conn=DbConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("Insert into orders values(?,?,?,?,?,?,?,?)");
        ps.setString(1, order.getOrdId());
        String dateStr=order.getOrdDate();
        SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy");
        java.util.Date d1=sdf.parse(dateStr);
        java.sql.Date d2=new java.sql.Date(d1.getTime());
        ps.setDate(2, d2);
        ps.setDouble(3,order.getGst());
        ps.setDouble(4, order.getGstAmount());
        ps.setDouble(5, order.getDiscount());
        ps.setDouble(6, order.getGrandTotal());
        ps.setString(7,order.getUserId());
        ps.setDouble(8, order.getOrdAmount());
        int x=ps.executeUpdate();
        PreparedStatement ps2=conn.prepareStatement("Insert into order_details values(?,?,?,?)");
        int count=0,y;
        for(OrderDetails detail:orderList)
        {
            ps2.setString(1, detail.getOrdId());
            ps2.setString(2, detail.getProdId());
            ps2.setDouble(3, detail.getQuantity());
            ps2.setDouble(4, detail.getCost());
            y=ps2.executeUpdate();
            count=count+y;
        }
        if(x>0 && count==orderList.size())
            return true;
        else
            return false;
    }

}
