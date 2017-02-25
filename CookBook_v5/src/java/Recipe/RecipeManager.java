/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recipe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Haba
 */
@ManagedBean(name = "RecipeManager")
@SessionScoped
public class RecipeManager {
    int max = 0, rows = 0;
    List<Recipe> recs = new ArrayList<>();
    PreparedStatement ps = null;
    Connection con = null;
    ResultSet rs = null;
    Recipe thisRecipe = new Recipe();
    String myName = "";
    /**
     * Creates a new instance of Recipe
     */
    public RecipeManager() {
        max = 0;
        rows = 0;
        List<Recipe> recs = new ArrayList<>();
        Recipe thisRecipe = new Recipe();
        myName = "";
    }
    
    public int getMax() {
        max = 0;
        return getRecs().size();
    }
    
    public int getRows() {
        rows = 0;
        return (int) Math.ceil(getMax() / 4);
    }
    
    public List<Recipe> getRecs() {
        List<Recipe> recs = new ArrayList<>();
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/morecookbook?zeroDateTimeBehavior=convertToNull", "root", "123456");
            String sql = "select * from Recipe";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Recipe rec = new Recipe();
                rec.setUserName(rs.getString("userName"));
                rec.setPublishedDate(rs.getDate("pushlishedDate"));
                rec.setRecipeName(rs.getString("recipeName"));
                rec.setDescription(rs.getString("description"));
                rec.setSteps(rs.getString("steps"));
                rec.setRecipeID(rs.getLong("recipeID"));
                recs.add(rec);
            }
//            while (rs.next()) {
//                User usr = new User();
//                usr.setUserID(rs.getLong("userId"));
//                usr.setName(rs.getString("name"));
//                usr.setAddress(rs.getString("address"));
//                usr.setCreated_date(rs.getDate("created_date"));
//                list.add(usr);
//            }
        } catch (ClassNotFoundException | SQLException e) {
        }
        return recs;
    }
    
    public String getView(String name) {
        
        name = name.replaceAll("\\s",""); 
        myName = name;
        Recipe myres = new Recipe();
        for (int i = 0 ; i < recs.size(); i++)
            if (recs.get(i).getRecipeName().equals(name))
                myres = recs.get(i);
        thisRecipe = myres;
        return "MyView";
    }
    
    public String getMyName() {
        return myName;
    }
    public Recipe getThisRecipe() {
        return thisRecipe;
    }
    
    public void setThisRecipe(Recipe r) { 
        thisRecipe = r;
    }
}
