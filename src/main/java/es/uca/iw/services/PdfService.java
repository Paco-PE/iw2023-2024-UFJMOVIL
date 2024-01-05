package es.uca.iw.services;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.vaadin.flow.server.StreamResource;

import es.uca.iw.domain.Cliente;

import java.io.ByteArrayInputStream;
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

    public static StreamResource generarFactura(Cliente cliente) {
        ByteArrayOutputStream pdfContent = new ByteArrayOutputStream();
        createPdf("Factura para el cliente:\n\n" +
        "Nombre de usuario: " + cliente.getUsername() + "\n" +
        "Correo electrónico: " + cliente.getEmail() + "\n\n" +
        "Gracias por su negocio.", pdfContent);
        return new StreamResource(cliente.getId() + ".pdf", () -> new ByteArrayInputStream(pdfContent.toByteArray()));
    }
}