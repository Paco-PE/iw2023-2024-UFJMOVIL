package es.uca.iw.views;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import es.uca.iw.MainLayout;
import es.uca.iw.domain.User;
import es.uca.iw.security.AuthenticatedUser;
import es.uca.iw.services.UserDetailsServiceImpl;
import jakarta.annotation.security.PermitAll;

@Route(value = "user/edit", layout = MainLayout.class)
@PermitAll
public class UserModifyView extends VerticalLayout {
    private final H2 title = new H2("Modificar datos personales");
    private TextField username = new TextField("Nombre de usuario");
    private TextField email = new TextField("Correo electrónico");
    private PasswordField passwordOld = new PasswordField("Antigua contraseña");
    private PasswordField passwordNew = new PasswordField("Nueva contraseña");
    private Button save = new Button("Guardar");

    public UserModifyView(AuthenticatedUser authenticatedUser, UserDetailsServiceImpl userService, PasswordEncoder passwordEncoder) {
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);
        add(title, username, email, passwordOld, passwordNew, save);

        Optional<User> maybeUser = authenticatedUser.get();
        if (maybeUser.isPresent()) {
            User user = maybeUser.get();
            username.setValue(user.getUsername());
            email.setValue(user.getEmail());
        }

        save.addClickListener(event -> {
            if (maybeUser.isPresent()) {
                User user = maybeUser.get();
                if(!passwordOld.isEmpty()){
                    if(passwordEncoder.matches(passwordOld.getValue(), user.getHashedPassword())){
                        user.setHashedPassword(passwordEncoder.encode(passwordNew.getValue()));
                    }
                    else{
                        Notification.show("Contraseña antigua incorrecta");
                        return;
                    }
                }
                user.setUsername(username.getValue());
                user.setEmail(email.getValue());
                userService.save(user);
                Notification.show("Usuario modificado correctamente");
            }
        });
    }
}