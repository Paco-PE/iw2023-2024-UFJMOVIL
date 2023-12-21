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
import es.uca.iw.services.ConsultaService;


@PageTitle("Atencion al cliente")
@Route(value = "/atencion", layout = MainLayout.class)
public class AtencionClienteView extends VerticalLayout {
    private final ConsultaService consultaService;
    private final Grid<Consulta> grid = new Grid<>(Consulta.class);


    public AtencionClienteView(ConsultaService consultaService){
        this.consultaService = consultaService; 
        H1 welcomeText = new H1("UFJMOVIL");
        H2 welcomeText2 = new H2("Bienvenido, departamento de atención al cliente");

        VerticalLayout layoutcolumn = new VerticalLayout();
        layoutcolumn.setWidthFull();
        layoutcolumn.setAlignSelf(FlexComponent.Alignment.CENTER,welcomeText);
        layoutcolumn.setAlignSelf(FlexComponent.Alignment.CENTER,welcomeText2);

        grid.setItems(consultaService.findAll());
        grid.removeAllColumns(); // Eliminar todas las columnas generadas automáticamente
        grid.addColumn(Consulta::getDescripcion).setHeader("Descripción");

        add(welcomeText);
        add(welcomeText2);
        add(grid);

        
    }
}
