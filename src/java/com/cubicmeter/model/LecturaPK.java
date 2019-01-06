/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cubicmeter.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Mireya Flores
 */
@Embeddable
public class LecturaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "MES")
    private int mes;
    @Basic(optional = false)
    @Column(name = "ANIO")
    private int anio;
    @Basic(optional = false)
    @Column(name = "IDGRUPO")
    private int idgrupo;
    @Basic(optional = false)
    @Column(name = "IDMEDIDOR")
    private int idmedidor;

    public LecturaPK() {
    }

    public LecturaPK(int mes, int anio, int idgrupo, int idmedidor) {
        this.mes = mes;
        this.anio = anio;
        this.idgrupo = idgrupo;
        this.idmedidor = idmedidor;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getIdgrupo() {
        return idgrupo;
    }

    public void setIdgrupo(int idgrupo) {
        this.idgrupo = idgrupo;
    }

    public int getIdmedidor() {
        return idmedidor;
    }

    public void setIdmedidor(int idmedidor) {
        this.idmedidor = idmedidor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) mes;
        hash += (int) anio;
        hash += (int) idgrupo;
        hash += (int) idmedidor;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LecturaPK)) {
            return false;
        }
        LecturaPK other = (LecturaPK) object;
        if (this.mes != other.mes) {
            return false;
        }
        if (this.anio != other.anio) {
            return false;
        }
        if (this.idgrupo != other.idgrupo) {
            return false;
        }
        if (this.idmedidor != other.idmedidor) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cubicmeter.model.LecturaPK[ mes=" + mes + ", anio=" + anio + ", idgrupo=" + idgrupo + ", idmedidor=" + idmedidor + " ]";
    }
    
}
