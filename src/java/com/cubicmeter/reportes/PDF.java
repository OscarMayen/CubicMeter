/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cubicmeter.reportes;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.JRPdfExporter;

/**
 *
 * @author nmayen
 */
public class PDF {
     
    public byte[] crearPDF(String datSource, String url,
            Map parameters) throws IOException, NamingException,
            SQLException, Exception {
        Connection connection = null;
        String archJasper;
        URI uriObj = null;
        byte[] reporteByte = null;
        try {
            InitialContext initialContext = new InitialContext();
            DataSource ds = (DataSource) initialContext.lookup(datSource);
            connection = ds.getConnection();
            uriObj = getClass().getResource(url).toURI();
            File reporte = new File(uriObj);
            archJasper = reporte.getAbsolutePath();
            JasperPrint impresion = null;
            JRExporter exporter = new JRPdfExporter();
            ByteArrayOutputStream reportePDF = new ByteArrayOutputStream();
            reporteByte = JasperRunManager.runReportToPdf(reporte.getPath(),
                    parameters, connection);
            impresion =
               JasperFillManager.fillReport(archJasper, parameters, connection);
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, impresion);
           exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, reportePDF);
           connection.close();
        } catch (NamingException ne) {
            Logger.getLogger(PDF.class.getName()).log(Level.SEVERE,
                    null, ne);
            throw new Exception("Error al accededer al DataSource "
                    + ne.getMessage());
        } catch (SQLException sqle) {
            Logger.getLogger(PDF.class.getName()).log(Level.SEVERE,
                    null, sqle);
            throw new Exception("SQL Exception " + sqle.getMessage());
        } catch (JRException ex) {
            Logger.getLogger(PDF.class.getName())
                    .log(Level.SEVERE, null, ex);
            throw new Exception("JRException " + ex.getMessage());
        } catch (Exception e) {
            Logger.getLogger(PDF.class.getName())
                    .log(Level.SEVERE, null, e);
            throw new Exception("Exception " + e.getMessage());
        } finally {
            connection.close();
        }
        return reporteByte;
    }
}
