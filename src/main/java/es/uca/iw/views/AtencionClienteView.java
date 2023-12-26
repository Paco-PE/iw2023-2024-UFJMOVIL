package es.uca.iw.views;

import com.vaadin.flow.component.checkbox.Checkbox;
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
import jakarta.annotation.security.RolesAllowed;


@PageTitle("Atencion al cliente")
@Route(value = "/atencion", layout = MainLayout.class)
@RolesAllowed("ADMINISTRADOR")
public class AtencionClienteView extends VerticalLayout {
    private final ConsultaService consultaService;
    private final Grid<Consulta> grid = new Grid<>(Consulta.class);
    private Checkbox checkbox;


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

        grid.addComponentColumn(consulta -> {
            Checkbox checkbox = new Checkbox();
            checkbox.setValue(consulta.getResuelta()); // Establecer el valor inicial del Checkbox
            checkbox.addValueChangeListener(event -> {
                consulta.setResuelta(event.getValue()); // Actualizar el estado de resolución en la consulta
                consultaService.saveConsulta(consulta); // Guardar la consulta actualizada en la base de datos
            });
            return checkbox;
        }).setHeader("Resuelta");

        
        

        add(welcomeText);
        add(welcomeText2);
        add(grid);

        
    }
}
