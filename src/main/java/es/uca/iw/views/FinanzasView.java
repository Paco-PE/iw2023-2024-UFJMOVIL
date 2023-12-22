package es.uca.iw.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;

import es.uca.iw.MainLayout;
import es.uca.iw.domain.Cliente;
//import es.uca.iw.services.ClienteService;
import es.uca.iw.fakers.ClienteService;

@PageTitle("Finanzas")
@Route(value = "/finanzas", layout = MainLayout.class)
public class FinanzasView extends VerticalLayout {
    private ClienteService clienteService;

    public FinanzasView(){
        this.clienteService = new ClienteService();

        H2 title = new H2("Clientes");
        add(title);

        Grid<Cliente> grid = new Grid<>(Cliente.class);
        grid.setColumns("id", "username", "email");
        grid.setItems(clienteService.findAll());

       grid.addComponentColumn(cliente -> {
            Button button = new Button("Descargar factura");
            button.addClickListener(click -> {
                Notification.show("PDF GENERADO"); //cambiar por la generación del pdf
            });
            return button;
        }).setHeader("Factura");

        setSizeFull();
        grid.setSizeFull();

        add(grid);
    }
}