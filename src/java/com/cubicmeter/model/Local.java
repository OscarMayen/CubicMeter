/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cubicmeter.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mireya Flores
 */
@Entity
@Table(name = "LOCAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Local.findAll", query = "SELECT l FROM Local l")
    , @NamedQuery(name = "Local.findByIdlocal", query = "SELECT l FROM Local l WHERE l.idlocal = :idlocal")
    , @NamedQuery(name = "Local.findByNomlocal", query = "SELECT l FROM Local l WHERE l.nomlocal = :nomlocal")})
public class Local implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDLOCAL")
    private Integer idlocal;
    @Basic(optional = false)
    @Column(name = "NOMLOCAL")
    private String nomlocal;
    @Column(name = "IDGRUPO")
    private Integer idgrupo;
    @Column(name = "IDMEDIDOR")
    private Integer idmedidor;
    @Column(name = "IDCLIENTE")
    private Integer idclient;

    public Local() {
    }

    public Local(Integer idlocal) {
        this.idlocal = idlocal;
    }

    public Local(Integer idlocal, String nomlocal) {
        this.idlocal = idlocal;
        this.nomlocal = nomlocal;
    }

    public Integer getIdlocal() {
        return idlocal;
    }

    public void setIdlocal(Integer idlocal) {
        this.idlocal = idlocal;
    }

    public String getNomlocal() {
        return nomlocal;
    }

    public void setNomlocal(String nomlocal) {
        this.nomlocal = nomlocal;
    }

    public Integer getIdgrupo() {
        return idgrupo;
    }

    public void setIdgrupo(Integer idgrupo) {
        this.idgrupo = idgrupo;
    }

    public Integer getIdmedidor() {
        return idmedidor;
    }

    public void setIdmedidor(Integer idmedidor) {
        this.idmedidor = idmedidor;
    }

    public Integer getIdclient() {
        return idclient;
    }

    public void setIdclient(Integer idclient) {
        this.idclient = idclient;
    }

//    public Clientes getIdcliente() {
//        return idcliente;
//    }
//
//    public void setIdcliente(Clientes idcliente) {
//        this.idcliente = idcliente;
//    }
//
//    public Medidor getMedidor() {
//        return medidor;
//    }
//
//    public void setMedidor(Medidor medidor) {
//        this.medidor = medidor;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idlocal != null ? idlocal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Local)) {
            return false;
        }
        Local other = (Local) object;
        if ((this.idlocal == null && other.idlocal != null) || (this.idlocal != null && !this.idlocal.equals(other.idlocal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cubicmeter.model.Local[ idlocal=" + idlocal + " ]";
    }
    
}
