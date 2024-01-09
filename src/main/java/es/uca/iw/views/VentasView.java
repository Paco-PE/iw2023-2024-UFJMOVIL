package es.uca.iw.views;


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import java.util.List;

import es.uca.iw.MainLayout;
import es.uca.iw.domain.Servicio;
import es.uca.iw.domain.Fibra;
import es.uca.iw.domain.Telefonia;
import es.uca.iw.services.FibraService;
import es.uca.iw.services.MovilService;
import es.uca.iw.services.ServicioService;
import es.uca.iw.services.TelefoniaService;
import jakarta.annotation.security.RolesAllowed;

@PageTitle("Ventas")
@Route(value = "/ventas", layout = MainLayout.class)
@RolesAllowed({"EMPLEADO_COMERCIAL", "ADMINISTRADOR"})
public class VentasView extends VerticalLayout {
    private final Grid<Fibra> grid = new Grid<>(Fibra.class);
    private final Grid<Telefonia> grid2 = new Grid<>(Telefonia.class);

    private <T extends Servicio> void configureGrid(Grid<T> grid, List<T> items, ValueProvider<T, ?>... columns) {
        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        grid.setAllRowsVisible(true);
        grid.setItems(items);
        grid.removeAllColumns();
        for (ValueProvider<T, ?> column : columns) {
            grid.addColumn(column).setHeader(column.toString());
        }
    }

    public VentasView(ServicioService servicioService,FibraService fibraService, MovilService movilService, TelefoniaService telefoniaService){
        H1 welcomeText = new H1("UFJMOVIL");
        H2 welcomeText2 = new H2("Bienvenido, departamento de ventas y marketing");
        H4 fibraText = new H4("Servicios de fibra disponibles:");
        H4 fijoText = new H4("Servicios de telefonía disponibles:");

        VerticalLayout layoutcolumn = new VerticalLayout();
        layoutcolumn.setWidthFull();
        layoutcolumn.setAlignSelf(FlexComponent.Alignment.CENTER,welcomeText);
        layoutcolumn.setAlignSelf(FlexComponent.Alignment.CENTER,welcomeText2);

        configureGrid(grid, fibraService.findAll(), Fibra::getName, Fibra::getPrecio, Fibra::getVelocidadContratadaMb);

        grid.addComponentColumn(servicio -> {
            Button deleteButton = new Button("Eliminar");
            deleteButton.addClickListener(e -> {
                servicioService.deleteServicio(servicio);
                grid.setItems(fibraService.findAll());
            });

            Button modifyButton = new Button("Modificar");
            modifyButton.addClickListener(e -> {
                UI.getCurrent().navigate("modificaservicio/" + servicio.getId().toString());
            });

            HorizontalLayout buttonLayout = new HorizontalLayout(deleteButton, modifyButton);
            buttonLayout.setAlignItems(FlexComponent.Alignment.START);

            return buttonLayout;
        }).setHeader("Acciones");

        configureGrid(grid2, telefoniaService.findAll(), Telefonia::getName, Telefonia::getTipoServicio, Telefonia::getPrecio, Telefonia::getMinutosMaximos, Telefonia::getLlamadasMaximas);


        grid2.addComponentColumn(servicio -> {
            Button deleteButton = new Button("Eliminar");
            deleteButton.addClickListener(e -> {
                servicioService.deleteServicio(servicio);
                grid.setItems(fibraService.findAll());
            });

            Button modifyButton = new Button("Modificar");
            modifyButton.addClickListener(e -> {
                UI.getCurrent().navigate("modificaservicio/" + servicio.getId().toString());
            });

            HorizontalLayout buttonLayout = new HorizontalLayout(deleteButton, modifyButton);
            buttonLayout.setAlignItems(FlexComponent.Alignment.START);

            return buttonLayout;
        }).setHeader("Acciones");

         Button editButton = new Button("Crear nuevo servicio");
            editButton.addClickListener(e -> {
                UI.getCurrent().navigate("/creaservicio");
            });

        

        add(welcomeText);
        add(welcomeText2);
        add(fibraText);
        add(grid);
        add(fijoText);
        add(grid2);
        add(editButton);

    }
}
