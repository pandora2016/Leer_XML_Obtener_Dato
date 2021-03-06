package com.xlsx;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;
import java.awt.Component;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GeneraXlsx {

    public static void main(String[] args) throws IOException {
        generaXlsx();
    }

    public static void generaXlsx() throws IOException {
        //--------------------------------- Obtener Ruta Entrada-------------------------
        String filename = File.separator + "tmp";
        JFileChooser fc = new JFileChooser(new File(filename));
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        Component frame = null;
        fc.showOpenDialog(frame);
        File selFile = fc.getSelectedFile();
        System.out.println(" Ruta de entrada " + selFile);

        //--------------------------------- Obtener Ruta Salida -----------------
        //nombre del archivo de Excel
        String nombreArchivo = selFile + "/CargaFacturas.xlsx";

        String nombreHoja1 = "Hoja1";//nombre de la hoja1

        XSSFWorkbook libroTrabajo = new XSSFWorkbook();
        XSSFSheet hoja1 = libroTrabajo.createSheet(nombreHoja1);

        //------------------------- LEER DIRECTORIO-------------------------------------------
        File folder = new File(selFile + "");//"D:\\FacturaElectronica"
        File[] listOfFiles = folder.listFiles();

        //int  contarXML=0;
        for (int i = 0; i < listOfFiles.length; i++) {
           // System.out.println("Otros--> " + listOfFiles[i].getName()); // otros archivos en la carpeta
            if ((listOfFiles[i].getName()).toLowerCase().endsWith(".xml")) {
                //  contarXML++;
                System.out.println("Archivos XML --> " + selFile + "\\" + listOfFiles[i].getName());

                // Leer Contenido del archivo XML ----------------------------------
                FileReader fr = new FileReader(selFile + "\\" + listOfFiles[i].getName());
                BufferedReader br = new BufferedReader(fr);
                String cadena;
                while ((cadena = br.readLine()) != null) {
                //campos = cadena.split("\t");
if (cadena.startsWith("<cbc:Description>")) {
                        System.out.println("Descripcion-->" + cadena);
                    }
                    
                }
        // Leer Contenido del archivo XML (FIN)------------------------------

                XSSFRow row = hoja1.createRow(i);
                XSSFCell cell2 = row.createCell(0);

                cell2.setCellValue(listOfFiles[i].getName());

                XSSFCell cell = row.createCell(1);
                //selFile
                // cell.setCellValue(selFile +"\\"+ listOfFiles[i].getName());
                cell.setCellValue("D:/Factura_Electronica/Facturas/XML/noviembre/" + listOfFiles[i].getName());

            } else if (listOfFiles[i].isDirectory()) {
                //  System.out.println("Directory " + listOfFiles[i].getName());
            }
        }
        //-------------------------FIN LEER DIRECTORIO------------------------------------------

        FileOutputStream fileOut = new FileOutputStream(nombreArchivo);

        //escribir este libro en un OutputStream.
        libroTrabajo.write(fileOut);
        fileOut.flush();
        fileOut.close();
    }
}
