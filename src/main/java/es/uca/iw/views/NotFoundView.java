package es.uca.iw.views;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;

import es.uca.iw.MainLayout;
import jakarta.servlet.http.HttpServletResponse;

@Tag(Tag.DIV)
@Route(value = "not-found", layout = MainLayout.class)
public class NotFoundView extends VerticalLayout implements HasErrorParameter<NotFoundException> {

    private H2 message;

    public NotFoundView() {
        message = new H2();
        add(message);
    }

    @Override
    public int setErrorParameter(BeforeEnterEvent event, ErrorParameter<NotFoundException> parameter) {
        message.setText("La página que estás buscando no existe o no tienes permiso para acceder a ella.");
        return HttpServletResponse.SC_NOT_FOUND;
    }
}