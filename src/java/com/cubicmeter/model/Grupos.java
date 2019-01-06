/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cubicmeter.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mireya Flores
 */
@Entity
@Table(name = "GRUPOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grupos.findAll", query = "SELECT g FROM Grupos g")
    , @NamedQuery(name = "Grupos.findByIdgrupo", query = "SELECT g FROM Grupos g WHERE g.idgrupo = :idgrupo")
    , @NamedQuery(name = "Grupos.findByNomgrupo", query = "SELECT g FROM Grupos g WHERE g.nomgrupo = :nomgrupo")
    , @NamedQuery(name = "Grupos.findByIdrel", query = "SELECT g FROM Grupos g WHERE g.idrel = :idrel")})
public class Grupos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDGRUPO")
    private Integer idgrupo;
    @Basic(optional = false)
    @Column(name = "NOMGRUPO")
    private String nomgrupo;
    @Column(name = "IDREL")
    private Integer idrel;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grupos")
//    private List<Medidor> medidorList;

    public Grupos() {
    }

    public Grupos(Integer idgrupo) {
        this.idgrupo = idgrupo;
    }

    public Grupos(Integer idgrupo, String nomgrupo) {
        this.idgrupo = idgrupo;
        this.nomgrupo = nomgrupo;
    }

    public Integer getIdgrupo() {
        return idgrupo;
    }

    public void setIdgrupo(Integer idgrupo) {
        this.idgrupo = idgrupo;
    }

    public String getNomgrupo() {
        return nomgrupo;
    }

    public void setNomgrupo(String nomgrupo) {
        this.nomgrupo = nomgrupo;
    }

    public Integer getIdrel() {
        return idrel;
    }

    public void setIdrel(Integer idrel) {
        this.idrel = idrel;
    }

//    @XmlTransient
//    public List<Medidor> getMedidorList() {
//        return medidorList;
//    }
//
//    public void setMedidorList(List<Medidor> medidorList) {
//        this.medidorList = medidorList;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idgrupo != null ? idgrupo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grupos)) {
            return false;
        }
        Grupos other = (Grupos) object;
        if ((this.idgrupo == null && other.idgrupo != null) || (this.idgrupo != null && !this.idgrupo.equals(other.idgrupo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cubicmeter.model.Grupos[ idgrupo=" + idgrupo + " ]";
    }
    
}
