package es.uca.iw.views;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.stream.Collectors;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;

import es.uca.iw.MainLayout;
import es.uca.iw.domain.Cliente;
import es.uca.iw.domain.User;
import es.uca.iw.fakers.ClienteFakeService;
import es.uca.iw.services.ClienteService;
import es.uca.iw.services.PdfService;
import es.uca.iw.services.UserDetailsServiceImpl;
import jakarta.annotation.security.RolesAllowed;

@PageTitle("Finanzas")
@Route(value = "/finanzas", layout = MainLayout.class)
@RolesAllowed({"EMPLEADO_FINANCIERO", "ADMINISTRADOR"})
public class FinanzasView extends VerticalLayout {
    private ClienteService clienteService;
    private UserDetailsServiceImpl userService;
    private final Grid<User> grid = new Grid<>(User.class);

    public FinanzasView(UserDetailsServiceImpl userService){
        this.userService = userService;
         H1 welcomeText = new H1("UFJMOVIL");
        H2 welcomeText2 = new H2("Bienvenido, departamento de ventas y marketing");

        VerticalLayout layoutcolumn = new VerticalLayout();
        layoutcolumn.setWidthFull();
        layoutcolumn.setAlignSelf(FlexComponent.Alignment.CENTER,welcomeText);
        layoutcolumn.setAlignSelf(FlexComponent.Alignment.CENTER,welcomeText2);

        H4 title = new H4("Lista de clientes");
        add(title);

        grid.setItems(userService.loadAll());
        grid.removeAllColumns();
        grid.addColumn(User::getUsername).setHeader("Usuario");
        grid.addColumn(User::getEmail).setHeader("Email");
        grid.addColumn(User::getRoles).setHeader("Rol");

        grid.addComponentColumn(cliente -> {
            StreamResource pdfResource = PdfService.generarFactura(cliente);
            com.vaadin.flow.component.html.Anchor downloadLink = new com.vaadin.flow.component.html.Anchor(pdfResource, "Generar Factura");
            downloadLink.setTarget("_blank");
            return downloadLink;
        }).setHeader("Factura");

        setSizeFull();
        grid.setSizeFull();

        add(welcomeText);
        add(welcomeText2);
        add(title);
        add(grid);
    }
}