package es.uca.iw.views.helloworld;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;


import es.uca.iw.views.MainLayout;

@PageTitle("Hello World")
@Route(value = "hello", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class HelloWorldView extends VerticalLayout {

    private TextField name;
    private Button sayHello;

    public HelloWorldView() {
        try {
            H1 welcomeText = new H1("UFJMOVIL");
            H1 welcomeText2 = new H1("Tu compañía telefónica de confianza");
            Html largeText = new Html("<span style='font-size: 24px;'>Conectando a la gente desde 2023</span>");
    
            name = new TextField("Your name");
            sayHello = new Button("Say hello");
            sayHello.addClickListener(e -> {
                Notification.show("Hello " + name.getValue());
            });
            sayHello.addClickShortcut(Key.ENTER);
    
            setMargin(true);
    
    
            add(welcomeText);
            add(welcomeText2);
            add(largeText);
            add(name, sayHello);
    
            System.out.println("Constructor ejecutado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            throw e; // Esto asegurará que la excepción se propague y se muestre en los logs.
        }
    }
    

}
