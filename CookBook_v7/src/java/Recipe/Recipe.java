package Recipe;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import java.util.Date;

@ManagedBean(name = "Recipe")
@RequestScoped
public class Recipe {

    private String userName;
    private Date publishedDate;
    private String recipeName;
    private String description;
    private String steps;
    private String Image;
    private long recipeID;

    public void setImage(String Image) {
        this.Image = Image;
    }

    public String getImage() {
        return Image;
    }
    
    public String getUserName() {
        return userName;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public String getDescription() {
        return description;
    }

    public String getSteps() {
        return steps;
    }

    public long getRecipeID() {
        return recipeID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public void setRecipeID(long recipeID) {
        this.recipeID = recipeID;
    }
    
    public String toString() {
        return recipeName + " " + description;
    }
}
