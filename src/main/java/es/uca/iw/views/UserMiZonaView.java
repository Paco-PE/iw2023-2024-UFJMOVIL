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
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Anchor;
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
    private Cliente cliente;
    private Button ContactaButton = new Button("Contacta con nosotros");
    private StreamResource pdfResource;
    private com.vaadin.flow.component.html.Anchor downloadLink;
    private Grid<Fibra> gridFibra;
    private Grid<Telefonia> gridFijo;
    private Grid<Movil> gridMovil;
    private Grid<Movil> gridMisMoviles;

    private void updateFactura(){
        pdfResource = PdfService.generarFactura(cliente);
        downloadLink.setHref(pdfResource);
    }

    public UserMiZonaView(AuthenticatedUser authenticatedUser, ClienteService clienteService, FibraService fibraService, MovilService movilService, TelefoniaService telefoniaService, ContratoService contratoService) {
        this.authenticatedUser = authenticatedUser;

        Optional<User> maybeUser = this.authenticatedUser.get();
        if (maybeUser.isPresent()) {
            User user = maybeUser.get();
            cliente = clienteService.loadClienteById(user.getId());
        }

        pdfResource = PdfService.generarFactura(cliente);
        downloadLink = new com.vaadin.flow.component.html.Anchor(pdfResource, "Generar Factura");
        downloadLink.setTarget("_blank");

        List<Contrato> contratos = cliente.getContratos();
        Set<Servicio> serviciosOld = new HashSet<>();
        Set<Servicio> serviciosNew = new HashSet<>();
        for (Contrato contrato : contratos) {
            serviciosOld.add(contrato.getServicio());
            serviciosNew.add(contrato.getServicio());
        }

        gridFibra = new Grid<>();
        gridFibra.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        gridFibra.setAllRowsVisible(true);
        gridFibra.addColumn(Fibra::getName).setHeader("Nombre");
        gridFibra.addColumn(Fibra::getVelocidadContratadaMb).setHeader("Velocidad");
        gridFibra.addColumn(Fibra::getPrecio).setHeader("Precio");
        gridFibra.setItems(fibraService.findAll());
        gridFibra.setWidthFull();
        
        //Hacemos que solo se pueda seleccionar uno de los checkboxes
        Checkbox[] previousCheckboxFibra = {null};
        gridFibra.addComponentColumn(fibra -> {
            Checkbox checkbox = new Checkbox();
            checkbox.setValue(serviciosNew.contains(fibra));
            checkbox.addValueChangeListener(event -> {
                if (event.getValue()) {
                    // Si hay un Checkbox seleccionado previamente, desmárcalo
                    if (previousCheckboxFibra[0] != null) {
                        previousCheckboxFibra[0].setValue(false);
                    }
                    // Actualiza el Checkbox seleccionado previamente
                    previousCheckboxFibra[0] = checkbox;
                    serviciosNew.add(fibra);
                } else {
                    serviciosNew.remove(fibra);
                    if (previousCheckboxFibra[0] == checkbox) {
                        // Si el Checkbox desmarcado era el seleccionado previamente, actualiza previousCheckbox a null
                        previousCheckboxFibra[0] = null;
                    }
                }
            });
            // Si el Checkbox está seleccionado al crear la tabla, haz que sea el seleccionado previamente
            if (checkbox.getValue()) {
                previousCheckboxFibra[0] = checkbox;
            }
            return checkbox;
        }).setHeader("Contratado");
        
        gridFijo = new Grid<>();
        gridFijo.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        gridFijo.setAllRowsVisible(true);
        gridFijo.addColumn(Telefonia::getName).setHeader("Nombre");
        gridFijo.addColumn(Telefonia::getMinutosMaximos).setHeader("Minutos máximos");
        gridFijo.addColumn(Telefonia::getLlamadasMaximas).setHeader("Llamadas máximas");
        gridFijo.addColumn(Telefonia::getPrecio).setHeader("Precio");
        gridFijo.setItems(telefoniaService.findAllFijo());
        gridFijo.setWidthFull();

        //Hacemos que solo se pueda seleccionar uno de los checkboxes
        Checkbox[] previousCheckboxFijo = {null};
        gridFijo.addComponentColumn(fijo -> {
            Checkbox checkbox = new Checkbox();
            checkbox.setValue(serviciosNew.contains(fijo));
            checkbox.addValueChangeListener(event -> {
                if (event.getValue()) {
                    // Si hay un Checkbox seleccionado previamente, desmárcalo
                    if (previousCheckboxFijo[0] != null) {
                        previousCheckboxFijo[0].setValue(false);
                    }
                    // Actualiza el Checkbox seleccionado previamente
                    previousCheckboxFijo[0] = checkbox;
                    serviciosNew.add(fijo);
                } else {
                    serviciosNew.remove(fijo);
                    if (previousCheckboxFijo[0] == checkbox) {
                        // Si el Checkbox desmarcado era el seleccionado previamente, actualiza previousCheckbox a null
                        previousCheckboxFijo[0] = null;
                    }
                }
            });
            // Si el Checkbox está seleccionado al crear la tabla, haz que sea el seleccionado previamente
            if (checkbox.getValue()) {
                previousCheckboxFijo[0] = checkbox;
            }
            return checkbox;
        }).setHeader("Contratado");
        
        gridMovil = new Grid<>();
        gridMovil.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        gridMovil.setAllRowsVisible(true);
        gridMovil.addColumn(Movil::getName).setHeader("Nombre");
        gridMovil.addColumn(Movil::getMinutosMaximos).setHeader("Minutos máximos");
        gridMovil.addColumn(Movil::getLlamadasMaximas).setHeader("Llamadas máximas");
        gridMovil.addColumn(Movil::getDatosMaximosGB).setHeader("Datos máximos");
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

        Grid<Movil> gridMisMoviles = new Grid<>();
        gridMisMoviles.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        gridMisMoviles.setAllRowsVisible(true);
        gridMisMoviles.addColumn(movil -> {
            Contrato contrato = cliente.getContratos().stream()
                .filter(c -> c.getServicio().equals(movil))
                .findFirst()
                .orElse(null);
        
            return contrato != null ? contrato.getNumeroTelefono() : "";
        }).setHeader("Número de teléfono");
        gridMisMoviles.addColumn(Movil::getName).setHeader("Tarifa");
        gridMisMoviles.addComponentColumn(movil -> {
            Contrato contrato = cliente.getContratos().stream()
                .filter(c -> c.getServicio().equals(movil))
                .findFirst()
                .orElse(null);
        
            if (contrato != null) {
                Checkbox checkbox = new Checkbox();
                checkbox.setValue(contrato.isRoaming());
                checkbox.addValueChangeListener(event -> {
                    contrato.setRoaming(event.getValue());
                    contratoService.save(contrato);
                });
                return checkbox;
            } else {
                return new Checkbox(); // Devuelve un Checkbox vacío si no se encuentra el contrato
            }
        }).setHeader("Roaming");
        gridMisMoviles.addComponentColumn(movil -> {
        Contrato contrato = cliente.getContratos().stream()
            .filter(c -> c.getServicio().equals(movil))
            .findFirst()
            .orElse(null);

            if (contrato != null) {
                String url = "numeros-bloqueados/" + contrato.getNumeroTelefono();
                Anchor anchor = new Anchor(url, "Ver números bloqueados");
                return anchor;
            } else {
                return new Text(""); // Devuelve un texto vacío si no se encuentra el contrato
            }
        }).setHeader("Números bloqueados");
        gridMisMoviles.setWidthFull();
        List<Movil> movilesContratados = cliente.getContratos().stream()
            .filter(contrato -> contrato.getServicio() instanceof Movil)
            .map(contrato -> (Movil) contrato.getServicio())
            .collect(Collectors.toList());
        gridMisMoviles.setItems(movilesContratados);


        H2 txtServiciosOfertados = new H2();
        VerticalLayout layoutColumn = new VerticalLayout();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        Button actualizaButton = new Button();
        Hr hr = new Hr();
        H2 h24 = new H2();
        Hr hr2 = new Hr();
        H2 h25 = new H2();
        Hr hr3 = new Hr();
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
        layoutColumn2.setHeightFull();
        layoutColumn2.setWidthFull();
        layoutColumn2.setJustifyContentMode(JustifyContentMode.START);
        layoutColumn2.setAlignItems(Alignment.START);
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
                    //Eliminamos los contratos que ya no se quieren
                    if(cliente.getContratos() != null){
                        Iterator<Contrato> iterator = cliente.getContratos().iterator();
                        while (iterator.hasNext()) {
                            Contrato contrato = iterator.next();
                            if (serviciosOld.contains(contrato.getServicio()) && !serviciosNew.contains(contrato.getServicio())) {
                                contratoService.descontratar(contrato.getId());
                                iterator.remove();
                            }
                        }
                        clienteService.save(cliente);
                    }

                    //Añadimos los nuevos contratos
                    Set<Servicio> newServices = new HashSet<>(serviciosNew);
                    newServices.removeAll(serviciosOld);

                    for (Servicio servicio : newServices) {
                        cliente.addContrato(contratoService.contratar(servicio.getId(), cliente.getId(), new Date(), null, servicio.getPrecio()));
                    }

                    clienteService.save(cliente);
                    serviciosOld.clear();
                    serviciosOld.addAll(serviciosNew);
                    updateFactura();
                    Notification.show("Contrato actualizado");
                } else {
                    Notification.show("No se han seleccionado servicios");
                }
            }
        });

        h24.setText("Mis móviles");
        h24.setWidth("max-content");
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
        getContent().add(actualizaButton);
        getContent().add(ContactaButton);

        getContent().setAlignSelf(FlexComponent.Alignment.CENTER, downloadLink);
        getContent().add(downloadLink);

        getContent().add(hr);
        getContent().add(h24);
        getContent().add(layoutColumn2);
        layoutColumn2.add(gridMisMoviles);
        getContent().add(hr2);
        getContent().add(h25);
        getContent().add(hr3);
        getContent().add(h26);

        ContactaButton.addClickListener(event -> {
            UI.getCurrent().navigate("/consultas");
        });
       
    }
}