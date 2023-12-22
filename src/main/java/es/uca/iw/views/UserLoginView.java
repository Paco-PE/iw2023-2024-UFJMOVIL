package es.uca.iw.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.iw.domain.User;
import es.uca.iw.services.UserService;

import es.uca.iw.MainLayout;

import java.io.Serial;

@PageTitle("Iniciar Sesion")
@Route(value = "user/login", layout = MainLayout.class)
public class UserLoginView extends VerticalLayout {

    @Serial
    private static final long serialVersionUID = 851217309689685413L;

    private final UserService service;

    private final H1 title;

    private final TextField username;
    private final PasswordField password;

    private final Button login;
    private final H4 status;

    private final BeanValidationBinder<User> binder;

    public UserLoginView(UserService service) {
        this.service = service;

        title = new H1("Iniciar Sesion");

        username = new TextField("Nombre de usuario:");
        username.setId("username");

        password = new PasswordField("Password");
        password.setId("password");

        login = new Button("Iniciar Sesion");
        login.setId("login");

        status = new H4();
        status.setId("status");
        status.setVisible(false);

        setMargin(true);

        add(title, username, password, login, status);

        login.addClickListener(e -> onLoginButtonClick());

        binder = new BeanValidationBinder<>(User.class);
        binder.bindInstanceFields(this);

        binder.setBean(new User());
    }

    /**
     * Handler
     */
    public void onLoginButtonClick() {

        if (binder.validate().isOk()) {
            if (service.loginUser(binder.getBean())) {
                status.setText("Bienvenido de nuevo");
                status.setVisible(true);
                binder.setBean(new User());
                password.setValue("");
                UI.getCurrent().navigate("mi-zona");

            } else {
                Notification.show("El usuario o la contraseña no es correcta");
            }

        } else {
            Notification.show("El usuario o la contraseña no es correcta");
        }

    }

    

}
