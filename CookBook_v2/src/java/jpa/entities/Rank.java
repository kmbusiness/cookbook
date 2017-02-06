/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Haba
 */
@Entity
@Table(name = "rank")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rank.findAll", query = "SELECT r FROM Rank r"),
    @NamedQuery(name = "Rank.findByUserName", query = "SELECT r FROM Rank r WHERE r.rankPK.userName = :userName"),
    @NamedQuery(name = "Rank.findByRecipeName", query = "SELECT r FROM Rank r WHERE r.rankPK.recipeName = :recipeName"),
    @NamedQuery(name = "Rank.findByRank", query = "SELECT r FROM Rank r WHERE r.rankPK.rank = :rank")})
public class Rank implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RankPK rankPK;
    @JoinColumn(name = "userName", referencedColumnName = "userName", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Person person;
    @JoinColumn(name = "recipeName", referencedColumnName = "recipeName", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Recipe recipe;

    public Rank() {
    }

    public Rank(RankPK rankPK) {
        this.rankPK = rankPK;
    }

    public Rank(String userName, String recipeName, int rank) {
        this.rankPK = new RankPK(userName, recipeName, rank);
    }

    public RankPK getRankPK() {
        return rankPK;
    }

    public void setRankPK(RankPK rankPK) {
        this.rankPK = rankPK;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rankPK != null ? rankPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rank)) {
            return false;
        }
        Rank other = (Rank) object;
        if ((this.rankPK == null && other.rankPK != null) || (this.rankPK != null && !this.rankPK.equals(other.rankPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.Rank[ rankPK=" + rankPK + " ]";
    }
    
}
