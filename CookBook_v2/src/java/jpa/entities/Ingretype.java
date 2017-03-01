/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Haba
 */
@Entity
@Table(name = "ingretype")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ingretype.findAll", query = "SELECT i FROM Ingretype i"),
    @NamedQuery(name = "Ingretype.findByIngreType", query = "SELECT i FROM Ingretype i WHERE i.ingreType = :ingreType")})
public class Ingretype implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "ingreType")
    private String ingreType;
    @JoinTable(name = "ingredient", joinColumns = {
        @JoinColumn(name = "ingreType", referencedColumnName = "ingreType")}, inverseJoinColumns = {
        @JoinColumn(name = "recipeName", referencedColumnName = "recipeName")})
    @ManyToMany
    private Collection<Recipe> recipeCollection;

    public Ingretype() {
    }

    public Ingretype(String ingreType) {
        this.ingreType = ingreType;
    }

    public String getIngreType() {
        return ingreType;
    }

    public void setIngreType(String ingreType) {
        this.ingreType = ingreType;
    }

    @XmlTransient
    public Collection<Recipe> getRecipeCollection() {
        return recipeCollection;
    }

    public void setRecipeCollection(Collection<Recipe> recipeCollection) {
        this.recipeCollection = recipeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ingreType != null ? ingreType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ingretype)) {
            return false;
        }
        Ingretype other = (Ingretype) object;
        if ((this.ingreType == null && other.ingreType != null) || (this.ingreType != null && !this.ingreType.equals(other.ingreType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.Ingretype[ ingreType=" + ingreType + " ]";
    }
    
}
