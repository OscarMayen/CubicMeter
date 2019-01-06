/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cubicmeter.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author nmayen
 */
public class Utils {
    
      public static void alert(String msg, FacesMessage.Severity tipomsg) {
        if (tipomsg.equals(FacesMessage.SEVERITY_INFO)) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", msg));
        } else if (tipomsg.equals(FacesMessage.SEVERITY_WARN)) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", msg));
        } else if (tipomsg.equals(FacesMessage.SEVERITY_ERROR)) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", msg));
        } else if (tipomsg.equals(FacesMessage.SEVERITY_FATAL)) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", msg));
        }
    }
    
}
