/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cubicmeter.beans;

import com.cubicmeter.ejb.ejbLecturaLocal;
import com.cubicmeter.model.Clientes;
import com.cubicmeter.model.Local;
import com.cubicmeter.utils.UtilDate;
import com.cubicmeter.utils.Utils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.PrimeFaces;
import org.primefaces.context.RequestContext;

/**
 *
 * @author nmayen
 */
@Named(value = "rptFactura")
@ViewScoped
public class rptFacturaBean implements Serializable {

    @EJB
    private ejbLecturaLocal ejbLectura;

    private Date maxDate = new Date();
    private Date fecha = new Date();
    private ArrayList alstCliente = new ArrayList();
    private ArrayList alstLocal = new ArrayList();
    private Integer idcliente;
    private Integer idlocal;
    private List < Local > lstLocal = new ArrayList();
    private UtilDate utilDate =new UtilDate();

    public Date getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(Date maxDate) {
        this.maxDate = maxDate;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ArrayList getAlstCliente() {
        return alstCliente;
    }

    public void setAlstCliente(ArrayList alstCliente) {
        this.alstCliente = alstCliente;
    }

    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public Integer getIdlocal() {
        return idlocal;
    }

    public void setIdlocal(Integer idlocal) {
        this.idlocal = idlocal;
    }

    public ArrayList getAlstLocal() {
        return alstLocal;
    }

    public void setAlstLocal(ArrayList alstLocal) {
        this.alstLocal = alstLocal;
    }
    

    /**
     * Creates a new instance of rptFacturaBean
     */
    public rptFacturaBean() {
    }

    @PostConstruct
    public void init() {
        try {
            completeCombos();
        } catch (Exception ex) {
            Logger.getLogger(rptFacturaBean.class.getName())
                    .log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }

    }

    public void completeCombos() throws Exception {
        List< Clientes> lstCli;
        lstCli = ejbLectura.buscarCliente();
        lstCli.stream().forEach((c) -> {
            alstCliente.add(new SelectItem(c.getIdcliente(),
                    c.getNombre()));
        });
        alstLocal.add(new SelectItem(-1, "Locales"));
    }
    
    public void onCmbClienteChange() {
        try {
            alstLocal.clear();
            alstLocal.add(new SelectItem(-1, "Locales"));
            lstLocal = ejbLectura.buscarLocalCliente(idcliente);
            lstLocal.stream().forEach((c) -> {
            alstLocal.add(new SelectItem(c.getIdlocal(), c.getNomlocal()));
            idlocal = -1;
        });
            
        } catch (Exception ex) {
            Logger.getLogger(rptFacturaBean.class.getName())
                    .log(Level.SEVERE, null, ex);
            Utils.alert("Error al consultar local " + ex.getMessage(),
                    FacesMessage.SEVERITY_INFO);
        }
        
    }

    public void itmImprimirListener() {
        try {
            Local local =  ejbLectura.find(Local.class, idlocal);
            if (local == null) {
                Utils.alert("No existe el Local seleccioando ",
                    FacesMessage.SEVERITY_WARN);
                return;
            }
            if (local.getIdgrupo() == null || local.getIdmedidor() == null) {
                Utils.alert("El cliente no tiene asignado Medidor",
                        FacesMessage.SEVERITY_WARN);
                return;
            }
            
            String rutarpt = "/com/cubicmeter/jasper/rptCubicMeter.jasper";

            FacesContext fc = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) fc
                    .getExternalContext().getRequest();

            String url = request.getContextPath() + "/Printer";

            request.getSession().setAttribute("rutarpt", rutarpt);
            request.getSession().setAttribute("url", url);
            request.getSession().setAttribute("dataSource", "java:app/jdbc/cubicMeter");

            
            
            Map param = new HashMap();
            param.put("MES", utilDate.dateToMonth(fecha));
            param.put("ANIO", utilDate.dateToYear(fecha));
            param.put("GRUPO", local.getIdgrupo() );
            param.put("MEDIDOR", local.getIdmedidor());
            
            Date fchant = utilDate.addMonth(fecha, -1);
            System.out.println(fecha);
            System.out.println(fchant);
            
            param.put("MESANT", utilDate.dateToMonth(fchant));
            param.put("ANIOANT", utilDate.dateToYear(fchant));
            
            

            request.getSession().setAttribute("param", param);
            PrimeFaces.current().executeScript(
                    "window.open('" + url
                    + "','Rpt','location=0,menubar=0,resizable=1,"
                    + "status=0,toolbar=0');");

        } catch (Exception ex) {
            Logger.getLogger(rptFacturaBean.class.getName())
                    .log(Level.SEVERE, null, ex);
            Utils.alert("Error al generar el reporte. " + ex.getMessage(),
                    FacesMessage.SEVERITY_WARN);
        }
    }
}
