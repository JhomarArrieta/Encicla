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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 *
 * @author Jhomar Arrieta
 */
public class ReportPDF {
    public static void crearReporte(){
        
        
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
            cell = new PdfPCell(new Phrase("14/02/2023", font));
            cell.setBackgroundColor(BaseColor.BLUE);
            cell.setBorderColor(BaseColor.BLUE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setPaddingBottom(18);

            table.addCell(cell);

            doc.add(table);
            
            //------------------------------------------------------------------
            
            
            doc.close();
            
        }catch(DocumentException | FileNotFoundException e){
            e.printStackTrace();
        }
    }
}