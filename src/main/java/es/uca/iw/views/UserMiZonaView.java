package es.uca.iw.views;

import es.uca.iw.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.HasValue.ValueChangeListener;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;

@PageTitle("Mi Zona")
@Route(value = "mi-zona", layout = MainLayout.class)
@RolesAllowed("CLIENTE")
@Uses(Icon.class)
public class UserMiZonaView extends Composite<VerticalLayout> {

    private Button ContactaButton = new Button("Contacta con nosotros");

    public UserMiZonaView() {
        H2 txtServiciosOfertados = new H2();
        HorizontalLayout layoutRow = new HorizontalLayout();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        H2 txtFibra = new H2();
        HorizontalLayout layoutRow2 = new HorizontalLayout();
        HorizontalLayout layoutRow3 = new HorizontalLayout();
        H3 txtFibraOpcion1 = new H3();
        Checkbox checkboxFibra1 = new Checkbox();
        HorizontalLayout layoutRow4 = new HorizontalLayout();
        H3 txtFibraOpcion2 = new H3();
        Checkbox checkboxFibra2 = new Checkbox();
        HorizontalLayout layoutRow5 = new HorizontalLayout();
        H3 txtFibraOpcion3 = new H3();
        Checkbox checkboxFibra3 = new Checkbox();
        HorizontalLayout layoutRow6 = new HorizontalLayout();
        H3 txtFibraOpcion4 = new H3();
        Checkbox checkboxFibra4 = new Checkbox();
        VerticalLayout layoutColumn3 = new VerticalLayout();
        H2 txtMovil = new H2();
        HorizontalLayout layoutRow7 = new HorizontalLayout();
        VerticalLayout layoutColumn4 = new VerticalLayout();
        HorizontalLayout layoutRow8 = new HorizontalLayout();
        H3 h35 = new H3();
        Checkbox checkboxMovilDatos1 = new Checkbox();
        HorizontalLayout layoutRow9 = new HorizontalLayout();
        H3 h36 = new H3();
        Checkbox checkboxMovilDatos2 = new Checkbox();
        HorizontalLayout layoutRow10 = new HorizontalLayout();
        H3 h37 = new H3();
        Checkbox checkboxMovilDatos3 = new Checkbox();
        HorizontalLayout layoutRow11 = new HorizontalLayout();
        H3 h38 = new H3();
        Checkbox checkboxMovilDatos4 = new Checkbox();
        VerticalLayout layoutColumn5 = new VerticalLayout();
        HorizontalLayout layoutRow12 = new HorizontalLayout();
        H3 h39 = new H3();
        Checkbox checkboxMovilMinutos1 = new Checkbox();
        HorizontalLayout layoutRow13 = new HorizontalLayout();
        H3 h310 = new H3();
        Checkbox checkboxMovilMinutos2 = new Checkbox();
        HorizontalLayout layoutRow14 = new HorizontalLayout();
        H3 h311 = new H3();
        Checkbox checkboxMovilMinutos3 = new Checkbox();
        HorizontalLayout layoutRow15 = new HorizontalLayout();
        H3 h312 = new H3();
        Checkbox checkboxMovilMinutos4 = new Checkbox();
        VerticalLayout layoutColumn6 = new VerticalLayout();
        H2 h24 = new H2();
        HorizontalLayout layoutRow16 = new HorizontalLayout();
        HorizontalLayout layoutRow17 = new HorizontalLayout();
        H3 h313 = new H3();
        Checkbox checkboxFijo1 = new Checkbox();
        HorizontalLayout layoutRow18 = new HorizontalLayout();
        H3 h314 = new H3();
        Checkbox checkboxFijo2 = new Checkbox();
        HorizontalLayout layoutRow19 = new HorizontalLayout();
        H3 h315 = new H3();
        Checkbox checkboxFijo3 = new Checkbox();
        HorizontalLayout layoutRow20 = new HorizontalLayout();
        H3 h316 = new H3();
        Checkbox checkboxFijo4 = new Checkbox();
        H3 h317 = new H3();
        Button buttonPrimary = new Button();
        Hr hr = new Hr();
        H2 h25 = new H2();
        Hr hr2 = new Hr();
        H2 h26 = new H2();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        txtServiciosOfertados.setText("Servicios ofertados");
        txtServiciosOfertados.setWidth("max-content");
        layoutRow.setWidthFull();
        getContent().setFlexGrow(1.0, layoutRow);
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.setHeight("min-content");
        layoutColumn2.setHeightFull();
        layoutRow.setFlexGrow(1.0, layoutColumn2);
        layoutColumn2.setWidth("100%");
        layoutColumn2.setHeight("100%");
        layoutColumn2.setJustifyContentMode(JustifyContentMode.START);
        layoutColumn2.setAlignItems(Alignment.START);
        txtFibra.setText("Fibra");
        layoutColumn2.setAlignSelf(FlexComponent.Alignment.CENTER, txtFibra);
        txtFibra.setWidth("max-content");
        layoutRow2.setWidthFull();
        layoutColumn2.setFlexGrow(1.0, layoutRow2);
        layoutRow2.addClassName(Gap.MEDIUM);
        layoutRow2.setWidth("100%");
        layoutRow2.getStyle().set("flex-grow", "1");
        layoutRow3.setHeightFull();
        layoutRow2.setFlexGrow(1.0, layoutRow3);
        layoutRow3.addClassName(Gap.MEDIUM);
        layoutRow3.setWidth("100%");
        layoutRow3.getStyle().set("flex-grow", "1");
        layoutRow3.setAlignItems(Alignment.CENTER);
        layoutRow3.setJustifyContentMode(JustifyContentMode.CENTER);
        txtFibraOpcion1.setText("1000 Mbs");
        layoutRow3.setAlignSelf(FlexComponent.Alignment.CENTER, txtFibraOpcion1);
        txtFibraOpcion1.setWidth("max-content");
        checkboxFibra1.setLabel("Contratado");
        layoutRow3.setAlignSelf(FlexComponent.Alignment.CENTER, checkboxFibra1);
        checkboxFibra1.setWidth("min-content");
        layoutRow4.setWidthFull();
        layoutColumn2.setFlexGrow(1.0, layoutRow4);
        layoutRow4.addClassName(Gap.MEDIUM);
        layoutRow4.setWidth("100%");
        layoutRow4.getStyle().set("flex-grow", "1");
        layoutRow4.setAlignItems(Alignment.CENTER);
        layoutRow4.setJustifyContentMode(JustifyContentMode.CENTER);
        txtFibraOpcion2.setText("500 Mbs");
        txtFibraOpcion2.setWidth("max-content");
        checkboxFibra2.setLabel("Contratado");
        checkboxFibra2.setWidth("min-content");
        layoutRow5.setWidthFull();
        layoutColumn2.setFlexGrow(1.0, layoutRow5);
        layoutRow5.addClassName(Gap.MEDIUM);
        layoutRow5.setWidth("100%");
        layoutRow5.getStyle().set("flex-grow", "1");
        layoutRow5.setAlignItems(Alignment.CENTER);
        layoutRow5.setJustifyContentMode(JustifyContentMode.CENTER);
        txtFibraOpcion3.setText("300 Mbs");
        txtFibraOpcion3.setWidth("max-content");
        checkboxFibra3.setLabel("Contratado");
        checkboxFibra3.setWidth("min-content");
        layoutRow6.setWidthFull();
        layoutColumn2.setFlexGrow(1.0, layoutRow6);
        layoutRow6.addClassName(Gap.MEDIUM);
        layoutRow6.setWidth("100%");
        layoutRow6.getStyle().set("flex-grow", "1");
        layoutRow6.setAlignItems(Alignment.CENTER);
        layoutRow6.setJustifyContentMode(JustifyContentMode.CENTER);
        txtFibraOpcion4.setText("100 Mbs");
        txtFibraOpcion4.setWidth("max-content");
        checkboxFibra4.setLabel("Contratado");
        checkboxFibra4.setWidth("min-content");
        layoutColumn3.setHeightFull();
        layoutRow.setFlexGrow(1.0, layoutColumn3);
        layoutColumn3.setWidth("100%");
        layoutColumn3.setHeight("100%");
        layoutColumn3.setJustifyContentMode(JustifyContentMode.START);
        layoutColumn3.setAlignItems(Alignment.START);
        txtMovil.setText("Movil");
        layoutColumn3.setAlignSelf(FlexComponent.Alignment.CENTER, txtMovil);
        txtMovil.setWidth("max-content");
        layoutRow7.setWidthFull();
        layoutColumn3.setFlexGrow(1.0, layoutRow7);
        layoutRow7.addClassName(Gap.MEDIUM);
        layoutRow7.setWidth("100%");
        layoutRow7.getStyle().set("flex-grow", "1");
        layoutColumn4.setHeightFull();
        layoutRow7.setFlexGrow(1.0, layoutColumn4);
        layoutColumn4.setWidth("100%");
        layoutColumn4.getStyle().set("flex-grow", "1");
        layoutRow8.setWidthFull();
        layoutColumn4.setFlexGrow(1.0, layoutRow8);
        layoutRow8.addClassName(Gap.MEDIUM);
        layoutRow8.setWidth("100%");
        layoutRow8.getStyle().set("flex-grow", "1");
        layoutRow8.setAlignItems(Alignment.CENTER);
        layoutRow8.setJustifyContentMode(JustifyContentMode.CENTER);
        h35.setText("Datos Ilimitados");
        h35.setWidth("100px");
        checkboxMovilDatos1.setLabel("Contratado");
        layoutRow8.setAlignSelf(FlexComponent.Alignment.CENTER, checkboxMovilDatos1);
        checkboxMovilDatos1.setWidth("min-content");
        layoutRow9.setWidthFull();
        layoutColumn4.setFlexGrow(1.0, layoutRow9);
        layoutRow9.addClassName(Gap.MEDIUM);
        layoutRow9.setWidth("100%");
        layoutRow9.getStyle().set("flex-grow", "1");
        layoutRow9.setAlignItems(Alignment.CENTER);
        layoutRow9.setJustifyContentMode(JustifyContentMode.CENTER);
        h36.setText("500 Mbs");
        h36.setWidth("max-content");
        checkboxMovilDatos2.setLabel("Contratado");
        checkboxMovilDatos2.setWidth("min-content");
        layoutRow10.setWidthFull();
        layoutColumn4.setFlexGrow(1.0, layoutRow10);
        layoutRow10.addClassName(Gap.MEDIUM);
        layoutRow10.setWidth("100%");
        layoutRow10.getStyle().set("flex-grow", "1");
        layoutRow10.setAlignItems(Alignment.CENTER);
        layoutRow10.setJustifyContentMode(JustifyContentMode.CENTER);
        h37.setText("300 Mbs");
        h37.setWidth("max-content");
        checkboxMovilDatos3.setLabel("Contratado");
        checkboxMovilDatos3.setWidth("min-content");
        layoutRow11.setWidthFull();
        layoutColumn4.setFlexGrow(1.0, layoutRow11);
        layoutRow11.addClassName(Gap.MEDIUM);
        layoutRow11.setWidth("100%");
        layoutRow11.getStyle().set("flex-grow", "1");
        layoutRow11.setAlignItems(Alignment.CENTER);
        layoutRow11.setJustifyContentMode(JustifyContentMode.CENTER);
        h38.setText("100 Mbs");
        h38.setWidth("max-content");
        checkboxMovilDatos4.setLabel("Contratado");
        checkboxMovilDatos4.setWidth("min-content");
        layoutColumn5.setHeightFull();
        layoutRow7.setFlexGrow(1.0, layoutColumn5);
        layoutColumn5.setWidth("100%");
        layoutColumn5.getStyle().set("flex-grow", "1");
        layoutRow12.setWidthFull();
        layoutColumn5.setFlexGrow(1.0, layoutRow12);
        layoutRow12.addClassName(Gap.MEDIUM);
        layoutRow12.setWidth("100%");
        layoutRow12.getStyle().set("flex-grow", "1");
        layoutRow12.setAlignItems(Alignment.CENTER);
        layoutRow12.setJustifyContentMode(JustifyContentMode.CENTER);
        h39.setText("Minutos Ilimitados");
        layoutRow12.setAlignSelf(FlexComponent.Alignment.CENTER, h39);
        h39.setWidth("100px");
        checkboxMovilMinutos1.setLabel("Contratado");
        layoutRow12.setAlignSelf(FlexComponent.Alignment.CENTER, checkboxMovilMinutos1);
        checkboxMovilMinutos1.setWidth("min-content");
        layoutRow13.setWidthFull();
        layoutColumn5.setFlexGrow(1.0, layoutRow13);
        layoutRow13.addClassName(Gap.MEDIUM);
        layoutRow13.setWidth("100%");
        layoutRow13.getStyle().set("flex-grow", "1");
        layoutRow13.setAlignItems(Alignment.CENTER);
        layoutRow13.setJustifyContentMode(JustifyContentMode.CENTER);
        h310.setText("1000 min");
        h310.setWidth("max-content");
        checkboxMovilMinutos2.setLabel("Contratado");
        checkboxMovilMinutos2.setWidth("min-content");
        layoutRow14.setWidthFull();
        layoutColumn5.setFlexGrow(1.0, layoutRow14);
        layoutRow14.addClassName(Gap.MEDIUM);
        layoutRow14.setWidth("100%");
        layoutRow14.getStyle().set("flex-grow", "1");
        layoutRow14.setAlignItems(Alignment.CENTER);
        layoutRow14.setJustifyContentMode(JustifyContentMode.CENTER);
        h311.setText("500 min");
        h311.setWidth("max-content");
        checkboxMovilMinutos3.setLabel("Contratado");
        checkboxMovilMinutos3.setWidth("min-content");
        layoutRow15.setWidthFull();
        layoutColumn5.setFlexGrow(1.0, layoutRow15);
        layoutRow15.addClassName(Gap.MEDIUM);
        layoutRow15.setWidth("100%");
        layoutRow15.getStyle().set("flex-grow", "1");
        layoutRow15.setAlignItems(Alignment.CENTER);
        layoutRow15.setJustifyContentMode(JustifyContentMode.CENTER);
        h312.setText("100 min");
        h312.setWidth("max-content");
        checkboxMovilMinutos4.setLabel("Contratado");
        checkboxMovilMinutos4.setWidth("min-content");
        layoutColumn6.setHeightFull();
        layoutRow.setFlexGrow(1.0, layoutColumn6);
        layoutColumn6.setWidth("100%");
        layoutColumn6.setHeight("100%");
        layoutColumn6.setJustifyContentMode(JustifyContentMode.START);
        layoutColumn6.setAlignItems(Alignment.START);
        h24.setText("Fijo");
        layoutColumn6.setAlignSelf(FlexComponent.Alignment.CENTER, h24);
        h24.setWidth("max-content");
        layoutRow16.setWidthFull();
        layoutColumn6.setFlexGrow(1.0, layoutRow16);
        layoutRow16.addClassName(Gap.MEDIUM);
        layoutRow16.setWidth("100%");
        layoutRow16.getStyle().set("flex-grow", "1");
        layoutRow17.setHeightFull();
        layoutRow16.setFlexGrow(1.0, layoutRow17);
        layoutRow17.addClassName(Gap.MEDIUM);
        layoutRow17.setWidth("100%");
        layoutRow17.getStyle().set("flex-grow", "1");
        layoutRow17.setAlignItems(Alignment.CENTER);
        layoutRow17.setJustifyContentMode(JustifyContentMode.CENTER);
        h313.setText("Llamadas Ilimitadas");
        layoutRow17.setAlignSelf(FlexComponent.Alignment.CENTER, h313);
        h313.setWidth("100px");
        checkboxFijo1.setLabel("Contratado");
        layoutRow17.setAlignSelf(FlexComponent.Alignment.CENTER, checkboxFijo1);
        checkboxFijo1.setWidth("min-content");
        layoutRow18.setWidthFull();
        layoutColumn6.setFlexGrow(1.0, layoutRow18);
        layoutRow18.addClassName(Gap.MEDIUM);
        layoutRow18.setWidth("100%");
        layoutRow18.getStyle().set("flex-grow", "1");
        layoutRow18.setAlignItems(Alignment.CENTER);
        layoutRow18.setJustifyContentMode(JustifyContentMode.CENTER);
        h314.setText("1000 min");
        h314.setWidth("max-content");
        checkboxFijo2.setLabel("Contratado");
        checkboxFijo2.setWidth("min-content");
        layoutRow19.setWidthFull();
        layoutColumn6.setFlexGrow(1.0, layoutRow19);
        layoutRow19.addClassName(Gap.MEDIUM);
        layoutRow19.setWidth("100%");
        layoutRow19.getStyle().set("flex-grow", "1");
        layoutRow19.setAlignItems(Alignment.CENTER);
        layoutRow19.setJustifyContentMode(JustifyContentMode.CENTER);
        h315.setText("500 min");
        h315.setWidth("max-content");
        checkboxFijo3.setLabel("Contratado");
        checkboxFijo3.setWidth("min-content");
        layoutRow20.setWidthFull();
        layoutColumn6.setFlexGrow(1.0, layoutRow20);
        layoutRow20.addClassName(Gap.MEDIUM);
        layoutRow20.setWidth("100%");
        layoutRow20.getStyle().set("flex-grow", "1");
        layoutRow20.setAlignItems(Alignment.CENTER);
        layoutRow20.setJustifyContentMode(JustifyContentMode.CENTER);
        h316.setText("100 min");
        h316.setWidth("max-content");
        checkboxFijo4.setLabel("Contratado");
        checkboxFijo4.setWidth("min-content");
        h317.setText("100€ al mes");
        getContent().setAlignSelf(FlexComponent.Alignment.CENTER, h317);
        getContent().setAlignSelf(FlexComponent.Alignment.CENTER, ContactaButton);
        h317.setWidth("max-content");
        buttonPrimary.setText("Actualizar contrato");
        buttonPrimary.setEnabled(false); // Deshabilitar el botón al principio
        buttonPrimary.getStyle().set("background-color", "grey"); // Cambiar el color a gris
        getContent().setAlignSelf(FlexComponent.Alignment.CENTER, buttonPrimary);
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonPrimary.addClickListener(event -> {
            Notification.show("Su contrato se ha actualizado");
        });
        // Añadir ValueChangeListeners a los checkbox
        ValueChangeListener<HasValue.ValueChangeEvent<Boolean>> listener = event -> {
            buttonPrimary.setEnabled(true); // Habilitar el botón cuando el valor del checkbox cambie
            buttonPrimary.getStyle().set("background-color", "blue"); // Cambiar el color a azul
        };
        checkboxFibra1.addValueChangeListener(listener);
        checkboxFibra2.addValueChangeListener(listener);
        checkboxFibra3.addValueChangeListener(listener);
        checkboxFibra4.addValueChangeListener(listener);
        checkboxMovilDatos1.addValueChangeListener(listener);
        checkboxMovilDatos2.addValueChangeListener(listener);
        checkboxMovilDatos3.addValueChangeListener(listener);
        checkboxMovilDatos4.addValueChangeListener(listener);
        checkboxMovilMinutos1.addValueChangeListener(listener);
        checkboxMovilMinutos2.addValueChangeListener(listener);
        checkboxMovilMinutos3.addValueChangeListener(listener);
        checkboxMovilMinutos4.addValueChangeListener(listener);
        checkboxFijo1.addValueChangeListener(listener);
        checkboxFijo2.addValueChangeListener(listener);
        checkboxFijo3.addValueChangeListener(listener);
        checkboxFijo4.addValueChangeListener(listener);
        h25.setText("Consumo de datos 5G");
        h25.setWidth("max-content");
        h26.setText("Desglose de llamadas");
        h26.setWidth("max-content");

        // Hacer que los checkbox sean mutuamente excluyentes
        checkboxFibra1.addValueChangeListener(event -> {
            if (event.getValue()) {
                checkboxFibra2.setValue(false);
                checkboxFibra3.setValue(false);
                checkboxFibra4.setValue(false);
            }
        });
        checkboxFibra2.addValueChangeListener(event -> {
            if (event.getValue()) {
                checkboxFibra1.setValue(false);
                checkboxFibra3.setValue(false);
                checkboxFibra4.setValue(false);
            }
        });
        
        checkboxFibra3.addValueChangeListener(event -> {
            if (event.getValue()) {
                checkboxFibra1.setValue(false);
                checkboxFibra2.setValue(false);
                checkboxFibra4.setValue(false);
            }
        });
        
        checkboxFibra4.addValueChangeListener(event -> {
            if (event.getValue()) {
                checkboxFibra1.setValue(false);
                checkboxFibra2.setValue(false);
                checkboxFibra3.setValue(false);
            }
        });

        checkboxMovilDatos1.addValueChangeListener(event -> {
            if (event.getValue()) {
                checkboxMovilDatos2.setValue(false);
                checkboxMovilDatos3.setValue(false);
                checkboxMovilDatos4.setValue(false);
            }
        });
        
        checkboxMovilDatos2.addValueChangeListener(event -> {
            if (event.getValue()) {
                checkboxMovilDatos1.setValue(false);
                checkboxMovilDatos3.setValue(false);
                checkboxMovilDatos4.setValue(false);
            }
        });
        
        checkboxMovilDatos3.addValueChangeListener(event -> {
            if (event.getValue()) {
                checkboxMovilDatos1.setValue(false);
                checkboxMovilDatos2.setValue(false);
                checkboxMovilDatos4.setValue(false);
            }
        });
        
        checkboxMovilDatos4.addValueChangeListener(event -> {
            if (event.getValue()) {
                checkboxMovilDatos1.setValue(false);
                checkboxMovilDatos2.setValue(false);
                checkboxMovilDatos3.setValue(false);
            }
        });

        checkboxMovilMinutos1.addValueChangeListener(event -> {
            if (event.getValue()) {
                checkboxMovilMinutos2.setValue(false);
                checkboxMovilMinutos3.setValue(false);
                checkboxMovilMinutos4.setValue(false);
            }
        });
        
        checkboxMovilMinutos2.addValueChangeListener(event -> {
            if (event.getValue()) {
                checkboxMovilMinutos1.setValue(false);
                checkboxMovilMinutos3.setValue(false);
                checkboxMovilMinutos4.setValue(false);
            }
        });
        
        checkboxMovilMinutos3.addValueChangeListener(event -> {
            if (event.getValue()) {
                checkboxMovilMinutos1.setValue(false);
                checkboxMovilMinutos2.setValue(false);
                checkboxMovilMinutos4.setValue(false);
            }
        });
        
        checkboxMovilMinutos4.addValueChangeListener(event -> {
            if (event.getValue()) {
                checkboxMovilMinutos1.setValue(false);
                checkboxMovilMinutos2.setValue(false);
                checkboxMovilMinutos3.setValue(false);
            }
        });

        checkboxFijo1.addValueChangeListener(event -> {
            if (event.getValue()) {
                checkboxFijo2.setValue(false);
                checkboxFijo3.setValue(false);
                checkboxFijo4.setValue(false);
            }
        });
        
        checkboxFijo2.addValueChangeListener(event -> {
            if (event.getValue()) {
                checkboxFijo1.setValue(false);
                checkboxFijo3.setValue(false);
                checkboxFijo4.setValue(false);
            }
        });
        
        checkboxFijo3.addValueChangeListener(event -> {
            if (event.getValue()) {
                checkboxFijo1.setValue(false);
                checkboxFijo2.setValue(false);
                checkboxFijo4.setValue(false);
            }
        });
        
        checkboxFijo4.addValueChangeListener(event -> {
            if (event.getValue()) {
                checkboxFijo1.setValue(false);
                checkboxFijo2.setValue(false);
                checkboxFijo3.setValue(false);
            }
        });

        getContent().add(txtServiciosOfertados);
        getContent().add(layoutRow);
        layoutRow.add(layoutColumn2);
        layoutColumn2.add(txtFibra);
        layoutColumn2.add(layoutRow2);
        layoutRow2.add(layoutRow3);
        layoutRow3.add(txtFibraOpcion1);
        layoutRow3.add(checkboxFibra1);
        layoutColumn2.add(layoutRow4);
        layoutRow4.add(txtFibraOpcion2);
        layoutRow4.add(checkboxFibra2);
        layoutColumn2.add(layoutRow5);
        layoutRow5.add(txtFibraOpcion3);
        layoutRow5.add(checkboxFibra3);
        layoutColumn2.add(layoutRow6);
        layoutRow6.add(txtFibraOpcion4);
        layoutRow6.add(checkboxFibra4);
        layoutRow.add(layoutColumn3);
        layoutColumn3.add(txtMovil);
        layoutColumn3.add(layoutRow7);
        layoutRow7.add(layoutColumn4);
        layoutColumn4.add(layoutRow8);
        layoutRow8.add(h35);
        layoutRow8.add(checkboxMovilDatos1);
        layoutColumn4.add(layoutRow9);
        layoutRow9.add(h36);
        layoutRow9.add(checkboxMovilDatos2);
        layoutColumn4.add(layoutRow10);
        layoutRow10.add(h37);
        layoutRow10.add(checkboxMovilDatos3);
        layoutColumn4.add(layoutRow11);
        layoutRow11.add(h38);
        layoutRow11.add(checkboxMovilDatos4);
        layoutRow7.add(layoutColumn5);
        layoutColumn5.add(layoutRow12);
        layoutRow12.add(h39);
        layoutRow12.add(checkboxMovilMinutos1);
        layoutColumn5.add(layoutRow13);
        layoutRow13.add(h310);
        layoutRow13.add(checkboxMovilMinutos2);
        layoutColumn5.add(layoutRow14);
        layoutRow14.add(h311);
        layoutRow14.add(checkboxMovilMinutos3);
        layoutColumn5.add(layoutRow15);
        layoutRow15.add(h312);
        layoutRow15.add(checkboxMovilMinutos4);
        layoutRow.add(layoutColumn6);
        layoutColumn6.add(h24);
        layoutColumn6.add(layoutRow16);
        layoutRow16.add(layoutRow17);
        layoutRow17.add(h313);
        layoutRow17.add(checkboxFijo1);
        layoutColumn6.add(layoutRow18);
        layoutRow18.add(h314);
        layoutRow18.add(checkboxFijo2);
        layoutColumn6.add(layoutRow19);
        layoutRow19.add(h315);
        layoutRow19.add(checkboxFijo3);
        layoutColumn6.add(layoutRow20);
        layoutRow20.add(h316);
        layoutRow20.add(checkboxFijo4);
        getContent().add(h317);
        getContent().add(ContactaButton);
        getContent().add(buttonPrimary);
        getContent().add(hr);
        getContent().add(h25);
        getContent().add(hr2);
        getContent().add(h26);

        ContactaButton.addClickListener(event -> {
        UI.getCurrent().navigate("/consultas");
    });
       
    }
}