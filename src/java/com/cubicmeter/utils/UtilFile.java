/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cubicmeter.utils;

import com.cubicmeter.model.Meter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.groupingBy;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author nmayen
 */
public class UtilFile {

    public static Function<String, Meter> mapToMeter = (line) -> {
        String[] p = line.replace("\"", "").split(",");
        System.out.println(p[0]);
        System.out.println(p[0].equals("LOSCASTANOSG1"));
        if (p[1].equals("LOSCASTANOSG1")) {
            return new Meter(p[0], p[1], strToDate(p[2].replace("=", "")), p[37], p[44], p[51]);
        } else {
            return new Meter(p[0], p[1], strToDate(p[2].replace("=", "")), p[38], p[45], p[52]);
        }
    };

    public static Date strToDate(String strFecha) {
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fecha = null;
        try {
            fecha = formatoDelTexto.parse(strFecha);
            return fecha;
        } catch (ParseException ex) {
            Logger.getLogger(UtilFile.class.getName())
                    .log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List< Meter> fileToMeter(String path) throws Exception {
        // TODO code application logic here
        //String path = "/Users/nmayen/AllMeters.csv";
        InputStream is;

        try {
            is = new FileInputStream(new File(path));

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            List<Meter> lstmeter = br.lines().skip(1).map(mapToMeter)
                    .collect(Collectors.toList());

            Map<String, List<Meter>> lstmap = lstmeter.stream().collect(
                    groupingBy((Meter v) -> v.getName()));

            List<Meter> lstresult = new ArrayList();
            for (Map.Entry<String, List<Meter>> e : lstmap.entrySet()) {
                Meter max = e.getValue().stream()
                        .max(Comparator.comparing(Meter::getFecha)).get();
                lstresult.add(max);
            }
            return lstresult;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UtilFile.class.getName())
                    .log(Level.SEVERE, null, ex);
            throw new Exception("Error al procesar el archivo " + ex.getMessage());
        }

    }

    public static void subirFichero(UploadedFile uploadFile,
            String path) throws IOException {
        File file = new File(path);
        FileOutputStream fos = new FileOutputStream(file);
        IOUtils.copy(uploadFile.getInputstream(), fos);
    }

    public void copyFile(String path, InputStream in) throws Exception {

        // write the inputStream to a FileOutputStream
        OutputStream out = null;
        try {
            out = new FileOutputStream(new File(path));
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            in.close();
            out.flush();
            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UtilFile.class.getName())
                    .log(Level.SEVERE, null, ex);
            throw new Exception("Error al leer el archivo " + ex.getMessage());
        } catch (IOException e) {
            Logger.getLogger(UtilFile.class.getName())
                    .log(Level.SEVERE, null, e);
             throw new Exception("Error al escribir el archivo " + e.getMessage());
        }
    }

}
