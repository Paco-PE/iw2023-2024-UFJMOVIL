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
import jakarta.annotation.security.RolesAllowed;

@PageTitle("Contesta consulta")
@Route(value = "/consultas", layout = MainLayout.class)
@RolesAllowed("CLIENTE")
public class ContestaConsultaView extends VerticalLayout{
    ConsultaService consultaService;
    private final TextField descripcion;
    private final TextField emailcontacto;
    private final Button guardarBoton;

    public ContestaConsultaView(ConsultaService consultaService){
        this.consultaService = consultaService;
        descripcion = new TextField("Describe la consulta:");
        descripcion.setId("username");
        descripcion.setWidth("600px"); 
        emailcontacto = new TextField("Email de contacto:");
        emailcontacto.setId("emailContacto");
        add(descripcion);
        add(emailcontacto);

       guardarBoton = new Button("Guardar");
        guardarBoton.addClickListener(e -> {
            String descripcionUsuario = descripcion.getValue();
            String emailContactoUsuario = emailcontacto.getValue();

            Consulta consulta = new Consulta();
            consulta.setDescripcion(descripcionUsuario);
            consulta.setEmailContacto(emailContactoUsuario);

            consultaService.createConsulta(consulta);

            Notification notification = new Notification("Consulta guardada correctamente");
            notification.setPosition(Notification.Position.TOP_CENTER);
            notification.setDuration(3000);
            notification.open();
            descripcion.clear();
            emailcontacto.clear();
        });
        add(guardarBoton);
    }
}
