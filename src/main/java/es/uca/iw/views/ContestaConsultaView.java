package es.uca.iw.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import es.uca.iw.MainLayout;
import es.uca.iw.domain.Consulta;
import es.uca.iw.services.ConsultaService;

@PageTitle("Contesta consulta")
@Route(value = "/consultas", layout = MainLayout.class)
public class ContestaConsultaView extends VerticalLayout{
    ConsultaService consultaService;
    private final TextField descripcion;
    private final Button guardarBoton;

    public ContestaConsultaView(ConsultaService consultaService){
        this.consultaService = consultaService;
        descripcion = new TextField("Describe la consulta:");
        descripcion.setId("username");
        descripcion.setWidth("600px"); // Set a large width
        descripcion.setHeight("100px");
        add(descripcion);

       guardarBoton = new Button("Guardar");
        guardarBoton.addClickListener(e -> {
            // Get the user-provided description
            String descripcionUsuario = descripcion.getValue();

            // Create a new Consulta object
            Consulta consulta = new Consulta();
            consulta.setDescripcion(descripcionUsuario);

            // Save the consulta with the user-provided description
            consultaService.createConsulta(consulta);

            Notification notification = new Notification("Consulta guardada correctamente");
            notification.setPosition(Notification.Position.TOP_CENTER);
            notification.setDuration(3000);
            notification.open();
            descripcion.clear();
        });
        add(guardarBoton);
    }
}
