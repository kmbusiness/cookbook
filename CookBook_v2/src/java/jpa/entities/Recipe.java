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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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
    @NamedQuery(name = "Recipe.findByRecipeName", query = "SELECT r FROM Recipe r WHERE r.recipeName = :recipeName")})
public class Recipe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "pushlishedDate")
    @Temporal(TemporalType.DATE)
    private Date pushlishedDate;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "recipeName")
    private String recipeName;
    @ManyToMany(mappedBy = "recipeCollection")
    private Collection<Ingretype> ingretypeCollection;
    @JoinColumn(name = "userName", referencedColumnName = "userName")
    @ManyToOne
    private Person userName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Collection<Rank> rankCollection;

    public Recipe() {
    }

    public Recipe(String recipeName) {
        this.recipeName = recipeName;
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

    @XmlTransient
    public Collection<Ingretype> getIngretypeCollection() {
        return ingretypeCollection;
    }

    public void setIngretypeCollection(Collection<Ingretype> ingretypeCollection) {
        this.ingretypeCollection = ingretypeCollection;
    }

    public Person getUserName() {
        return userName;
    }

    public void setUserName(Person userName) {
        this.userName = userName;
    }

    @XmlTransient
    public Collection<Rank> getRankCollection() {
        return rankCollection;
    }

    public void setRankCollection(Collection<Rank> rankCollection) {
        this.rankCollection = rankCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recipeName != null ? recipeName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recipe)) {
            return false;
        }
        Recipe other = (Recipe) object;
        if ((this.recipeName == null && other.recipeName != null) || (this.recipeName != null && !this.recipeName.equals(other.recipeName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.Recipe[ recipeName=" + recipeName + " ]";
    }
    
}
