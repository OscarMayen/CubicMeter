/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cubicmeter.reportes;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nmayen
 */
@WebServlet(name = "Printer", urlPatterns = {"/Printer"})
public class Printer extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");
        //  PrintWriter out = response.getWriter();
        PDF pdf = new PDF();
        byte[] reporteByte = null;

        String rutarpt = (String) request.getSession().getAttribute("rutarpt");
        Map param = (Map) request.getSession().getAttribute("param");
        String dataSource = (String) request.getSession().getAttribute("dataSource");

        if (dataSource == null || dataSource.equals("")) {
            PrintWriter out = response.getWriter();
            out.println("<h2>El parametro del Data Source no es valida</h2>");
            out.close();
            return;
        }

        if (rutarpt == null || rutarpt.equals("")) {
            PrintWriter out = response.getWriter();
            out.println("<h2>El parametro rutarpt del reporte no es valida </h2>");
            out.close();
            return;
        }

//        if (parameters == null){
//            PrintWriter out = response.getWriter();
//            out.println("<h2>Los parametro del reporte no son validos </h2>");
//            out.close();
//            return;
//        }
        try {

            reporteByte = pdf.crearPDF(dataSource, rutarpt, param);
            response.setContentType("application/pdf");
            response.setContentLength(reporteByte.length);
            response.getOutputStream().write(reporteByte);
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (Exception ex) {
            Logger.getLogger(Printer.class.getName())
                    .log(Level.SEVERE, null, ex);
            PrintWriter out = response.getWriter();
            out.println("<H2> Error: " + ex.getMessage() + "</H2>");
            out.close();
        } finally {
            request.getSession()
                    .removeAttribute("parameters");
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
