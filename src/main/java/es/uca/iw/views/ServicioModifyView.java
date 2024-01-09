package es.uca.iw.views;

import java.util.Optional;
import java.util.UUID;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
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
@Route(value = "/modificaservicio", layout = MainLayout.class)
@RolesAllowed({"EMPLEADO_COMERCIAL", "ADMINISTRADOR"})
public class ServicioModifyView extends VerticalLayout implements HasUrlParameter<String>{
    private H2 title = new H2("Modificar Servicio");
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
    private final Button guardarButton;
    private Servicio servicio;

    private TextField createTextField(String label, boolean visible) {
        TextField textField = new TextField(label);
        textField.setVisible(visible);
        return textField;
    }

    public ServicioModifyView(ServicioService servicioService, FibraService fibraService, TelefoniaService telefoniaService, MovilService movilService) {
        this.servicioService = servicioService;
        this.fibraService = fibraService;
        this.telefoniaService = telefoniaService;
        this.movilService = movilService;

        nombreField = createTextField("Nombre del Servicio", true);
        precioField = createTextField("Precio del Servicio", true);
        velocidadContratadaField = createTextField("Velocidad contratada (mb)", false);
        minutosMaximos = createTextField("Minutos maximos permitidos", false);
        minutosMaximosMovil = createTextField("Minutos maximos permitidos", false);
        llamadasMaximas = createTextField("Llamadas maximas permitidas", false);
        llamadasMaximasMovil = createTextField("Llamadas maximas permitidas", false);
        datosMaximos = createTextField("Datos maximos permitidos", false);
        tipoServicioComboBox = new ComboBox<>("Tipo de Servicio");
        tipoServicioComboBox.setItems("Fibra", "Fijo", "Móvil");
        tipoServicioComboBox.setReadOnly(true);

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
        });

        add(title, nombreField, precioField,tipoServicioComboBox,velocidadContratadaField,minutosMaximos,minutosMaximosMovil,llamadasMaximas,llamadasMaximasMovil,datosMaximos, guardarButton);
    }

    private void guardarServicio() {
        String nombreServicio = nombreField.getValue();
        Float precioServicio = Float.parseFloat(precioField.getValue());
        String tipoServicio = tipoServicioComboBox.getValue();

        switch (tipoServicio) {
            case "Fibra":
                if (servicio instanceof Fibra) {
                    Fibra fibraExistente = (Fibra) servicio;
                    fibraExistente.setName(nombreServicio);
                    fibraExistente.setPrecio(precioServicio);
                    fibraExistente.setTipoServicio(tipoServicio);
                    fibraExistente.setVelocidadContratadaMb(Float.parseFloat(velocidadContratadaField.getValue()));
                    fibraService.UpdateFibra(fibraExistente);
                }
                break;
            case "Fijo":
                if (servicio instanceof Telefonia) {
                    Telefonia telefoniaExistente = (Telefonia) servicio;
                    telefoniaExistente.setName(nombreServicio);
                    telefoniaExistente.setPrecio(precioServicio);
                    telefoniaExistente.setTipoServicio(tipoServicio);
                    telefoniaExistente.setMinutosMaximos(Integer.parseInt(minutosMaximos.getValue()));
                    telefoniaExistente.setLlamadasMaximas(Integer.parseInt(llamadasMaximas.getValue()));
                    telefoniaService.UpdateTelefonia(telefoniaExistente);
                }
                break;
            case "Móvil":
                if (servicio instanceof Movil) {
                    Movil movilExistente = (Movil) servicio;
                    movilExistente.setName(nombreServicio);
                    movilExistente.setPrecio(precioServicio);
                    movilExistente.setTipoServicio(tipoServicio);
                    movilExistente.setMinutosMaximos(Integer.parseInt(minutosMaximosMovil.getValue()));
                    movilExistente.setLlamadasMaximas(Integer.parseInt(llamadasMaximasMovil.getValue()));
                    movilExistente.setDatosMaximosGB(Float.parseFloat(datosMaximos.getValue()));
                    movilService.UpdateMovil(movilExistente);
                }
                break;
            default:
                break;
        }

        Notification notification = new Notification("Servicio guardado correctamente");
        notification.setPosition(Notification.Position.TOP_CENTER);
        notification.setDuration(3000);
        notification.open();
    }

    @Override
    public void setParameter(BeforeEvent event, String uuid) {
        Optional<Servicio> maybeServicio = servicioService.findById(UUID.fromString(uuid));
        if (maybeServicio.isPresent()) {
            servicio = maybeServicio.get();

            nombreField.setValue(servicio.getName());
            precioField.setValue(String.valueOf(servicio.getPrecio()));
            tipoServicioComboBox.setValue(servicio.getTipoServicio());
            if (servicio instanceof Fibra) {
                velocidadContratadaField.setValue(String.valueOf(((Fibra) servicio).getVelocidadContratadaMb()));
            } else if (servicio instanceof Telefonia) {
                minutosMaximos.setValue(String.valueOf(((Telefonia) servicio).getMinutosMaximos()));
                llamadasMaximas.setValue(String.valueOf(((Telefonia) servicio).getLlamadasMaximas()));
                if(servicio instanceof Movil){
                    minutosMaximosMovil.setValue(String.valueOf(((Movil) servicio).getMinutosMaximos()));
                    llamadasMaximasMovil.setValue(String.valueOf(((Movil) servicio).getLlamadasMaximas()));
                    datosMaximos.setValue(String.valueOf(((Movil) servicio).getDatosMaximosGB()));
                }
            }
        } else {
            event.rerouteTo("");
        }
    }
}