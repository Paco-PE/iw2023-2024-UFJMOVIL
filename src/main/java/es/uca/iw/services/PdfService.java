package es.uca.iw.services;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class PdfService {
    public static void createPdf(String content, ByteArrayOutputStream outputStream) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, outputStream);
            document.open();
            document.add(new Paragraph(content));
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}