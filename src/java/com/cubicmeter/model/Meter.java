/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cubicmeter.model;

import java.util.Date;

/**
 *
 * @author nmayen
 */
public class Meter {
    
    private String number; //0
    private String name; //1
    private Date fecha; //2
    private String meter1; //38
    private String meter2; //45
    private String meter3; //52

    public Meter(String number, String name, Date fecha, String meter1, String meter2, String meter3) {
        this.number = number;
        this.name = name;
        this.fecha = fecha;
        this.meter1 = meter1;
        this.meter2 = meter2;
        this.meter3 = meter3;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getMeter1() {
        return meter1;
    }

    public void setMeter1(String meter1) {
        this.meter1 = meter1;
    }

    public String getMeter2() {
        return meter2;
    }

    public void setMeter2(String meter2) {
        this.meter2 = meter2;
    }

    public String getMeter3() {
        return meter3;
    }

    public void setMeter3(String meter3) {
        this.meter3 = meter3;
    }
    
    @Override public String toString() {
        return "Meter [number=" + number + ", name=" + name + ", fecha=" + fecha + ", meter1=" + meter1 + ", meter2=" + meter2 + ", meter3=" + meter3 + "]"; 
    }
    
}
