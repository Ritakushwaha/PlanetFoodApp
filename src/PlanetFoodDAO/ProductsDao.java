/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanetFoodDAO;

import PlanetFoodDbUtil.DbConnection;
import PlanetFoodPOJO.Products;
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
public class ProductsDao {
    public static boolean addProduct(Products p)throws SQLException{
        Connection conn=DbConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("Insert into products values(?,?,?,?,?)");
        ps.setString(1, p.getProdId());
        ps.setString(2, p.getCatId());
        ps.setString(3, p.getProductName());
        ps.setDouble(4, p.getProductPrice());
        ps.setString(5, p.getIsActive());
        int x=ps.executeUpdate();
        if(x>0)
            return true;
        else
            return false;
    }


    
    public static HashMap<String , String> getAllCategoryId() throws SQLException{
    Connection conn = DbConnection.getConnection();
    Statement st = conn.createStatement();
    ResultSet rs = st.executeQuery("Select cat_name,cat_id from categories ");
    HashMap<String,String> categories = new HashMap<>();
    while(rs.next()){
        String catName = rs.getString(1);
        String catId = rs.getString(2);
        categories.put(catName, catId);
    }
    return categories;
    }
    
    public static String getNewID()throws SQLException{
        Connection conn=DbConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("Select count(*) from products");
        int id=101;
        ResultSet rs=ps.executeQuery();
        if(rs.next())
        {
            id=id+rs.getInt(1);
         }
        return "P"+id;
    }

    
   public static HashMap<String , Products> getProductById() throws SQLException{
    Connection conn = DbConnection.getConnection();
    String qry = ("Select * from products where cat_id=? ");
    PreparedStatement ps = conn.prepareStatement(qry);
    HashMap<String,Products> productList = new HashMap<>();
    ps.setString(1, "cat_Id");
    ResultSet rs = ps.executeQuery();
    while(rs.next()){
        Products p = new Products();
        p.setCatId("cat_Id");
        p.setProdId(rs.getString("prod_Id"));
        p.setProductName(rs.getString("prod_name"));
        p.setProductPrice(rs.getDouble("prod_price"));
        p.setIsActive(rs.getString("active"));
        productList.put(p.getProdId(), p);  
    }
    return productList;
    }
   
   
   public static ArrayList<Products> getAllData()throws SQLException{
       Connection conn = DbConnection.getConnection();
       String qry = "Select * from products";
       Statement st = conn.createStatement();
       ResultSet rs = st.executeQuery(qry);
       ArrayList<Products> prodList = new ArrayList<>();
       while(rs.next()){
       Products p = new Products();
       p.setCatId(rs.getString("cat_id"));
       p.setProdId(rs.getString("prod_id"));
       p.setProductName(rs.getString("prod_name"));
       p.setProductPrice(rs.getDouble("prod_price"));
       p.setIsActive(rs.getString("active"));
       prodList.add(p);
       }
       return prodList;
   }
    
   public static HashMap<String,Products> getProductsByCategory(String catId) throws SQLException
    {
       Connection conn=DbConnection.getConnection();
       String qry="Select * from Products where cat_id=?";
       PreparedStatement ps=conn.prepareStatement(qry);
       HashMap<String,Products> productList=new HashMap<String,Products>();
       ps.setString(1, catId);
       ResultSet rs=ps.executeQuery();
       while(rs.next()){
            Products p=new Products();
            p.setCatId(catId);
            p.setProdId(rs.getString("prod_id"));
            p.setProductName(rs.getString("prod_name"));
            p.setProductPrice(rs.getDouble("prod_price"));
            p.setIsActive(rs.getString("active"));
            productList.put(p.getProdId(), p);
          }
       return productList;
    }

    public static boolean updateProduct(Products p )throws SQLException{
        Connection conn = DbConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("update products set PROD_NAME=? ,PROD_PRICE=? ,ACTIVE=? where PROD_ID = ?");
        ps.setString(1,p.getProductName());
        ps.setDouble(2,p.getProductPrice());
        ps.setString(3, p.getIsActive());
        ps.setString(4, p.getProdId());
        if(ps.executeUpdate()>0)
                return true;
        else 
                return false;
    }
    
    public static boolean deleteProduct(String prodId)throws SQLException{
        Connection conn = DbConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("delete from products where PROD_ID=?");
        ps.setString(1, prodId);
        if(ps.executeUpdate()>0)
            return true;
        else
            return false;
    }

        public static ArrayList<Products> getAllDataById(String catId) throws SQLException{
       Connection conn = DbConnection.getConnection();
       PreparedStatement ps = conn.prepareStatement("Select * from products where cat_id=?");
       ps.setString(1, catId);
       ArrayList<Products> productList = new ArrayList<>();
       ps.setString(1, catId);
       ResultSet rs=ps.executeQuery();
       while(rs.next()){
            Products p=new Products();
            p.setCatId(catId);
            p.setProdId(rs.getString("prod_id"));
            p.setProductName(rs.getString("prod_name"));
            p.setProductPrice(rs.getDouble("prod_price"));
            p.setIsActive(rs.getString("active"));
            productList.add(p);
          }
       return productList;
    }
    
}
