package es.uca.iw.views;


import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import es.uca.iw.MainLayout;
import es.uca.iw.domain.Consulta;
import es.uca.iw.domain.Servicio;
import es.uca.iw.services.ServicioService;

@PageTitle("Ventas")
@Route(value = "/ventas", layout = MainLayout.class)
public class VentasView extends VerticalLayout {
    private final ServicioService servicioService;
    private final Grid<Servicio> grid = new Grid<>(Servicio.class);

    public VentasView(ServicioService servicioService){
        this.servicioService = servicioService;
        H1 welcomeText = new H1("UFJMOVIL");
        H2 welcomeText2 = new H2("Bienvenido, departamento de ventas y marketing");

        VerticalLayout layoutcolumn = new VerticalLayout();
        layoutcolumn.setWidthFull();
        layoutcolumn.setAlignSelf(FlexComponent.Alignment.CENTER,welcomeText);
        layoutcolumn.setAlignSelf(FlexComponent.Alignment.CENTER,welcomeText2);

        grid.setItems(servicioService.findAll());
        grid.removeAllColumns(); // Eliminar todas las columnas generadas automáticamente
        grid.addColumn(Servicio::getName).setHeader("Name");
        grid.addColumn(Servicio::getPrecio).setHeader("Precio");

        add(welcomeText);
        add(welcomeText2);
        add(grid);

    }
}
