package es.uca.iw.helloworld.views.helloworld;

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
import com.vaadin.flow.router.RouterLink;

import es.uca.iw.MainLayout;

@PageTitle("Hello World")
@Route(value = "hello", layout = MainLayout.class)
@RouteAlias(value = "hello", layout = MainLayout.class)
public class HelloWorldView extends VerticalLayout {

    //private TextField name;
    //private Button sayHello;

    public HelloWorldView() {
        H1 welcomeText = new H1("UFJMOVIL");
        Html welcomeText2 = new Html ("<h3>Tu compañia movil de confianza</h3>");
        //name = new TextField("Your name");
        //sayHello = new Button("Say hello");
        //sayHello.addClickListener(e -> {
          //  Notification.show("Hello " + name.getValue());
        //});
        //sayHello.addClickShortcut(Key.ENTER);

        RouterLink linkToOtherView = new RouterLink ("Registrate y forma parte de nuestra familia", es.uca.iw.user.views.UserRegistrationView.class);

        setMargin(true);
        //setVerticalComponentAlignment(Alignment.END, name, sayHello);

        add(welcomeText);
        add(welcomeText2);
        //add(name, sayHello);
        add(linkToOtherView);
        
    }

}
