/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Haba
 */
@Entity
@Table(name = "rguest")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rguest.findAll", query = "SELECT r FROM Rguest r"),
    @NamedQuery(name = "Rguest.findByUserName", query = "SELECT r FROM Rguest r WHERE r.userName = :userName"),
    @NamedQuery(name = "Rguest.findBySignUpDate", query = "SELECT r FROM Rguest r WHERE r.signUpDate = :signUpDate")})
public class Rguest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "userName")
    private String userName;
    @Column(name = "signUpDate")
    @Temporal(TemporalType.DATE)
    private Date signUpDate;
    @JoinColumn(name = "userName", referencedColumnName = "userName", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Person person;

    public Rguest() {
    }

    public Rguest(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getSignUpDate() {
        return signUpDate;
    }

    public void setSignUpDate(Date signUpDate) {
        this.signUpDate = signUpDate;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userName != null ? userName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rguest)) {
            return false;
        }
        Rguest other = (Rguest) object;
        if ((this.userName == null && other.userName != null) || (this.userName != null && !this.userName.equals(other.userName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.Rguest[ userName=" + userName + " ]";
    }
    
}
