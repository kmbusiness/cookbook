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
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Haba
 */
@ManagedBean(name = "RecipeView")
@RequestScoped
public class RecipeView implements Serializable{
    
    private static final long serialVersionUID = 2L;
    @ManagedProperty("#{param.book}")
    String book;
    ArrayList<Recipe> myList = new ArrayList<>();
    PreparedStatement ps = null;
    Connection con = null;
    ResultSet rs = null;
    Recipe thisRecipe = new Recipe();
    
    public RecipeView() {
        thisRecipe = new Recipe();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/morecookbook?zeroDateTimeBehavior=convertToNull", "root", "123456");
            String sql = "select * from Recipe";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Recipe rec = new Recipe();
                rec.setUserName(rs.getString("userName"));
                rec.setPushlishedDate(rs.getDate("pushlishedDate"));
                rec.setRecipeName(rs.getString("recipeName"));
                rec.setDescription(rs.getString("description"));
                rec.setSteps(rs.getString("steps"));
                rec.setRecipeID(rs.getString("recipeID"));
                rec.setImage(rs.getString("Image"));
                myList.add(rec);
            }
        } catch (ClassNotFoundException | SQLException e) {
        }
        
    }
    public List<Recipe> getMyList() {
        return myList;
    }
    
    public Recipe getThisRecipe() {
        for (int i = 0 ; i < myList.size(); i++) {
            if (myList.get(i).getRecipeName().compareTo(book) == 0) {
                thisRecipe = myList.get(i);
            }
        }
        return thisRecipe;
    }
    
    public void setThisRecipe(Recipe p) {
        thisRecipe = p;
    }
    
    public void setBook(String book) {
        this.book = book;
    }

    public String getBook() {
        return book;
    }
    
    public String view() {
        return "/Recipe/RecipeView";
    }
    
    public String save() {
        return "/Recipe/RecipeView.xhtml?faces-redirect=true";
    }
}
