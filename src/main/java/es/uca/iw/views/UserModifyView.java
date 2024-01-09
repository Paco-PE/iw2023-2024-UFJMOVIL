package es.uca.iw.views;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
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
    private Button delete = new Button("Eliminar");

    public UserModifyView(AuthenticatedUser authenticatedUser, UserDetailsServiceImpl userService, PasswordEncoder passwordEncoder) {
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);
        add(title, username, email, passwordOld, passwordNew, save);

        Optional<User> maybeUser = authenticatedUser.get();
        if (maybeUser.isPresent()) {
            User user = maybeUser.get();
            username.setValue(user.getUsername());
            email.setValue(user.getEmail());

            if (!user.getUsername().equals("admin")) {
                add(delete);
            }
        }

        save.addClickListener(event -> {
            if (maybeUser.isPresent()) {
                User user = maybeUser.get();
                String usernameValue = username.getValue();
                String emailValue = email.getValue();
        
                if (usernameValue.isEmpty() || emailValue.isEmpty()) {
                    Notification.show("Nombre de usuario y correo electrónico son obligatorios");
                } else {
                    if(!passwordOld.isEmpty()){
                        if(passwordEncoder.matches(passwordOld.getValue(), user.getHashedPassword())){
                            user.setHashedPassword(passwordEncoder.encode(passwordNew.getValue()));
                        }
                        else{
                            Notification.show("Contraseña antigua incorrecta");
                            return;
                        }
                    }
                    user.setUsername(usernameValue);
                    user.setEmail(emailValue);
                    userService.save(user);
                    Notification.show("Usuario modificado correctamente");
                }
            }
        });

        delete.addClickListener(event -> {
            if (maybeUser.isPresent()) {
                Dialog confirmationDialog = new Dialog();
                Button confirmButton = new Button("Confirmar", event2 -> {
                    User user = maybeUser.get();
                    userService.delete(user);
                    confirmationDialog.close();
                    Notification.show("Usuario eliminado correctamente");
                    getUI().ifPresent(ui -> ui.navigate("home"));
                });
                Button cancelButton = new Button("Cancelar", event2 -> confirmationDialog.close());
                confirmationDialog.add(new Text("¿Estás seguro de que quieres eliminar tu cuenta? Esta acción no se puede deshacer."), confirmButton, cancelButton);
                confirmationDialog.open();
            }
        });
    }
}