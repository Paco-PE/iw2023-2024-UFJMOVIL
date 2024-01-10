package es.uca.iw.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import es.uca.iw.MainLayout;
import es.uca.iw.domain.Fibra;
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
public class CreaServicioView extends ServicioViewBase{
    private TextField createTextField(String label) {
        return createTextField(label, true);
    }

    private TextField createTextField(String label, boolean visible) {
        TextField textField = new TextField(label);
        textField.setVisible(visible);
        return textField;
    }

    public CreaServicioView(ServicioService servicioService, FibraService fibraService, TelefoniaService telefoniaService, MovilService movilService) {
        this.fibraService = fibraService;
        this.telefoniaService = telefoniaService;
        this.movilService = movilService;

        nombreField = createTextField("Nombre del Servicio");
        precioField = createTextField("Precio del Servicio(€)");
        velocidadContratadaField = createTextField("Velocidad contratada (Mb/s)", false);
        velocidadContratadaField.setWidth("15%");
        minutosMaximos = createTextField("Minutos maximos permitidos", false);
        minutosMaximos.setWidth("20%");
        minutosMaximosMovil = createTextField("Minutos maximos permitidos", false);
        minutosMaximosMovil.setWidth("20%");
        llamadasMaximas = createTextField("Llamadas maximas permitidas", false);
        llamadasMaximas.setWidth("20%");
        llamadasMaximasMovil = createTextField("Llamadas maximas permitidas", false);
        llamadasMaximasMovil.setWidth("20%");
        datosMaximos = createTextField("Datos maximos permitidos", false);
        tipoServicioComboBox = new ComboBox<>("Tipo de Servicio");
        tipoServicioComboBox.setItems("Fibra", "Fijo", "Móvil");

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

        add(nombreField, precioField,tipoServicioComboBox,velocidadContratadaField,minutosMaximos,minutosMaximosMovil,llamadasMaximas,llamadasMaximasMovil,datosMaximos, guardarButton);
    }

    private void guardarServicio() {
        String nombreServicio = nombreField.getValue();
        Float precioServicio = Float.parseFloat(precioField.getValue());
        String tipoServicio = tipoServicioComboBox.getValue();

        switch (tipoServicio) {
            case "Fibra":
                Fibra nuevaFibra = new Fibra();
                nuevaFibra.setName(nombreServicio);
                nuevaFibra.setPrecio(precioServicio);
                nuevaFibra.setTipoServicio(tipoServicio);
                nuevaFibra.setVelocidadContratadaMb(Float.parseFloat(velocidadContratadaField.getValue()));
                fibraService.SaveFibra(nuevaFibra);
                break;
            case "Fijo":
                Telefonia nuevaTelefonia = new Telefonia();
                nuevaTelefonia.setName(nombreServicio);
                nuevaTelefonia.setPrecio(precioServicio);
                nuevaTelefonia.setTipoServicio(tipoServicio);
                nuevaTelefonia.setMinutosMaximos(Integer.parseInt(minutosMaximos.getValue()));
                nuevaTelefonia.setLlamadasMaximas(Integer.parseInt(llamadasMaximas.getValue()));
                telefoniaService.SaveTelefonia(nuevaTelefonia);
                break;
            case "Móvil":
                Movil nuevoMovil = new Movil();
                nuevoMovil.setName(nombreServicio);
                nuevoMovil.setPrecio(precioServicio);
                nuevoMovil.setTipoServicio(tipoServicio);
                nuevoMovil.setMinutosMaximos(Integer.parseInt(minutosMaximosMovil.getValue()));
                nuevoMovil.setLlamadasMaximas(Integer.parseInt(llamadasMaximasMovil.getValue()));
                nuevoMovil.setDatosMaximosGB(Integer.parseInt(datosMaximos.getValue()));
                movilService.SaveMovil(nuevoMovil);
                break;
            default:
                break;
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
    }
}