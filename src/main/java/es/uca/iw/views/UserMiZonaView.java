package es.uca.iw.views;

import es.uca.iw.MainLayout;
import es.uca.iw.domain.Cliente;
import es.uca.iw.domain.Fibra;
import es.uca.iw.domain.Movil;
import es.uca.iw.domain.Telefonia;
import es.uca.iw.domain.User;
import es.uca.iw.security.AuthenticatedUser;
import es.uca.iw.services.ClienteService;
import es.uca.iw.services.FibraService;
import es.uca.iw.services.MovilService;
import es.uca.iw.services.PdfService;
import es.uca.iw.services.TelefoniaService;

import java.util.Optional;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import jakarta.annotation.security.RolesAllowed;

@PageTitle("Mi Zona")
@Route(value = "mi-zona", layout = MainLayout.class)
@RolesAllowed("CLIENTE")
@Uses(Icon.class)
public class UserMiZonaView extends Composite<VerticalLayout> {

    private AuthenticatedUser authenticatedUser;
    private ClienteService clienteService;
    private FibraService fibraService;
    private MovilService movilService;
    private TelefoniaService telefoniaService;
    private Button ContactaButton = new Button("Contacta con nosotros");

    public UserMiZonaView(AuthenticatedUser authenticatedUser, ClienteService clienteService, FibraService fibraService, MovilService movilService, TelefoniaService telefoniaService) {
        this.authenticatedUser = authenticatedUser;
        this.clienteService = clienteService;
        this.fibraService = fibraService;
        this.movilService = movilService;
        this.telefoniaService = telefoniaService;

        Grid<Fibra> gridFibra = new Grid<>();
        gridFibra.addColumn(Fibra::getName).setHeader("Nombre");
        gridFibra.addColumn(Fibra::getVelocidadContratadaMb).setHeader("Velocidad");
        gridFibra.addColumn(Fibra::getPrecio).setHeader("Precio");
        gridFibra.setItems(fibraService.findAll());
        gridFibra.setWidthFull();
        
        Grid<Telefonia> gridFijo = new Grid<>();
        gridFijo.addColumn(Telefonia::getName).setHeader("Nombre");
        gridFijo.addColumn(Telefonia::getMinutosMaximos).setHeader("Minutos máximos");
        gridFijo.addColumn(Telefonia::getLlamadasMaximas).setHeader("Llamadas máximas");
        gridFijo.addColumn(Telefonia::getPrecio).setHeader("Precio");
        gridFijo.setItems(telefoniaService.findAllFijo());
        gridFijo.setWidthFull();
        
        Grid<Movil> gridMovil = new Grid<>();
        gridMovil.addColumn(Movil::getName).setHeader("Nombre");
        gridMovil.addColumn(Movil::getMinutosMaximos).setHeader("Minutos máximos");
        gridMovil.addColumn(Movil::getLlamadasMaximas).setHeader("Llamadas máximas");
        gridMovil.addColumn(Movil::getDatosMaximosGB).setHeader("Datos máximos");
        gridMovil.addColumn(Movil::getRoaming).setHeader("Llamadas máximas");
        gridMovil.addColumn(Movil::getPrecio).setHeader("Precio");
        gridMovil.setItems(movilService.findAll());
        gridMovil.setWidthFull();

        H2 txtServiciosOfertados = new H2();
        VerticalLayout layoutColumn = new VerticalLayout();
        Button buttonPrimary = new Button();
        Hr hr = new Hr();
        H2 h25 = new H2();
        Hr hr2 = new Hr();
        H2 h26 = new H2();

        H3 txtFibra = new H3();
        txtFibra.setText("Fibra");

        H3 txtFijo = new H3();
        txtFijo.setText("Fijo");

        H3 txtMovil = new H3();
        txtMovil.setText("Móvil");

        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        txtServiciosOfertados.setText("Servicios ofertados");
        txtServiciosOfertados.setWidth("max-content");
        layoutColumn.setHeightFull();
        layoutColumn.setWidthFull();
        layoutColumn.setJustifyContentMode(JustifyContentMode.START);
        layoutColumn.setAlignItems(Alignment.START);
        getContent().setAlignSelf(FlexComponent.Alignment.CENTER, ContactaButton);

        buttonPrimary.setText("Actualizar contrato");
        buttonPrimary.setEnabled(false); // Deshabilitar el botón al principio
        buttonPrimary.getStyle().set("background-color", "grey"); // Cambiar el color a gris
        getContent().setAlignSelf(FlexComponent.Alignment.CENTER, buttonPrimary);
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonPrimary.addClickListener(event -> {
            Notification.show("Su contrato se ha actualizado");
        });
        h25.setText("Consumo de datos 5G");
        h25.setWidth("max-content");
        h26.setText("Desglose de llamadas");
        h26.setWidth("max-content");

        getContent().add(txtServiciosOfertados);
        getContent().add(layoutColumn);
        layoutColumn.add(txtFibra);
        layoutColumn.add(gridFibra);
        layoutColumn.add(txtFijo);
        layoutColumn.add(gridFijo);
        layoutColumn.add(txtMovil);
        layoutColumn.add(gridMovil);
        getContent().add(ContactaButton);
        getContent().add(buttonPrimary);

        Optional<User> maybeUser = this.authenticatedUser.get();
        if (maybeUser.isPresent()) {
            User user = maybeUser.get();
            Cliente cliente = clienteService.loadClienteById(user.getId());
            StreamResource pdfResource = PdfService.generarFactura(cliente);
            com.vaadin.flow.component.html.Anchor downloadLink = new com.vaadin.flow.component.html.Anchor(pdfResource, "Generar Factura");
            downloadLink.setTarget("_blank");
            getContent().setAlignSelf(FlexComponent.Alignment.CENTER, downloadLink);
            getContent().add(downloadLink);
        }

        getContent().add(hr);
        getContent().add(h25);
        getContent().add(hr2);
        getContent().add(h26);

        ContactaButton.addClickListener(event -> {
            UI.getCurrent().navigate("/consultas");
        });
       
    }
}