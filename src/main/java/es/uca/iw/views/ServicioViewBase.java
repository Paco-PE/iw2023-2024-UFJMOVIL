package es.uca.iw.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

import es.uca.iw.services.FibraService;
import es.uca.iw.services.MovilService;
import es.uca.iw.services.TelefoniaService;

public abstract class ServicioViewBase extends VerticalLayout {
    protected FibraService fibraService;
    protected TelefoniaService telefoniaService;
    protected MovilService movilService;
    protected TextField nombreField;
    protected TextField precioField;
    protected ComboBox<String> tipoServicioComboBox;
    protected TextField velocidadContratadaField;
    protected TextField minutosMaximos;
    protected TextField minutosMaximosMovil;
    protected TextField llamadasMaximas;
    protected TextField llamadasMaximasMovil;
    protected TextField datosMaximos;
    protected Button guardarButton;
}
