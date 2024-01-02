package es.uca.iw;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.Nav;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.html.UnorderedList;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.auth.AccessAnnotationChecker;
import com.vaadin.flow.theme.lumo.LumoUtility.AlignItems;
import com.vaadin.flow.theme.lumo.LumoUtility.BoxSizing;
import com.vaadin.flow.theme.lumo.LumoUtility.Display;
import com.vaadin.flow.theme.lumo.LumoUtility.FlexDirection;
import com.vaadin.flow.theme.lumo.LumoUtility.FontSize;
import com.vaadin.flow.theme.lumo.LumoUtility.FontWeight;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import com.vaadin.flow.theme.lumo.LumoUtility.Height;
import com.vaadin.flow.theme.lumo.LumoUtility.ListStyleType;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import com.vaadin.flow.theme.lumo.LumoUtility.Overflow;
import com.vaadin.flow.theme.lumo.LumoUtility.Padding;
import com.vaadin.flow.theme.lumo.LumoUtility.TextColor;
import com.vaadin.flow.theme.lumo.LumoUtility.Whitespace;
import com.vaadin.flow.theme.lumo.LumoUtility.Width;

import es.uca.iw.domain.User;
import es.uca.iw.security.AuthenticatedUser;
import es.uca.iw.views.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.vaadin.lineawesome.LineAwesomeIcon;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout {

    private AuthenticatedUser authenticatedUser;
    private AccessAnnotationChecker accessChecker;

    /**
     * A simple navigation item component, based on ListItem element.
     */
    public static class MenuItemInfo extends ListItem {

        private final Class<? extends Component> view;

        public MenuItemInfo(String menuTitle, Component icon, Class<? extends Component> view) {
            this.view = view;
            RouterLink link = new RouterLink();
            // Use Lumo classnames for various styling
            link.addClassNames(Display.FLEX, Gap.XSMALL, Height.MEDIUM, AlignItems.CENTER, Padding.Horizontal.SMALL,
                    TextColor.BODY);
            link.setRoute(view);

            Span text = new Span(menuTitle);
            // Use Lumo classnames for various styling
            text.addClassNames(FontWeight.MEDIUM, FontSize.MEDIUM, Whitespace.NOWRAP);

            if (icon != null) {
                link.add(icon);
            }
            link.add(text);
            add(link);
        }

        public Class<?> getView() {
            return view;
        }

    }

    public MainLayout(AuthenticatedUser authenticatedUser, AccessAnnotationChecker accessChecker) {
        this.authenticatedUser = authenticatedUser;
        this.accessChecker = accessChecker;
        addToNavbar(createHeaderContent());
        setDrawerOpened(false);
    }

    private Component createHeaderContent() {
        Header header = new Header();
        header.addClassNames(BoxSizing.BORDER, Display.FLEX, FlexDirection.COLUMN, Width.FULL);

        Div layout = new Div();
        layout.addClassNames(Display.FLEX, AlignItems.CENTER, Padding.Horizontal.LARGE);

        H1 appName = new H1("UFJMOVIL");
        appName.addClassNames(Margin.Vertical.MEDIUM, Margin.End.AUTO, FontSize.LARGE);
        layout.add(appName);

        Nav nav = new Nav();
        nav.addClassNames(Display.FLEX, Overflow.AUTO, Padding.Horizontal.MEDIUM, Padding.Vertical.XSMALL);

        // Wrap the links in a list; improves accessibility
        UnorderedList list = new UnorderedList();
        list.addClassNames(Display.FLEX, Gap.SMALL, ListStyleType.NONE, Margin.NONE, Padding.NONE);
        nav.add(list);

        for (MenuItemInfo menuItem : createMenuItems()) {
            list.add(menuItem);

        }

        Optional<User> maybeUser = authenticatedUser.get();
        if (maybeUser.isPresent()) {
            User user = maybeUser.get();

            MenuBar userMenu = new MenuBar();
            userMenu.setThemeName("tertiary-inline contrast");

            MenuItem userName = userMenu.addItem("");
            Div div = new Div();
            div.add(user.getUsername());
            div.add(new Icon("lumo", "dropdown"));
            div.getElement().getStyle().set("display", "flex");
            div.getElement().getStyle().set("align-items", "center");
            div.getElement().getStyle().set("gap", "var(--lumo-space-s)");
            userName.add(div);
            userName.getSubMenu().addItem("Cerrar sesión", e -> {
                authenticatedUser.logout();
            });

            layout.add(userMenu);
        } else {
            Anchor registerLink = new Anchor("/user/registration", "Regístrate");
            Anchor loginLink = new Anchor("/user/login", "Iniciar Sesión");
            Div spacer = new Div();
            spacer.setWidth("1em");
            layout.add(registerLink, spacer, loginLink);
        }

        header.add(layout, nav);
        
        return header;
    }

    private MenuItemInfo[] createMenuItems() {
        List<MenuItemInfo> menuItems = new ArrayList<>();
    
        if (accessChecker.hasAccess(InicioView.class)) {
            menuItems.add(new MenuItemInfo("Inicio", LineAwesomeIcon.GLOBE_SOLID.create(), InicioView.class));
        }
        if (accessChecker.hasAccess(AdminRegistrationView.class)) {
            menuItems.add(new MenuItemInfo("Registro de Usuario Admin", LineAwesomeIcon.GLOBE_SOLID.create(), AdminRegistrationView.class));
        }
        if (accessChecker.hasAccess(UserMiZonaView.class)) {
            menuItems.add(new MenuItemInfo("Mi zona", LineAwesomeIcon.GLOBE_SOLID.create(), UserMiZonaView.class));
        }
        if (accessChecker.hasAccess(FinanzasView.class)) {
            menuItems.add(new MenuItemInfo("Finanzas", LineAwesomeIcon.GLOBE_SOLID.create(), FinanzasView.class));
        }
        if (accessChecker.hasAccess(AtencionClienteView.class)) {
            menuItems.add(new MenuItemInfo("Atención Cliente", LineAwesomeIcon.GLOBE_SOLID.create(), AtencionClienteView.class));
        }
        if (accessChecker.hasAccess(VentasView.class)) {
            menuItems.add(new MenuItemInfo("Ventas", LineAwesomeIcon.GLOBE_SOLID.create(), VentasView.class));
        }
        if (accessChecker.hasAccess(ContestaConsultaView.class)) {
            menuItems.add(new MenuItemInfo("Contesta Consulta", LineAwesomeIcon.GLOBE_SOLID.create(), ContestaConsultaView.class));
        }
        if (accessChecker.hasAccess(CreaServicioView.class)) {
            menuItems.add(new MenuItemInfo("Añadir servicio", LineAwesomeIcon.GLOBE_SOLID.create(), CreaServicioView.class));
        }
    
        return menuItems.toArray(new MenuItemInfo[0]);
    }

}
