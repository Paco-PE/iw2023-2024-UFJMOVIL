package es.uca.iw.views;

import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import es.uca.iw.MainLayout;
import es.uca.iw.domain.Consulta;
import es.uca.iw.services.ConsultaService;
import es.uca.iw.domain.Contrato;
import es.uca.iw.services.ContratoService;
import jakarta.annotation.security.RolesAllowed;


@PageTitle("Atencion al cliente")
@Route(value = "/atencion", layout = MainLayout.class)
@RolesAllowed({"EMPLEADO_ATENCION_CLIENTE", "ADMINISTRADOR"})
public class AtencionClienteView extends VerticalLayout {
    private final ConsultaService consultaService;
    private final ContratoService contratoService;
    private final Grid<Consulta> grid = new Grid<>(Consulta.class);
    private final Grid<Contrato> grid2 = new Grid<>(Contrato.class);
    private Checkbox checkbox;


    public AtencionClienteView(ConsultaService consultaService,ContratoService contratoService){
        this.consultaService = consultaService;
        this.contratoService = contratoService;
        H1 welcomeText = new H1("UFJMOVIL");
        H2 welcomeText2 = new H2("Bienvenido, departamento de atención al cliente");
        H4 consultasText = new H4("Consultas de nuestros clientes:");
        H4 contratosText = new H4("Contratos de nuestros clientes:");
        

        VerticalLayout layoutcolumn = new VerticalLayout();
        layoutcolumn.setWidthFull();
        layoutcolumn.setAlignSelf(FlexComponent.Alignment.CENTER,welcomeText);
        layoutcolumn.setAlignSelf(FlexComponent.Alignment.CENTER,welcomeText2);

        grid.setItems(consultaService.findAll());
        grid.removeAllColumns(); // Eliminar todas las columnas generadas automáticamente
        grid.addColumn(Consulta::getDescripcion).setHeader("Descripción");
        grid.addColumn(Consulta::getEmailContacto).setHeader("Email del cliente");

        grid.addComponentColumn(consulta -> {
            Checkbox checkbox = new Checkbox();
            checkbox.setValue(consulta.getResuelta()); // Establecer el valor inicial del Checkbox
            checkbox.addValueChangeListener(event -> {
                consulta.setResuelta(event.getValue()); // Actualizar el estado de resolución en la consulta
                consultaService.saveConsulta(consulta); // Guardar la consulta actualizada en la base de datos
            });
            return checkbox;
        }).setHeader("Resuelta");

        grid2.setItems(contratoService.findAll());
        grid2.removeAllColumns();
        grid2.addColumn(Contrato::getFechaInicio).setHeader("Fecha inicio");
        grid2.addColumn(Contrato::getFechaFin).setHeader("Fecha fin");
        grid2.addColumn(Contrato::getCosteMensual).setHeader("Coste mensual");
       
        add(welcomeText);
        add(welcomeText2);
        add(consultasText);
        add(grid);
        add(contratosText);
        add(grid2);

        
    }
}
