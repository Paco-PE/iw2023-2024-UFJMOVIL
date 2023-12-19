package es.uca.iw.views;




import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


import es.uca.iw.MainLayout;


@PageTitle("Finanzas")
@Route(value = "/finanzas", layout = MainLayout.class)
public class FinanzasView extends VerticalLayout {
    private Button generarbutton = new Button("Generar nueva factura");

    public FinanzasView(){
        H1 welcomeText = new H1("UFJMOVIL");
        H2 welcomeText2 = new H2("Bienvenido, departamento de finanzas");

        VerticalLayout layoutcolumn = new VerticalLayout();
        layoutcolumn.setWidthFull();
        layoutcolumn.setAlignSelf(FlexComponent.Alignment.CENTER,welcomeText);
        layoutcolumn.setAlignSelf(FlexComponent.Alignment.CENTER,welcomeText2);
         layoutcolumn.setAlignSelf(FlexComponent.Alignment.CENTER, generarbutton);

        add(welcomeText);
        add(welcomeText2);
        add(generarbutton);

        generarbutton.addClickListener(event -> {
        UI.getCurrent().navigate("/facturanueva");
    });
    }

    
}
