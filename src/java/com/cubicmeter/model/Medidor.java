/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cubicmeter.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "MEDIDOR")
@XmlRootElement
public class Medidor implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MedidorPK medidorPK;
    @Basic(optional = false)
    @Column(name = "NOMMEDIDOR")
    private String nommedidor;
//    @JoinColumn(name = "IDGRUPO", referencedColumnName = "IDGRUPO", insertable = false, updatable = false)
//    @ManyToOne(optional = false)
//    private Grupos grupos;
//    @OneToMany(mappedBy = "medidor")
//    private List<Local> localList;

    public Medidor() {
    }

    public Medidor(MedidorPK medidorPK) {
        this.medidorPK = medidorPK;
    }

    public Medidor(MedidorPK medidorPK, String nommedidor) {
        this.medidorPK = medidorPK;
        this.nommedidor = nommedidor;
    }

    public Medidor(int idgrupo, int idmedidor) {
        this.medidorPK = new MedidorPK(idgrupo, idmedidor);
    }

    public MedidorPK getMedidorPK() {
        return medidorPK;
    }

    public void setMedidorPK(MedidorPK medidorPK) {
        this.medidorPK = medidorPK;
    }

    public String getNommedidor() {
        return nommedidor;
    }

    public void setNommedidor(String nommedidor) {
        this.nommedidor = nommedidor;
    }

//    public Grupos getGrupos() {
//        return grupos;
//    }
//
//    public void setGrupos(Grupos grupos) {
//        this.grupos = grupos;
//    }
//
//    @XmlTransient
//    public List<Local> getLocalList() {
//        return localList;
//    }
//
//    public void setLocalList(List<Local> localList) {
//        this.localList = localList;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (medidorPK != null ? medidorPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medidor)) {
            return false;
        }
        Medidor other = (Medidor) object;
        if ((this.medidorPK == null && other.medidorPK != null) || (this.medidorPK != null && !this.medidorPK.equals(other.medidorPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cubicmeter.model.Medidor[ medidorPK=" + medidorPK + " ]";
    }
    
}
