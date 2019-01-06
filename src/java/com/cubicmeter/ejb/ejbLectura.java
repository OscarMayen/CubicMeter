/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cubicmeter.ejb;

import com.cubicmeter.model.Clientes;
import com.cubicmeter.model.Grupos;
import com.cubicmeter.model.Lectura;
import com.cubicmeter.model.LecturaPK;
import com.cubicmeter.model.Local;
import com.cubicmeter.model.Meter;
import com.cubicmeter.utils.UtilDate;
import com.cubicmeter.utils.UtilFile;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author nmayen
 */
@Stateless
public class ejbLectura implements ejbLecturaLocal {

    @PersistenceContext(unitName = "CubicMeterPU")
    private EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    /**
     * Metodo q crea el registro de la lectura.
     *
     * @param path.
     * @return List de Lectura
     * @throws java.lang.Exception error general.
     */
    @Override
    public List< Lectura> crearLectura(String path,
            Date fchIni, Date fchFin) throws Exception {
        UtilFile utilFile = new UtilFile();
        List< Meter> lstMeter = new ArrayList();
        try {
            return meterToLectura(utilFile.fileToMeter(path), fchIni, fchFin);
        } catch (Exception ex) {
            Logger.getLogger(ejbLectura.class.getName())
                    .log(Level.SEVERE, null, ex);
            throw new Exception("Error al crear la lectura " + ex.getMessage());
        }
    }

    public List< Lectura> meterGrupo(Meter meter, Grupos grp,
            Date fchIni, Date fchFin, List< Clientes> lstCli) throws Exception {
        List< Lectura> lstLect = new ArrayList();
        Lectura lect;
        Local local;
        Clientes cliente;
        List< Local> lstLocal = new ArrayList();
        UtilDate utilDate = new UtilDate();
        int mes = -1;
        int anio = -1;

        try {
            mes = utilDate.dateToMonth(fchIni);
            anio = utilDate.dateToYear(fchIni);
            lstLocal = buscarLocal(grp.getIdgrupo());

            lect = new Lectura(mes, anio, grp.getIdgrupo(), 1);
            lect.setFecha(new Date());
            lect.setFechainimed(fchIni);
            lect.setFechafinmed(fchFin);
            System.out.println(meter.getMeter1());
            
            String result = meter.getMeter1()
                        .replaceAll("Cubic Meter", "");
            System.out.println(result);
            

            if (meter.getMeter1() != null && !meter.getMeter1().equals("")) {
                
                lect.setMtrcubic(new BigDecimal(meter.getMeter1()
                        .replaceAll("Cubic Meter", "").trim()));
            } else {
                lect.setMtrcubic(BigDecimal.ZERO);
            }
            local = buscarLocal(lstLocal, lect.getLecturaPK().getIdmedidor());
            if (local != null) {
                lect.setIdcliente(local.getIdclient());
                lect.setNomlocal(local.getNomlocal());

                cliente = buscarCliente(lstCli, local.getIdclient());
                if (cliente != null) {
                    lect.setNomcliente(cliente.getNombre());
                    lect.setTarifa(cliente.getTarifa());
                } else {
                    lect.setNomcliente("-");
                    lect.setTarifa(BigDecimal.ZERO);
                }
            }
            lect.setNomgrupo(grp.getNomgrupo());
            lstLect.add(lect);

            lect = new Lectura(mes, anio, grp.getIdgrupo(), 2);
            lect.setFecha(new Date());
            lect.setFechainimed(fchIni);
            lect.setFechafinmed(fchFin);
            if (meter.getMeter2() != null && !meter.getMeter1().equals("")) {
                lect.setMtrcubic(new BigDecimal(meter.getMeter2()
                        .replaceAll("Cubic Meter", "").trim()));
            } else {
                lect.setMtrcubic(BigDecimal.ZERO);
            }
            local = buscarLocal(lstLocal, lect.getLecturaPK().getIdmedidor());
            if (local != null) {
                lect.setIdcliente(local.getIdclient());
                lect.setNomlocal(local.getNomlocal());

                cliente = buscarCliente(lstCli, local.getIdclient());
                if (cliente != null) {
                    lect.setNomcliente(cliente.getNombre());
                    lect.setTarifa(cliente.getTarifa());
                } else {
                    lect.setNomcliente("-");
                    lect.setTarifa(BigDecimal.ZERO);
                }
            }
            lect.setNomgrupo(grp.getNomgrupo());
            lstLect.add(lect);

            lect = new Lectura(mes, anio, grp.getIdgrupo(), 3);
            lect.setFecha(new Date());
            lect.setFechainimed(fchIni);
            lect.setFechafinmed(fchFin);
            if (meter.getMeter3() != null && !meter.getMeter1().equals("")) {
                lect.setMtrcubic(new BigDecimal(meter.getMeter3()
                        .replaceAll("Cubic Meter", "").trim()));
            } else {
                lect.setMtrcubic(BigDecimal.ZERO);
            }
            local = buscarLocal(lstLocal, lect.getLecturaPK().getIdmedidor());
            if (local != null) {
                lect.setIdcliente(local.getIdclient());
                lect.setNomlocal(local.getNomlocal());

                cliente = buscarCliente(lstCli, local.getIdclient());
                if (cliente != null) {
                    lect.setNomcliente(cliente.getNombre());
                    lect.setTarifa(cliente.getTarifa());
                } else {
                    lect.setNomcliente("-");
                    lect.setTarifa(BigDecimal.ZERO);
                }
            }
            lect.setNomgrupo(grp.getNomgrupo());
            lstLect.add(lect);
            return lstLect;
        } catch (Exception ex) {
            Logger.getLogger(ejbLectura.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Error meterGrupo : " + ex.getMessage());
        }
    }

    public List< Lectura> meterToLectura(List< Meter> lst,
            Date fchIni, Date fchFin) throws Exception {

        List< Grupos> lstGrp = new ArrayList();
        List< Clientes> lstCli = new ArrayList();
        List< Lectura> lstLect = new ArrayList();
        List< Lectura> lstAll = new ArrayList();
        try {
            lstGrp = buscarGrupo();
            if (lstGrp.isEmpty()) {
                throw new Exception("No existe el grupo ");
            }
            lstCli = buscarCliente();
            if (lstCli.isEmpty()) {
                throw new Exception("No existe clientes activos");
            }
            Grupos grp = new Grupos();
            for (Meter meter : lst) {
                System.out.println(meter);
                grp = buscarGrupo(lstGrp, meter.getName());
                if (grp == null) {
                    throw new Exception("No existe e; grupo " + meter.getName());
                }
                lstLect = meterGrupo(meter, grp, fchIni, fchFin, lstCli);
                lstAll.addAll(lstLect);
            }
            return lstAll;
        } catch (Exception ex) {
            Logger.getLogger(ejbLectura.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Error meterToLectura: " + ex.getMessage());
        }
    }

    /**
     * Busqueda de proyecos vigentes para un cliente especifico
     *
     * @param codcia codigo de compania.
     * @param codcli codigo de cliente.
     * @return List de proyectos.
     * @throws Exception error generico.
     */
    public List< Grupos> buscarGrupo()
            throws Exception {
        StringBuilder jpql = new StringBuilder();
        try {
            jpql.append("SELECT * FROM GRUPOS A ");
            Query q = em.createNativeQuery(jpql.toString(), Grupos.class);
            return q.getResultList();
        } catch (NoResultException no) {
            Logger.getLogger(ejbLectura.class.getName()).log(Level.INFO,
                    "No se encontraron los resultados deseados",
                    no.getMessage());
            return new ArrayList();
        } catch (Exception e) {
            Logger.getLogger(ejbLectura.class.getName()).log(Level.SEVERE,
                    "Erro en buscarGrupo : " + e.getMessage());
            throw new Exception("Error al buscarGrupo" + e.getMessage());
        }
    }

    public Grupos buscarGrupo(List< Grupos> lst, String name) {
        return lst.stream()
                .filter(gr -> gr.getNomgrupo().equals(name))
                .findAny().orElse(null);
    }

    /**
     * Busqueda de proyecos vigentes para un cliente especifico
     *
     * @param codcia codigo de compania.
     * @param codcli codigo de cliente.
     * @return List de proyectos.
     * @throws Exception error generico.
     */
    public List< Local> buscarLocal(Integer idgrupo)
            throws Exception {
        StringBuilder jpql = new StringBuilder();
        try {
            jpql.append("SELECT * FROM LOCAL");
            jpql.append(" WHERE IDGRUPO = ?1");

            Query q = em.createNativeQuery(jpql.toString(), Local.class);
            q.setParameter(1, idgrupo);
            return q.getResultList();
        } catch (NoResultException no) {
            Logger.getLogger(ejbLectura.class.getName()).log(Level.INFO,
                    "No se encontraron los resultados deseados",
                    no.getMessage());
            return new ArrayList();
        } catch (Exception e) {
            Logger.getLogger(ejbLectura.class.getName()).log(Level.SEVERE,
                    "Erro en buscarLocal : " + e.getMessage());
            throw new Exception("Error al buscarLocal" + e.getMessage());
        }
    }

    public Local buscarLocal(List< Local> lst, int medidor) {
        return lst.stream()
                .filter(lc -> lc.getIdmedidor().equals(medidor))
                .findAny().orElse(null);
    }

    /**
     * Busqueda de clientes
     *
     * @return List de proyectos.
     * @throws Exception error generico.
     */
    public List< Clientes> buscarCliente()
            throws Exception {
        StringBuilder jpql = new StringBuilder();
        try {
            jpql.append("SELECT * FROM CLIENTES WHERE ESTADO = 1");
            Query q = em.createNativeQuery(jpql.toString(), Clientes.class);
            return q.getResultList();
        } catch (NoResultException no) {
            Logger.getLogger(ejbLectura.class.getName()).log(Level.INFO,
                    "No se encontraron los resultados deseados",
                    no.getMessage());
            return new ArrayList();
        } catch (Exception e) {
            Logger.getLogger(ejbLectura.class.getName()).log(Level.SEVERE,
                    "Erro en buscarCliente : " + e.getMessage());
            throw new Exception("Error al buscarLocal" + e.getMessage());
        }
    }

    public Clientes buscarCliente(List< Clientes> lst, Integer idcliente) {
        return lst.stream()
                .filter(lc -> lc.getIdcliente() == idcliente)
                .findAny().orElse(null);
    }

    public List mergeEntities(List entidades) throws Exception {
        for (Object entidad : entidades) {
            em.merge(entidad);
        }
        return entidades;
    }

    public final < T> T find(final Class clazz, final Object key)
            throws Exception {
        if (clazz == null || key == null) {
            return null;
        }
        return (T) em.find(clazz, key);
    }
    
    
    public List< Local> buscarLocalCliente(Integer idcli) throws Exception {
        StringBuilder jpql = new StringBuilder();
        try {
            jpql.append("SELECT * FROM LOCAL");
            jpql.append(" WHERE IDCLIENTE = ?1");

            Query q = em.createNativeQuery(jpql.toString(), Local.class);
            q.setParameter(1, idcli);
            return q.getResultList();
        } catch (NoResultException no) {
            Logger.getLogger(ejbLectura.class.getName()).log(Level.INFO,
                    "No se encontraron los resultados deseados",
                    no.getMessage());
            return new ArrayList();
        } catch (Exception e) {
            Logger.getLogger(ejbLectura.class.getName()).log(Level.SEVERE,
                    "Erro en buscarLocalCliente : {0}", e.getMessage());
            throw new Exception("Error al buscarLocalCliente" + e.getMessage());
        }
    }

}
