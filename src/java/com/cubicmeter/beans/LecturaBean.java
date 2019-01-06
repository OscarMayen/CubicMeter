/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cubicmeter.beans;

import com.cubicmeter.ejb.ejbLecturaLocal;
import com.cubicmeter.model.Lectura;
import com.cubicmeter.utils.UtilFile;
import com.cubicmeter.utils.Utils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.PrimeFaces;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author nmayen
 */
@Named(value = "lectura")
@ViewScoped
public class LecturaBean implements Serializable {

    @EJB
    private ejbLecturaLocal ejbLectura;
    
    private UploadedFile file;
    private String destination = "/home/nmayen/file/";
    private UtilFile utilFile = new UtilFile();
    private List < Lectura > lstLectura = new ArrayList();
    private Date maxDate = new Date();
    private Date fchIni = new Date();
    private Date fchFin = new Date();
    private boolean upload = false;

    public boolean isUpload() {
        return upload;
    }

    public void setUpload(boolean upload) {
        this.upload = upload;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public List<Lectura> getLstLectura() {
        return lstLectura;
    }

    public void setLstLectura(List<Lectura> lstLectura) {
        this.lstLectura = lstLectura;
    }

    public Date getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(Date maxDate) {
        this.maxDate = maxDate;
    }

    public Date getFchIni() {
        return fchIni;
    }

    public void setFchIni(Date fchIni) {
        this.fchIni = fchIni;
    }

    public Date getFchFin() {
        return fchFin;
    }

    public void setFchFin(Date fchFin) {
        this.fchFin = fchFin;
    }

   

    
    /**
     * Creates a new instance of FileBean
     */
    public LecturaBean() {
    }

    public void itmNuevoListener() {
        DataTable dt = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
                        .findComponent("formLectura:tabView:tblDetalle");
        if (dt != null) {
            //dt.setSortOrder("ascending");  // reset sortOrder
             dt.setValueExpression("sortBy", null);
        }
        lstLectura.clear();
        fchFin = new Date();
        fchIni = new Date();
        upload = false;
    }
    public void itmUploadListener() {
        if (fchIni.compareTo(fchFin) > 0) {
            Utils.alert("Fecha de Fin debe ser mayor que Fecha de Inicio.", FacesMessage.SEVERITY_WARN);
            return;
        }
        DataTable dt = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
                        .findComponent("formLectura:tabView:tblDetalle");
        if (dt != null) {
            //dt.setSortOrder("ascending");  // reset sortOrder
             dt.setValueExpression("sortBy", null);
        }
        
        PrimeFaces.current().executeScript("PF('dlgFile').show();");
    }
    
    public void itmGuardarListener() {
        if (!upload) {
            Utils.alert("Seleccione una archivo", FacesMessage.SEVERITY_WARN);
            return;
        }
        if (fchIni.compareTo(fchFin) > 0) {
            Utils.alert("Fecha de Fin debe ser mayor que Fecha de Inicio.", FacesMessage.SEVERITY_WARN);
            return;
        }
        if (lstLectura.isEmpty()) {
            Utils.alert("No existe detalle de las lecturas.", FacesMessage.SEVERITY_WARN);
            return;
        }
        try {
            ejbLectura.mergeEntities(lstLectura);
            Utils.alert("Lectura guardada.", FacesMessage.SEVERITY_INFO);
        } catch (Exception ex) {
            Logger.getLogger(LecturaBean.class.getName())
                    .log(Level.SEVERE, null, ex);
            Utils.alert("Error al guardar la lectura " + ex.getMessage(), FacesMessage.SEVERITY_WARN);
        }
        
    }

    public void upload() {
        if (file != null) {
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void handleFileUpload(FileUploadEvent event) {
        try {
            utilFile.copyFile(destination + event.getFile().getFileName(), event.getFile().getInputstream());
            PrimeFaces.current().executeScript("PF('dlgFile').hide();");
            Utils.alert("Succesful!!!!", FacesMessage.SEVERITY_INFO);
            lstLectura = ejbLectura.crearLectura(destination + event.getFile().getFileName(),
                    fchIni, fchFin);
            upload = true;
            // copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
        } catch (Exception ex) {
            Logger.getLogger(LecturaBean.class.getName())
                    .log(Level.SEVERE, null, ex);
            Utils.alert("Errro al procesar el archivo " + ex.getMessage(),
                    FacesMessage.SEVERITY_WARN);
        }
    }

//    public void copyFile(String fileName, InputStream in) {
//        try {
//
//            // write the inputStream to a FileOutputStream
//            OutputStream out = new FileOutputStream(new File(destination + fileName));
//
//            int read = 0;
//            byte[] bytes = new byte[1024];
//
//            while ((read = in.read(bytes)) != -1) {
//                out.write(bytes, 0, read);
//            }
//
//            in.close();
//            out.flush();
//            out.close();
//
//            System.out.println("New file created!");
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
//    }

}
