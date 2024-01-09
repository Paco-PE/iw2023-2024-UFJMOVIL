package es.uca.iw.views;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

import java.util.Optional;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

import es.uca.iw.MainLayout;
import es.uca.iw.domain.Contrato;
import es.uca.iw.services.ContratoService;
import jakarta.annotation.security.RolesAllowed;

@Route(value = "numeros-bloqueados", layout = MainLayout.class)
@RolesAllowed("CLIENTE")
public class NumerosBloqueadosView extends VerticalLayout implements HasUrlParameter<String>{
    private H2 title = new H2();
    private final ContratoService contratoService;
    private Contrato contrato;
    private Grid<String> grid;
    private TextField nuevoNumero;
    private Button addButton;

    public NumerosBloqueadosView(ContratoService contratoService) {
        this.contratoService = contratoService;

        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);
        
        grid = new Grid<>();
        grid.setWidthFull();
        grid.addColumn(String::toString).setHeader("Números bloqueados");
        grid.addComponentColumn(numeroBloqueado -> {
            Button button = new Button("Eliminar", clickEvent -> {
                contrato.getNumerosBloqueados().remove(numeroBloqueado);
                contratoService.save(contrato);
                updateGrid();
            });
            return button;
        }).setHeader("Acciones");
        
        Div gridContainer = new Div(grid);
        gridContainer.setWidth("25%");
        
        nuevoNumero = new TextField("Nuevo número a bloquear");
        addButton = new Button("Añadir", click -> addNumero());

        add(title, gridContainer, nuevoNumero, addButton);
    }

    private void updateGrid() {
        grid.setItems(contrato.getNumerosBloqueados());
    }

    private void addNumero() {
        if (contrato.addNumeroBloqueado(nuevoNumero.getValue())) Notification.show("Número añadido");
        else Notification.show("Número ya bloqueado");
        contratoService.save(contrato);
        nuevoNumero.clear();
        contrato = contratoService.findByNumeroTelefono(contrato.getNumeroTelefono()).orElseThrow(() -> new RuntimeException("Contrato no encontrado"));
        updateGrid();
    }
    
    @Override
    public void setParameter(BeforeEvent event, String numeroTelefono) {
        Optional<Contrato> maybeContrato = contratoService.findByNumeroTelefono(numeroTelefono);
        if(maybeContrato.isPresent()){
            contrato = maybeContrato.get();
            title.setText("Números bloqueados de " + contrato.getNumeroTelefono());
            updateGrid();
        }else{
            event.rerouteTo("");
        }
    }
}