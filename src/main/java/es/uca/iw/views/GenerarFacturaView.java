package es.uca.iw.views;


import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import es.uca.iw.MainLayout;

@PageTitle("Generar Factura")
@Route(value = "/generarfactura/:id", layout = MainLayout.class)
public class GenerarFacturaView extends VerticalLayout implements HasUrlParameter<String>{
    private String id;

    public GenerarFacturaView(){
        H1 welcomeText = new H1("UFJMOVIL");
        H2 welcomeText2 = new H2("Aquí se podra generar y enviar facturas");

        VerticalLayout layoutcolumn = new VerticalLayout();
        layoutcolumn.setWidthFull();
        layoutcolumn.setAlignSelf(FlexComponent.Alignment.CENTER,welcomeText);
        layoutcolumn.setAlignSelf(FlexComponent.Alignment.CENTER,welcomeText2);

        add(welcomeText);
        add(welcomeText2);
    }

    @Override
    public void setParameter(BeforeEvent event, String parameter) {
        this.id = parameter;
        H2 uuidText = new H2("UUID: " + id);
        add(uuidText);
    }
    
}
