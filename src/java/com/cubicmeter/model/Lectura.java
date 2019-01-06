/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cubicmeter.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mireya Flores
 */
@Entity
@Table(name = "LECTURA")
@XmlRootElement
public class Lectura implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LecturaPK lecturaPK;
    @Column(name = "IDCLIENTE")
    private Integer idcliente;
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "FECHAINIMED")
    @Temporal(TemporalType.DATE)
    private Date fechainimed;
    @Column(name = "FECHAFINMED")
    @Temporal(TemporalType.DATE)
    private Date fechafinmed;
    @Column(name = "MTRCUBICANT")
    private BigDecimal mtrcubicant;
    @Column(name = "MTRCUBIC")
    private BigDecimal mtrcubic;
    @Column(name = "TARIFA")
    private BigDecimal tarifa;
    
    @Transient
    private String nomgrupo;
    @Transient
    private String nomlocal;
    @Transient
    private String nomcliente;

    public Lectura() {
    }

    public Lectura(LecturaPK lecturaPK) {
        this.lecturaPK = lecturaPK;
    }

    public Lectura(int mes, int anio, int idgrupo, int idmedidor) {
        this.lecturaPK = new LecturaPK(mes, anio, idgrupo, idmedidor);
    }

    public LecturaPK getLecturaPK() {
        return lecturaPK;
    }

    public void setLecturaPK(LecturaPK lecturaPK) {
        this.lecturaPK = lecturaPK;
    }

    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechainimed() {
        return fechainimed;
    }

    public void setFechainimed(Date fechainimed) {
        this.fechainimed = fechainimed;
    }

    public Date getFechafinmed() {
        return fechafinmed;
    }

    public void setFechafinmed(Date fechafinmed) {
        this.fechafinmed = fechafinmed;
    }

    public BigDecimal getMtrcubicant() {
        return mtrcubicant;
    }

    public void setMtrcubicant(BigDecimal mtrcubicant) {
        this.mtrcubicant = mtrcubicant;
    }

    public BigDecimal getMtrcubic() {
        return mtrcubic;
    }

    public void setMtrcubic(BigDecimal mtrcubic) {
        this.mtrcubic = mtrcubic;
    }

    public BigDecimal getTarifa() {
        return tarifa;
    }

    public void setTarifa(BigDecimal tarifa) {
        this.tarifa = tarifa;
    }

    public String getNomgrupo() {
        return nomgrupo;
    }

    public void setNomgrupo(String nomgrupo) {
        this.nomgrupo = nomgrupo;
    }

    public String getNomlocal() {
        return nomlocal;
    }

    public void setNomlocal(String nomlocal) {
        this.nomlocal = nomlocal;
    }

    public String getNomcliente() {
        return nomcliente;
    }

    public void setNomcliente(String nomcliente) {
        this.nomcliente = nomcliente;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lecturaPK != null ? lecturaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lectura)) {
            return false;
        }
        Lectura other = (Lectura) object;
        if ((this.lecturaPK == null && other.lecturaPK != null) || (this.lecturaPK != null && !this.lecturaPK.equals(other.lecturaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cubicmeter.model.Lectura[ lecturaPK=" + lecturaPK + " ]";
    }
    
}
