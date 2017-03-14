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
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Haba
 */
@ManagedBean(name = "RecipeManager")
@SessionScoped
public class RecipeManager implements Serializable{
    
    private static final long serialVersionUID = 1L;
    String book;
    int max = 0, rows = 0, myIndex = -1;
    List<Recipe> recs = new ArrayList<>();
    PreparedStatement ps = null;
    Connection con = null;
    ResultSet rs = null;
    Recipe thisRecipe = new Recipe();
    String myName = "";
    ArrayList<String> asd = new ArrayList<String>();
    /**
     * Creates a new instance of Recipe
     */
    public RecipeManager() {
        max = 0;
        rows = 0;
        Recipe thisRecipe = new Recipe();
        myName = "";
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
        } catch (ClassNotFoundException | SQLException e) {
        }
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
        
        return recs;
    }
    
    public String getView(String name) {
//        FacesContext fc = FacesContext.getCurrentInstance();
//        myName = fc.getExternalContext().getRequestParameterMap().get("book");
//        myName = "walalal";
//        name = name.replaceAll("\\s",""); 
//        myName = name;
//        myName = book;`
//        for (int i = 0 ; i < recs.size(); i++) {
//            asd.add(recs.get(i).getRecipeName());
//            if (recs.get(i).getRecipeName().compareTo("Hamburger") == 0) {
//                thisRecipe = recs.get(i);
//                myIndex = i;
//            }
//        }
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
    
    public void setBook(String book) {
        this.book = book;
    }

    public String getBook() {
        return book;
    }
    
    public int getMyIndex() {
        return myIndex;
    }
    
    public ArrayList<String> getAsd() {
        return asd;
    }
    
    @RequestScoped
    public class ManageView implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @ManagedProperty("#{param.book}")
    String book;
    List<Recipe> myList = new ArrayList<>();
    Recipe thisRecipe = new Recipe();
    
    public void getList() {
        myList = getRecs();
    }
    
    public List<Recipe> getMyList() {
        return myList;
    }
    public Recipe getThisRecipe() {
        return thisRecipe;
    }
    
    public void setBook(String book) {
        this.book = book;
    }

    public String getBook() {
        return book;
    }
    
    public String view() {
        for (int i = 0 ; i < myList.size(); i++) {
            if (myList.get(i).getRecipeName().compareTo(book) == 0) {
                thisRecipe = myList.get(i);
            }
        }
        return "MyView";
    }
}

}
