package es.uca.iw.views;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import es.uca.iw.MainLayout;
import jakarta.annotation.security.PermitAll;

@PageTitle("Inicio")
@Route(value = "", layout = MainLayout.class)
@PermitAll
@AnonymousAllowed
public class InicioView extends VerticalLayout{

    public InicioView(){

        H1 welcomeText = new H1("UFJMOVIL");
        H2 welcomeText2 = new H2("Tu compañía telefónica de confianza");

        VerticalLayout layoutcolumn = new VerticalLayout();
        layoutcolumn.setWidthFull();
        layoutcolumn.setAlignSelf(FlexComponent.Alignment.CENTER,welcomeText);
        layoutcolumn.setAlignSelf(FlexComponent.Alignment.CENTER,welcomeText2);

        add(welcomeText);
        add(welcomeText2);

    }
}