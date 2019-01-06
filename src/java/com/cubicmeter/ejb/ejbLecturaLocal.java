/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cubicmeter.ejb;

import com.cubicmeter.model.Clientes;
import com.cubicmeter.model.Lectura;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author nmayen
 */
@Local
public interface ejbLecturaLocal {
    /**
     * Metodo q crea el registro de la lectura.
     *
     * @param path.
     * @param fchIni
     * @param fchFin
     * @param mes 
     * @param anio
     * @return List de Lectura
     * @throws java.lang.Exception error general.
     */
    
    List < Lectura >  crearLectura(String path,
            Date fchIni, Date fchFin) throws Exception;
    
    List mergeEntities(List entidades) throws Exception;
    
    /**
     * Busqueda de clientes
     *
     * @return List de proyectos.
     * @throws Exception error generico.
     */
    List< Clientes> buscarCliente()
            throws Exception;
    
    < T> T find(final Class clazz, final Object key)
            throws Exception;

   List< com.cubicmeter.model.Local> buscarLocalCliente(Integer idcli)
           throws Exception;

}
