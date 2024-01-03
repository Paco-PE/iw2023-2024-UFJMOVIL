package es.uca.iw.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import es.uca.iw.MainLayout;
import es.uca.iw.domain.Fibra;
import es.uca.iw.domain.Servicio;
import es.uca.iw.domain.Telefonia;
import es.uca.iw.domain.Movil;
import es.uca.iw.services.FibraService;
import es.uca.iw.services.MovilService;
import es.uca.iw.services.ServicioService;
import es.uca.iw.services.TelefoniaService;
import jakarta.annotation.security.RolesAllowed;

@PageTitle("Crea Servicio")
@Route(value = "/creaservicio", layout = MainLayout.class)
@RolesAllowed({"EMPLEADO_COMERCIAL", "ADMINISTRADOR"})
public class CreaServicioView extends VerticalLayout{
    private final ServicioService servicioService;
    private final FibraService fibraService;
    private final TelefoniaService telefoniaService;
    private final MovilService movilService;
    private final TextField nombreField;
    private final TextField precioField;
    private final ComboBox<String> tipoServicioComboBox;
    private final TextField velocidadContratadaField;
    private final TextField minutosMaximos;
    private final TextField llamadasMaximas;
    private final TextField datosMaximos;
    private final Checkbox roaming;
    private final Button guardarButton;

    public CreaServicioView(ServicioService servicioService, FibraService fibraService, TelefoniaService telefoniaService, MovilService movilService) {
        this.servicioService = servicioService;
        this.fibraService = fibraService;
        this.telefoniaService = telefoniaService;
        this.movilService = movilService;

        nombreField = new TextField("Nombre del Servicio");
        precioField = new TextField("Precio del Servicio");
        tipoServicioComboBox = new ComboBox<>("Tipo de Servicio");
        tipoServicioComboBox.setItems("Fibra", "Telefonía");
        velocidadContratadaField = new TextField("Velocidad contratada (mb)");
        velocidadContratadaField.setVisible(false);
        minutosMaximos = new TextField("Minutos maximos permitidos");
        minutosMaximos.setVisible(false);
        llamadasMaximas = new TextField("Llamadas maximas permitidas");
        llamadasMaximas.setVisible(false);
        datosMaximos = new TextField("Datos maximos permitidos");
        datosMaximos.setVisible(false);
        roaming = new Checkbox("Roaming");
        roaming.setVisible(false);

        guardarButton = new Button("Guardar Servicio");
        guardarButton.addClickListener(e -> guardarServicio());

         tipoServicioComboBox.addValueChangeListener(event -> {
            String selectedTipoServicio = event.getValue();
            velocidadContratadaField.setVisible("Fibra".equals(selectedTipoServicio));
            minutosMaximos.setVisible("Telefonía".equals(selectedTipoServicio));
            llamadasMaximas.setVisible("Telefonía".equals(selectedTipoServicio));
            datosMaximos.setVisible("Telefonía".equals(selectedTipoServicio));
            roaming.setVisible("Telefonía".equals(selectedTipoServicio));
        });

        add(nombreField, precioField,tipoServicioComboBox,velocidadContratadaField,minutosMaximos,llamadasMaximas,datosMaximos,roaming, guardarButton);
    }

    private void guardarServicio() {
        String nombreServicio = nombreField.getValue();
        Float precioServicio = Float.parseFloat(precioField.getValue());
        String tipoServicio = tipoServicioComboBox.getValue();

        Servicio nuevoServicio = new Servicio();
        nuevoServicio.setName(nombreServicio);
        nuevoServicio.setPrecio(precioServicio);
        nuevoServicio.setTipoServicio(tipoServicio);
        if ("Fibra".equals(tipoServicio)) {
            Fibra nuevaFibra = new Fibra();
            nuevaFibra.setVelocidadContratadaMb(Float.parseFloat(velocidadContratadaField.getValue()));
        }
        if ("Telefonía".equals(tipoServicio)){
            Telefonia nuevaTelefonia = new Telefonia();
            Movil nuevMovil = new Movil();
            nuevaTelefonia.setMinutosMaximos(Integer.parseInt(minutosMaximos.getValue()));
            nuevaTelefonia.setLlamadasMaximas(Integer.parseInt(llamadasMaximas.getValue()));
            nuevMovil.setDatosMaximosGB(Integer.parseInt(datosMaximos.getValue()));
            nuevMovil.setRoaming(roaming.getValue());
            
        }
        servicioService.SaveServicio(nuevoServicio);
        Notification notification = new Notification("Servicio guardado correctamente");
        notification.setPosition(Notification.Position.TOP_CENTER);
        notification.setDuration(3000);
        notification.open();
        nombreField.clear();
        precioField.clear();
        velocidadContratadaField.clear();
        minutosMaximos.clear();
        llamadasMaximas.clear();
        datosMaximos.clear();
        roaming.clear();
    }
}