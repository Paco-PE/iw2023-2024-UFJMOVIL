package es.uca.iw.services;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.vaadin.flow.server.StreamResource;

import es.uca.iw.domain.Cliente;
import es.uca.iw.domain.Contrato;
import es.uca.iw.domain.Fibra;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PdfService {
    private static final Logger logger = LoggerFactory.getLogger(PdfService.class);
    public static void createPdf(String content, ByteArrayOutputStream outputStream) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, outputStream);
            document.open();
            document.add(new Paragraph(content));
            document.close();
        } catch (DocumentException e) {
            logger.error("Error creating PDF", e);
        }
    }

    public static StreamResource generarFactura(Cliente cliente) {
        ByteArrayOutputStream pdfContent = new ByteArrayOutputStream();
        float costeMensualTotal = 0;
        StringBuilder factura = new StringBuilder("FACTURA\n\n" +
            "Nombre de usuario: " + cliente.getUsername() + "\n" +
            "Correo electrónico: " + cliente.getEmail() + "\n\n" +
            "----------------------------------------------------------------------------------------------------------------------------------\n\n");
    
        if (cliente.getContratos() != null) {
            for (Contrato contrato : cliente.getContratos()) {
                factura.append(" - Servicio: " + contrato.getServicio().getName() + "\n");
                if(!(contrato.getServicio() instanceof Fibra)) factura.append("   Número de teléfono: " + contrato.getNumeroTelefono() + "\n");
                factura.append("   Fecha de inicio: " + contrato.getFechaInicio().toString() + "\n" +
                    "   Precio: " + contrato.getServicio().getPrecio() + " € \n\n");
                costeMensualTotal += contrato.getServicio().getPrecio();
            }
        }
    
        factura.append( "----------------------------------------------------------------------------------------------------------------------------------\n\n" +
            "Coste mensual: "+ costeMensualTotal +" € \n\n" + 
            "Gracias por su confianza.");
        createPdf(factura.toString(), pdfContent);
        return new StreamResource(cliente.getUsername() + "Factura" + ".pdf", () -> new ByteArrayInputStream(pdfContent.toByteArray()));
    }
}