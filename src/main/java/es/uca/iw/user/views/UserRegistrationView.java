package es.uca.iw.user.views;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import es.uca.iw.user.domain.User;
import es.uca.iw.user.services.UserManagementService;

import es.uca.iw.MainLayout;
import java.io.Serial;

@PageTitle("Registrate User")
@Route(value = "userregistration", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class UserRegistrationView extends VerticalLayout {

    @Serial
    private static final long serialVersionUID = 851217309689685413L;

    private final UserManagementService service;

    private final H1 title;

    private final TextField username;
    private final EmailField email;
    private final PasswordField password;
    private final PasswordField password2;


    private final Button register;
    private final H4 status;

    private final BeanValidationBinder<User> binder;

    public UserRegistrationView(UserManagementService service) {
        this.service = service;

        title = new H1("Registrate");
        Html textobienvenida = new Html ("<h4>Bienvenido a UFJMOVIL, por favor registrate</h4>");

        username = new TextField("Nombre de usuario:");
        username.setId("username");

        email = new EmailField("Direccion email:");
        email.setId("email");

        password = new PasswordField("Password");
        password.setId("password");

        password2 = new PasswordField("Repite Password");
        password2.setId("password2");

        register = new Button("Registrarse");
        register.setId("register");

        status = new H4();
        status.setId("status");
        status.setVisible(false);

        setMargin(true);

        add(title,textobienvenida, username, email, password, password2, register, status);

        register.addClickListener(e -> onRegisterButtonClick());

        binder = new BeanValidationBinder<>(User.class);
        binder.bindInstanceFields(this);

        binder.setBean(new User());
    }

    /**
     * Handler
     */
    public void onRegisterButtonClick() {

        if (binder.validate().isOk() & password.getValue().equals(password2.getValue())) {
            if (service.registerUser(binder.getBean())) {
                status.setText("Great. Please look at your mail inbox!");
                status.setVisible(true);
                binder.setBean(new User());
                password2.setValue("");
            } else {
                Notification.show("Please, the username is already in use");

            }


        } else {
            Notification.show("Please, check input data");
        }

    }

}
