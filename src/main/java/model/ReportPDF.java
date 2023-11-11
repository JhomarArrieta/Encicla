/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import database.OperacionesVehiculos;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 *
 * @author Jhomar Arrieta
 */
public class ReportPDF {
    
    OperacionesVehiculos vehicles = new OperacionesVehiculos();
    
    public void crearReporte(String fecha, ArrayList<Bicycle> lista){
        
        
        try{
            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream("report.pdf"));
            doc.open();
            
            //TITULO------------------------------------------------------------
            
            PdfPTable table = new PdfPTable(1); // 1 column.
            table.setWidthPercentage(100.0f);
            PdfPCell cell;
            Font font = new Font(Font.FontFamily.HELVETICA, 24, Font.BOLD, BaseColor.WHITE);
            cell = new PdfPCell(new Phrase("BALANCE", font));
            cell.setBackgroundColor(BaseColor.BLUE);
            cell.setBorderColor(BaseColor.BLUE);
            
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setPaddingTop(18);
            table.addCell(cell);
            
            font = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.WHITE);
            cell = new PdfPCell(new Phrase(fecha, font));
            cell.setBackgroundColor(BaseColor.BLUE);
            cell.setBorderColor(BaseColor.BLUE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setPaddingBottom(18);

            table.addCell(cell);

            doc.add(table);
            
            //------------------------------------------------------------------
            
            // Crear tabla para los datos de las bicicletas
            PdfPTable tableBicicletas = new PdfPTable(3); // 3 columnas
            PdfPCell cell1;

            // Encabezados de la tabla
            cell1 = new PdfPCell(new Phrase("Código"));
            tableBicicletas.addCell(cell1);
            cell1 = new PdfPCell(new Phrase("Acopio Pertenece"));
            tableBicicletas.addCell(cell1);
            cell1 = new PdfPCell(new Phrase("Acopio Encuentra"));
            tableBicicletas.addCell(cell1);
            
            // Obtener datos de las bicicletas
            
            for (Bicycle bicicleta : lista) {
                tableBicicletas.addCell(String.valueOf(bicicleta.getCode()));
                tableBicicletas.addCell(bicicleta.getAcopio());
                tableBicicletas.addCell(bicicleta.getEstado());
            }
            
            //Agregar la tabla al documemto
            doc.add(tableBicicletas);
            
            
            doc.close();
            
        }catch(DocumentException | FileNotFoundException e){
            e.printStackTrace();
        }
    }
}