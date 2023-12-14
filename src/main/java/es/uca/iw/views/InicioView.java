package es.uca.iw.views;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.theme.lumo.LumoUtility.JustifyContent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;


import es.uca.iw.MainLayout;

@PageTitle("Inicio")
@Route(value = "inicio", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class InicioView extends VerticalLayout{
    
    private Button loginButton = new Button("Iniciar sesión");
    private Button registerButton = new Button("Registrarse");

    public InicioView(){


    H1 welcomeText = new H1("UFJMOVIL");
    H2 welcomeText2 = new H2("Tu compañía telefónica de confianza");

    VerticalLayout layoutcolumn = new VerticalLayout();
    layoutcolumn.setWidthFull();
    layoutcolumn.setAlignSelf(FlexComponent.Alignment.END, loginButton);
    layoutcolumn.setAlignSelf(FlexComponent.Alignment.END, registerButton);
    layoutcolumn.setAlignSelf(FlexComponent.Alignment.CENTER,welcomeText);
    layoutcolumn.setAlignSelf(FlexComponent.Alignment.CENTER,welcomeText2);
    
    
    add(loginButton);
    add(registerButton);
    add(welcomeText);
    add(welcomeText2);

    loginButton.addClickListener(event -> {
        UI.getCurrent().navigate("user/login");
    });

    registerButton.addClickListener(event -> {
        UI.getCurrent().navigate("user/register");
    });

    


    }
}