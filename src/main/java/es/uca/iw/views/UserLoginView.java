package es.uca.iw.views;

import java.util.Optional;
import java.util.Set;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.internal.RouteUtil;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import es.uca.iw.security.AuthenticatedUser;
import es.uca.iw.MainLayout;
import es.uca.iw.domain.Role;
import es.uca.iw.domain.User;

@PageTitle("Iniciar Sesion")
@Route(value = "user/login", layout = MainLayout.class)
@AnonymousAllowed
public class UserLoginView extends LoginOverlay implements BeforeEnterObserver {

    private final AuthenticatedUser authenticatedUser;

    public UserLoginView(AuthenticatedUser authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
        setAction(RouteUtil.getRoutePath(VaadinService.getCurrent().getContext(), getClass()));
    
        LoginI18n i18n = LoginI18n.createDefault();
        i18n.setHeader(new LoginI18n.Header());
        i18n.getHeader().setTitle("UFJMOVIL");
        i18n.setAdditionalInformation(null);
        i18n.setForm(new LoginI18n.Form());
        i18n.getForm().setTitle("Iniciar sesión");
        i18n.getForm().setUsername("Usuario");
        i18n.getForm().setPassword("Contraseña");
        i18n.getForm().setSubmit("Entrar");
        setI18n(i18n);
        
        setForgotPasswordButtonVisible(false);
        setOpened(true);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Optional<User> maybeUser = authenticatedUser.get();
        if (maybeUser.isPresent()) {
            setOpened(false);
            Set<Role> userRole = maybeUser.get().getRoles();

            if (userRole.contains(Role.CLIENTE)) {
                event.forwardTo("/mi-zona");
            } else if (userRole.contains(Role.EMPLEADO_ATENCION_CLIENTE)) {
                event.rerouteTo("/atencion");
            } else if (userRole.contains(Role.EMPLEADO_FINANCIERO)) {
                event.forwardTo("/finanzas");
            } else if (userRole.contains(Role.EMPLEADO_COMERCIAL)) {
                event.forwardTo("/ventas");
            }

            setError(event.getLocation().getQueryParameters().getParameters().containsKey("error"));
        }
    }
}

