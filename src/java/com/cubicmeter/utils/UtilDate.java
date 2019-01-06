/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cubicmeter.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author nmayen
 */
public class UtilDate {

    public final int dateToMonth(Date fecha) {
        int mes = -1;
        if (fecha == null) {
            return mes;
        }
        Calendar c = new GregorianCalendar();
        c.setTime(fecha);
        mes = c.get(Calendar.MONTH) + 1;
        return mes;
    }

    public final int dateToYear(final Date fecha) {
        int anio = -1;
        if (fecha == null) {
            return anio;
        }
        Calendar c = new GregorianCalendar();
        c.setTime(fecha);
        anio = c.get(Calendar.YEAR);
        return anio;
    }

    public final Date addMonth(final Date fecha, final int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.add(Calendar.MONTH, n);
        return cal.getTime();
    }

}
