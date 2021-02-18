/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanetFoodDAO;

import PlanetFoodDbUtil.DbConnection;
import PlanetFoodPOJO.Categories;
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
public class CategoriesDao {
    public static boolean addCategories(Categories c)throws SQLException{
        Connection conn = DbConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("insert into categories values(?,?)");
        ps.setString(1, c.getCatId());
        ps.setString(2, c.getCatName());
        if(ps.executeUpdate()>0)
            return true;
        else 
            return false;
    }

    public static HashMap<String,String> getAllCategoryId()throws SQLException{
        Connection conn=DbConnection.getConnection();
        Statement st= conn.createStatement();
        ResultSet rs=st.executeQuery("Select cat_name,cat_id from categories");
        HashMap<String,String> categories=new HashMap<>();
        while(rs.next())
        {
            String catName=rs.getString(1);
            String catId=rs.getString(2);
            categories.put(catName,catId);
        }
        return categories;
    }

       public static ArrayList<Categories> getAllData()throws SQLException{
       Connection conn = DbConnection.getConnection();
       String qry = "Select * from categories";
       Statement st = conn.createStatement();
       ResultSet rs = st.executeQuery(qry);
       ArrayList<Categories> catList = new ArrayList<>();
       while(rs.next()){
       Categories e = new Categories();
       e.setCatId(rs.getString("cat_id"));
       e.setCatName(rs.getString("cat_name"));
       catList.add(e);
       }
       return catList;
   }

    public static boolean updateCat(Categories cat , String catId) throws SQLException{
        Connection conn=DbConnection.getConnection();
        String qry="update categories set CAT_NAME=? where CAT_ID=?";
        PreparedStatement ps=conn.prepareStatement(qry);
        ps.setString(1,cat.getCatName());
        System.out.println("catname    "+cat.getCatName());
        ps.setString(2,cat.getCatId());
        System.out.println("catid     "+cat.getCatId());
        if(ps.executeUpdate()>0)
            return true;
        else
            return false;
    }

    public static HashMap<String,String>getCategoryId()throws SQLException
    {
        Connection conn=DbConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("Select * from categories");
        HashMap<String,String> categories=new HashMap<>();
        while(rs.next())
        {
           String catId=rs.getString(1);
           String catName=rs.getString(2);
           categories.put(catId,catName);
        }
        return categories;
    }

}
