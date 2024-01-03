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
    private final TextField minutosMaximosMovil;
    private final TextField llamadasMaximas;
    private final TextField llamadasMaximasMovil;
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
        tipoServicioComboBox.setItems("Fibra", "Fijo", "Móvil");
        velocidadContratadaField = new TextField("Velocidad contratada (mb)");
        velocidadContratadaField.setVisible(false);
        minutosMaximos = new TextField("Minutos maximos permitidos");
        minutosMaximos.setVisible(false);
        minutosMaximosMovil = new TextField("Minutos maximos permitidos");
        minutosMaximosMovil.setVisible(false);
        llamadasMaximas = new TextField("Llamadas maximas permitidas");
        llamadasMaximas.setVisible(false);
        llamadasMaximasMovil = new TextField("Llamadas maximas permitidas");
        llamadasMaximasMovil.setVisible(false);
        datosMaximos = new TextField("Datos maximos permitidos");
        datosMaximos.setVisible(false);
        roaming = new Checkbox("Roaming");
        roaming.setVisible(false);

        guardarButton = new Button("Guardar Servicio");
        guardarButton.addClickListener(e -> guardarServicio());

         tipoServicioComboBox.addValueChangeListener(event -> {
            String selectedTipoServicio = event.getValue();
            velocidadContratadaField.setVisible("Fibra".equals(selectedTipoServicio));
            minutosMaximos.setVisible("Fijo".equals(selectedTipoServicio));
            minutosMaximosMovil.setVisible("Móvil".equals(selectedTipoServicio));
            llamadasMaximas.setVisible("Fijo".equals(selectedTipoServicio));
            llamadasMaximasMovil.setVisible("Móvil".equals(selectedTipoServicio));
            datosMaximos.setVisible("Móvil".equals(selectedTipoServicio));
            roaming.setVisible("Móvil".equals(selectedTipoServicio));
        });

        add(nombreField, precioField,tipoServicioComboBox,velocidadContratadaField,minutosMaximos,minutosMaximosMovil,llamadasMaximas,llamadasMaximasMovil,datosMaximos,roaming, guardarButton);
    }

    private void guardarServicio() {
        String nombreServicio = nombreField.getValue();
        Float precioServicio = Float.parseFloat(precioField.getValue());
        String tipoServicio = tipoServicioComboBox.getValue();

        Servicio nuevoServicio = new Servicio();
        if ("Fibra".equals(tipoServicio)) {
            Fibra nuevaFibra = new Fibra();
            nuevaFibra.setName(nombreServicio);
            nuevaFibra.setPrecio(precioServicio);
            nuevaFibra.setTipoServicio(tipoServicio);
            nuevaFibra.setVelocidadContratadaMb(Float.parseFloat(velocidadContratadaField.getValue()));
            fibraService.SaveFibra(nuevaFibra);
        }
        if ("Fijo".equals(tipoServicio)){
            Telefonia nuevaTelefonia = new Telefonia();
            nuevaTelefonia.setName(nombreServicio);
            nuevaTelefonia.setPrecio(precioServicio);
            nuevaTelefonia.setTipoServicio(tipoServicio);
            nuevaTelefonia.setMinutosMaximos(Integer.parseInt(minutosMaximos.getValue()));
            nuevaTelefonia.setLlamadasMaximas(Integer.parseInt(llamadasMaximas.getValue()));
            telefoniaService.SaveTelefonia(nuevaTelefonia);
            
        }
        if ("Móvil".equals(tipoServicio)){
            Movil nuevoMovil = new Movil();
            nuevoMovil.setName(nombreServicio);
            nuevoMovil.setPrecio(precioServicio);
            nuevoMovil.setTipoServicio(tipoServicio);
            nuevoMovil.setMinutosMaximos(Integer.parseInt(minutosMaximosMovil.getValue()));
            nuevoMovil.setLlamadasMaximas(Integer.parseInt(llamadasMaximasMovil.getValue()));
            nuevoMovil.setDatosMaximosGB(Integer.parseInt(datosMaximos.getValue()));
            nuevoMovil.setRoaming(roaming.getValue());
            movilService.SaveMovil(nuevoMovil);
            
        }
        Notification notification = new Notification("Servicio guardado correctamente");
        notification.setPosition(Notification.Position.TOP_CENTER);
        notification.setDuration(3000);
        notification.open();
        nombreField.clear();
        precioField.clear();
        velocidadContratadaField.clear();
        minutosMaximos.clear();
        minutosMaximosMovil.clear();
        llamadasMaximas.clear();
        llamadasMaximasMovil.clear();
        datosMaximos.clear();
        roaming.clear();
    }
}