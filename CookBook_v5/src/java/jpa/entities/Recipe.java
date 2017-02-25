/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Haba
 */
@Entity
@Table(name = "recipe")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recipe.findAll", query = "SELECT r FROM Recipe r"),
    @NamedQuery(name = "Recipe.findByPushlishedDate", query = "SELECT r FROM Recipe r WHERE r.pushlishedDate = :pushlishedDate"),
    @NamedQuery(name = "Recipe.findByRecipeName", query = "SELECT r FROM Recipe r WHERE r.recipeName = :recipeName"),
    @NamedQuery(name = "Recipe.findByDescription", query = "SELECT r FROM Recipe r WHERE r.description = :description"),
    @NamedQuery(name = "Recipe.findBySteps", query = "SELECT r FROM Recipe r WHERE r.steps = :steps"),
    @NamedQuery(name = "Recipe.findByRecipeID", query = "SELECT r FROM Recipe r WHERE r.recipeID = :recipeID")})
public class Recipe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "pushlishedDate")
    @Temporal(TemporalType.DATE)
    private Date pushlishedDate;
    @Size(max = 30)
    @Column(name = "recipeName")
    private String recipeName;
    @Size(max = 10000)
    @Column(name = "description")
    private String description;
    @Size(max = 10000)
    @Column(name = "steps")
    private String steps;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "recipeID")
    private String recipeID;
    @OneToMany(mappedBy = "recipeID")
    private Collection<Ingredient> ingredientCollection;
    @JoinColumn(name = "userName", referencedColumnName = "userName")
    @ManyToOne
    private Owner userName;

    public Recipe() {
    }

    public Recipe(String recipeID) {
        this.recipeID = recipeID;
    }

    public Date getPushlishedDate() {
        return pushlishedDate;
    }

    public void setPushlishedDate(Date pushlishedDate) {
        this.pushlishedDate = pushlishedDate;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public String getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(String recipeID) {
        this.recipeID = recipeID;
    }

    @XmlTransient
    public Collection<Ingredient> getIngredientCollection() {
        return ingredientCollection;
    }

    public void setIngredientCollection(Collection<Ingredient> ingredientCollection) {
        this.ingredientCollection = ingredientCollection;
    }

    public Owner getUserName() {
        return userName;
    }

    public void setUserName(Owner userName) {
        this.userName = userName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recipeID != null ? recipeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recipe)) {
            return false;
        }
        Recipe other = (Recipe) object;
        if ((this.recipeID == null && other.recipeID != null) || (this.recipeID != null && !this.recipeID.equals(other.recipeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.Recipe[ recipeID=" + recipeID + " ]";
    }
    
}
