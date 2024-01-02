package es.uca.iw.views;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;

import es.uca.iw.MainLayout;
import es.uca.iw.domain.Cliente;
//import es.uca.iw.services.ClienteService;
import es.uca.iw.fakers.ClienteFakeService;
import es.uca.iw.services.PdfService;
import jakarta.annotation.security.RolesAllowed;

@PageTitle("Finanzas")
@Route(value = "/finanzas", layout = MainLayout.class)
@RolesAllowed({"EMPLEADO_FINANCIERO", "ADMINISTRADOR"})
public class FinanzasView extends VerticalLayout {
    private ClienteFakeService clienteService;

    public FinanzasView(){
        this.clienteService = new ClienteFakeService();

        H2 title = new H2("Clientes");
        add(title);

        Grid<Cliente> grid = new Grid<>(Cliente.class);
            grid.setColumns("username", "email");
            grid.setItems(clienteService.findAll());

            grid.addComponentColumn(cliente -> {
                StreamResource pdfResource = PdfService.generarFactura(cliente);
                com.vaadin.flow.component.html.Anchor downloadLink = new com.vaadin.flow.component.html.Anchor(pdfResource, "Generar Factura");
                downloadLink.setTarget("_blank");
                return downloadLink;
            }).setHeader("Factura");

            setSizeFull();
            grid.setSizeFull();

        add(grid);
    }
}