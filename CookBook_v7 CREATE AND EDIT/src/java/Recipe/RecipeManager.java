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
import java.text.SimpleDateFormat;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Haba
 */
@ManagedBean(name = "RecipeManager")
@SessionScoped
public class RecipeManager implements Serializable {

    private static final long serialVersionUID = 1L;
    String book;
    String search = "";
    int max = 0, rows = 0, myIndex = -1;
    List<Recipe> recs = new ArrayList<>();
    PreparedStatement ps = null;
    Connection con = null;
    ResultSet rs = null;
    Recipe thisRecipe = new Recipe();
    Recipe newRecipe = new Recipe();
    String myName = "";
    ArrayList<String> asd = new ArrayList<String>();
    List mySearch = new ArrayList<Recipe>();
    String action = "";
    boolean view = false;
    /**
     * Creates a new instance of Recipe
     */
    public RecipeManager() {
        max = 0;
        rows = 0;
        Recipe thisRecipe = new Recipe();
        newRecipe = new Recipe();
        myName = "";
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        init();
    }

    public String getAction() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
	action = params.get("book");
        for (int i = 0 ; i < recs.size(); i++) {
            if (recs.get(i).getRecipeName().compareTo(action) == 0) {
                thisRecipe = recs.get(i);
            }
        }
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public List getMySearch() {
        return mySearch;
    }

    public void setMySearch(List mySearch) {
        this.mySearch = mySearch;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public Recipe getNewRecipe() {
        return newRecipe;
    }

    public void setNewRecipe(Recipe r) {
        newRecipe = r;
    }

    public int getMax() {
        max = 0;
        return getRecs().size();
    }

    public int getRows() {
        rows = 0;
        return (int) recs.size();
    }

    public List<Recipe> getRecs() {

        return recs;
    }

    public String getView(String name) {
        return "/Recipe/RecipeView";
    }

    public String getMyName() {
        return myName;
    }

    public Recipe getThisRecipe() {  
        if (view) getAction();
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

    public String indexview() {
        view = true;
        return "/Recipe/RecipeView";
    }
    
    public String editview() {
        return "/Recipe/RecipeView";
    }
    
    public String edit() {
        view = false;
        return "/Recipe/RecipeEdit";
    }
    
    public String save() {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/morecookbook?zeroDateTimeBehavior=convertToNull", "root", "123456");
            String sql = "INSERT INTO recipe (userName,pushlishedDate,recipeName,description,steps,recipeID,image) "
                    + "VALUES (?,?,?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, newRecipe.getUserName());
            String date = fmt.format(newRecipe.getPushlishedDate());
            java.sql.Date dt = java.sql.Date.valueOf(date);
            ps.setDate(2, dt);
            ps.setString(3, newRecipe.getRecipeName());
            ps.setString(4, newRecipe.getDescription());
            ps.setString(5, newRecipe.getSteps());
            ps.setString(6, newRecipe.getRecipeID());
            ps.setString(7, newRecipe.getImage());
            int i = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            /* Lazy */
        }
        newRecipe = new Recipe();
        init();
//        newRecipe = new Recipe();
        return "/index.xhtml";
    }
    
    public String searching() {
        mySearch = new ArrayList<Recipe>();
        for (int i = 0; i < recs.size(); i++) {
            if (recs.get(i).getRecipeName().equals(search))
                mySearch.add(recs.get(i));
        }
        search = "";
        return "/indexSearch.xhtml?faces-redirect=true";
    }

    public void init() {
        recs = new ArrayList<Recipe>();
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
                recs.add(rec);
            }
        } catch (ClassNotFoundException | SQLException e) {
        }
    }
}
