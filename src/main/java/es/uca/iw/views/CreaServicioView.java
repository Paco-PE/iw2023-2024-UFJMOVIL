package es.uca.iw.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import es.uca.iw.MainLayout;
import es.uca.iw.domain.Servicio;
import es.uca.iw.services.ServicioService;
import jakarta.annotation.security.RolesAllowed;

@PageTitle("Crea Servicio")
@Route(value = "/creaservicio", layout = MainLayout.class)
@RolesAllowed("ADMINISTRADOR")
public class CreaServicioView extends VerticalLayout{
 private final ServicioService servicioService;
    private final TextField nombreField;
    private final TextField precioField;
    private final ComboBox<String> tipoServicioComboBox;
    private final Button guardarButton;

    public CreaServicioView(ServicioService servicioService) {
        this.servicioService = servicioService;

        nombreField = new TextField("Nombre del Servicio");
        precioField = new TextField("Precio del Servicio");
        tipoServicioComboBox = new ComboBox<>("Tipo de Servicio");
        tipoServicioComboBox.setItems("Fibra", "Telefonía");

        guardarButton = new Button("Guardar Servicio");
        guardarButton.addClickListener(e -> guardarServicio());

        add(nombreField, precioField,tipoServicioComboBox, guardarButton);
    }

    private void guardarServicio() {
        String nombreServicio = nombreField.getValue();
        Float precioServicio = Float.parseFloat(precioField.getValue());
        String tipoServicio = tipoServicioComboBox.getValue();

        Servicio nuevoServicio = new Servicio();
        nuevoServicio.setName(nombreServicio);
        nuevoServicio.setPrecio(precioServicio);
        nuevoServicio.setTipoServicio(tipoServicio);
        servicioService.SaveServicio(nuevoServicio);
        Notification notification = new Notification("Servicio guardado correctamente");
        notification.setPosition(Notification.Position.TOP_CENTER);
        notification.setDuration(3000);
        notification.open();
        nombreField.clear();
        precioField.clear();
    }
}