package es.uca.iw.views;

import es.uca.iw.MainLayout;
import es.uca.iw.domain.Cliente;
import es.uca.iw.domain.Contrato;
import es.uca.iw.domain.Fibra;
import es.uca.iw.domain.Movil;
import es.uca.iw.domain.Servicio;
import es.uca.iw.domain.Telefonia;
import es.uca.iw.domain.User;
import es.uca.iw.security.AuthenticatedUser;
import es.uca.iw.services.ClienteService;
import es.uca.iw.services.ContratoService;
import es.uca.iw.services.FibraService;
import es.uca.iw.services.MovilService;
import es.uca.iw.services.PdfService;
import es.uca.iw.services.TelefoniaService;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.hibernate.Hibernate;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
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
import com.vaadin.flow.data.renderer.ComponentRenderer;
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
    private Cliente cliente;
    private Button ContactaButton = new Button("Contacta con nosotros");

    public UserMiZonaView(AuthenticatedUser authenticatedUser, ClienteService clienteService, FibraService fibraService, MovilService movilService, TelefoniaService telefoniaService, ContratoService contratoService) {
        this.authenticatedUser = authenticatedUser;

        Optional<User> maybeUser = this.authenticatedUser.get();
        if (maybeUser.isPresent()) {
            User user = maybeUser.get();
            cliente = clienteService.loadClienteById(user.getId());
        }

        StreamResource pdfResource;
        com.vaadin.flow.component.html.Anchor downloadLink;

        List<Contrato> contratos = cliente.getContratos();
        Set<Servicio> serviciosOld = new HashSet<>();
        Set<Servicio> serviciosNew = new HashSet<>();
        for (Contrato contrato : contratos) {
            serviciosOld.add(contrato.getServicio());
            serviciosNew.add(contrato.getServicio());
        }

        Grid<Fibra> gridFibra = new Grid<>();
        gridFibra.addColumn(Fibra::getName).setHeader("Nombre");
        gridFibra.addColumn(Fibra::getVelocidadContratadaMb).setHeader("Velocidad");
        gridFibra.addColumn(Fibra::getPrecio).setHeader("Precio");
        gridFibra.setItems(fibraService.findAll());
        gridFibra.setWidthFull();
        gridFibra.addComponentColumn(fibra -> {
            Checkbox checkbox = new Checkbox();
            checkbox.setValue(serviciosNew.contains(fibra));
            checkbox.addValueChangeListener(event -> {
                if (event.getValue()) {
                    serviciosNew.add(fibra);
                } else {
                    serviciosNew.remove(fibra);
                }
            });
            return checkbox;
        }).setHeader("Contratado");
        
        Grid<Telefonia> gridFijo = new Grid<>();
        gridFijo.addColumn(Telefonia::getName).setHeader("Nombre");
        gridFijo.addColumn(Telefonia::getMinutosMaximos).setHeader("Minutos máximos");
        gridFijo.addColumn(Telefonia::getLlamadasMaximas).setHeader("Llamadas máximas");
        gridFijo.addColumn(Telefonia::getPrecio).setHeader("Precio");
        gridFijo.setItems(telefoniaService.findAllFijo());
        gridFijo.setWidthFull();
        gridFijo.addComponentColumn(fijo -> {
            Checkbox checkbox = new Checkbox();
            checkbox.setValue(serviciosNew.contains(fijo));
            checkbox.addValueChangeListener(event -> {
                if (event.getValue()) {
                    serviciosNew.add(fijo);
                } else {
                    serviciosNew.remove(fijo);
                }
            });
            return checkbox;
        }).setHeader("Contratado");
        
        Grid<Movil> gridMovil = new Grid<>();
        gridMovil.addColumn(Movil::getName).setHeader("Nombre");
        gridMovil.addColumn(Movil::getMinutosMaximos).setHeader("Minutos máximos");
        gridMovil.addColumn(Movil::getLlamadasMaximas).setHeader("Llamadas máximas");
        gridMovil.addColumn(Movil::getDatosMaximosGB).setHeader("Datos máximos");
        gridMovil.addColumn(Movil::getRoaming).setHeader("Roaming");
        gridMovil.addColumn(Movil::getPrecio).setHeader("Precio");
        gridMovil.setItems(movilService.findAll());
        gridMovil.setWidthFull();
        gridMovil.addComponentColumn(movil -> {
            Checkbox checkbox = new Checkbox();
            checkbox.setValue(serviciosNew.contains(movil));
            checkbox.addValueChangeListener(event -> {
                if (event.getValue()) {
                    serviciosNew.add(movil);
                } else {
                    serviciosNew.remove(movil);
                }
            });
            return checkbox;
        }).setHeader("Contratado");


        H2 txtServiciosOfertados = new H2();
        VerticalLayout layoutColumn = new VerticalLayout();
        Button actualizaButton = new Button();
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

        actualizaButton.setText("Actualizar contrato");
        getContent().setAlignSelf(FlexComponent.Alignment.CENTER, actualizaButton);
        actualizaButton.setWidth("min-content");
        actualizaButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        actualizaButton.addClickListener(event -> {
            if(serviciosOld.equals(serviciosNew)){
                Notification.show("No se han realizado cambios");
            }else{
                if(!serviciosNew.isEmpty()){
                    if(cliente.getContratos() != null){
                        for (Contrato contrato : cliente.getContratos()) {
                            contratoService.descontratar(contrato.getId());
                        }
                        cliente.setContratos(null);
                        clienteService.save(cliente);
                    }
                    for (Servicio servicio : serviciosNew) {
                        cliente.addContrato(contratoService.contratar(servicio.getId(), cliente.getId(), new Date(), null, servicio.getPrecio()));
                    }
                    clienteService.save(cliente);
                    serviciosOld.clear();
                    serviciosOld.addAll(serviciosNew);
                    Notification.show("Contrato actualizado");
                } else {
                    Notification.show("No se han seleccionado servicios");
                }
            }
        });
        h25.setText("Consumo de datos 5G");
        h25.setWidth("max-content");
        h26.setText("Desglose de llamadas");
        h26.setWidth("max-content");

        pdfResource = PdfService.generarFactura(cliente);
        downloadLink = new com.vaadin.flow.component.html.Anchor(pdfResource, "Generar Factura");
        downloadLink.setTarget("_blank");

        getContent().add(txtServiciosOfertados);
        getContent().add(layoutColumn);
        layoutColumn.add(txtFibra);
        layoutColumn.add(gridFibra);
        layoutColumn.add(txtFijo);
        layoutColumn.add(gridFijo);
        layoutColumn.add(txtMovil);
        layoutColumn.add(gridMovil);
        getContent().add(actualizaButton);
        getContent().add(ContactaButton);

        getContent().setAlignSelf(FlexComponent.Alignment.CENTER, downloadLink);
        getContent().add(downloadLink);

        getContent().add(hr);
        getContent().add(h25);
        getContent().add(hr2);
        getContent().add(h26);

        ContactaButton.addClickListener(event -> {
            UI.getCurrent().navigate("/consultas");
        });
       
    }
}