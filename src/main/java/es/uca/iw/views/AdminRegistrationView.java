package es.uca.iw.views;

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
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.component.combobox.ComboBox;

import es.uca.iw.services.UserDetailsServiceImpl;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import es.uca.iw.MainLayout;
import es.uca.iw.domain.Role;
import es.uca.iw.domain.User;

import java.io.Serial;

@PageTitle("Registrate User")
@Route(value = "admin/registration", layout = MainLayout.class)
@RolesAllowed("ADMINISTRADOR")
public class AdminRegistrationView extends VerticalLayout {

    @Serial
    private static final long serialVersionUID = 851217309689685413L;

    private final UserDetailsServiceImpl service;

    private final H1 title;

    private final TextField username;
    private final EmailField email;
    private final PasswordField hashedPassword;
    private final PasswordField password2;

    private final ComboBox<Role> roleComboBox;

    private final Button register;
    private final H4 status;

    private final BeanValidationBinder<User> binder;

    public AdminRegistrationView(UserDetailsServiceImpl service) {
        this.service = service;

        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);

        title = new H1("Registro de usuarios");

        username = new TextField("Nombre de usuario:");
        username.setId("username");

        email = new EmailField("Direccion email:");
        email.setId("email");

        roleComboBox = new ComboBox<>("Rol del usuario");
        roleComboBox.setId("role");
        roleComboBox.setItems(Role.values());

        hashedPassword = new PasswordField("Contraseña");
        hashedPassword.setId("password");

        password2 = new PasswordField("Repite Contraseña");
        password2.setId("password2");

        register = new Button("Registrarse");
        register.setId("register");

        status = new H4();
        status.setId("status");
        status.setVisible(false);

        setMargin(true);

        add(title, username, email, roleComboBox, hashedPassword, password2, register, status);

        register.addClickListener(e -> onRegisterButtonClick());

        binder = new BeanValidationBinder<>(User.class);
        binder.bindInstanceFields(this);

        binder.setBean(new User());
    }

    /**
     * Handler
     */
    public void onRegisterButtonClick() {

        if (binder.validate().isOk() & hashedPassword.getValue().equals(password2.getValue())) {
            if (service.registerUser(binder.getBean(), roleComboBox.getValue())) {
                status.setText("Enhorabuena, ya formas parte de nuestra familia");
                status.setVisible(true);
                binder.setBean(new User());
                password2.setValue("");
            } else {
                Notification.show("El usuario ya esta en uso");
            }

        } else {
            Notification.show("Por favor, rellene correctamente todos los campos");
        }

    }

}
