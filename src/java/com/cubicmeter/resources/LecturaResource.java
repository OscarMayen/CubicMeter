/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cubicmeter.resources;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author nmayen
 */
@Path("lectura")
public class LecturaResource {

    @Context
    private UriInfo context;
    
    private ExecutorService executorService = Executors.newCachedThreadPool();

    /**
     * Creates a new instance of LecturaResource
     */
    public LecturaResource() {
    }

    /**
     * Retrieves representation of an instance of com.cubicmeter.beans.LecturaResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

     @POST
    @Path("detalle/compania/{codcia}/tipodocu/{tipodocu}/numero/{nodocu}/corr/{corln}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void asycPOST(@Suspended final AsyncResponse ar) {

        executorService.submit(new Runnable() {
            public void run() {
                Response response = Response.ok("OK").build();
                System.out.println("inicia!!!!!!!!!!!!!");
                try {
                    Thread.sleep(10000);
                    System.out.println("stop");
                    ar.resume(response);
                } catch (InterruptedException ex) {
                     System.out.println("Error @@@@@@@@");
                    Logger.getLogger(LecturaResource.class.getName())
                            .log(Level.SEVERE, null, ex);
                    ar.resume(ex);
                } catch (Exception ex) {
                    System.out.println("Error ########### ");
                    
                    
                    Logger.getLogger(LecturaResource.class.getName())
                            .log(Level.SEVERE, null, ex);
                    
//                    response = Response.status(Response.Status.INTERNAL_SERVER_ERROR)
//                            .entity(new ResponseWs(500, ex.getMessage(),"1",null))
//                            .type(MediaType.APPLICATION_JSON)
//                            .build();
//                     ar.resume(clWsAsync.crearResponseWs(
//                             Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(),
//                             ex.getMessage(), "1", null));
                        ar.resume(ex);
                        
                }
            }
        });

    }
}
