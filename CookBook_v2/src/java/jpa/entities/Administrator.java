/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Haba
 */
@Entity
@Table(name = "administrator")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Administrator.findAll", query = "SELECT a FROM Administrator a"),
    @NamedQuery(name = "Administrator.findByAdminAcess", query = "SELECT a FROM Administrator a WHERE a.adminAcess = :adminAcess"),
    @NamedQuery(name = "Administrator.findByUserName", query = "SELECT a FROM Administrator a WHERE a.userName = :userName")})
public class Administrator implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "adminAcess")
    private Boolean adminAcess;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "userName")
    private String userName;
    @JoinColumn(name = "job", referencedColumnName = "job")
    @ManyToOne
    private Jobs job;
    @JoinColumn(name = "userName", referencedColumnName = "userName", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Person person;

    public Administrator() {
    }

    public Administrator(String userName) {
        this.userName = userName;
    }

    public Boolean getAdminAcess() {
        return adminAcess;
    }

    public void setAdminAcess(Boolean adminAcess) {
        this.adminAcess = adminAcess;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Jobs getJob() {
        return job;
    }

    public void setJob(Jobs job) {
        this.job = job;
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
        if (!(object instanceof Administrator)) {
            return false;
        }
        Administrator other = (Administrator) object;
        if ((this.userName == null && other.userName != null) || (this.userName != null && !this.userName.equals(other.userName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.Administrator[ userName=" + userName + " ]";
    }
    
}
