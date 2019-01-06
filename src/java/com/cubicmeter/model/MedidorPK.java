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
public class MedidorPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "IDGRUPO")
    private int idgrupo;
    @Basic(optional = false)
    @Column(name = "IDMEDIDOR")
    private int idmedidor;

    public MedidorPK() {
    }

    public MedidorPK(int idgrupo, int idmedidor) {
        this.idgrupo = idgrupo;
        this.idmedidor = idmedidor;
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
        hash += (int) idgrupo;
        hash += (int) idmedidor;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MedidorPK)) {
            return false;
        }
        MedidorPK other = (MedidorPK) object;
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
        return "com.cubicmeter.model.MedidorPK[ idgrupo=" + idgrupo + ", idmedidor=" + idmedidor + " ]";
    }
    
}
