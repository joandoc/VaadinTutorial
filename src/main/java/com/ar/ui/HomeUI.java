package com.ar.ui;

import com.ar.ui.header.HeaderPanel;
import com.ar.ui.menu.Menu;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;

public class HomeUI extends UI {

    public VerticalLayout mainContent;

    @Override
    protected void init(VaadinRequest request) {

        setTheme("example");
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setSizeFull();

        verticalLayout.addComponent(new HeaderPanel());
        verticalLayout.addComponent(new Menu());
        mainContent = new VerticalLayout(new Label("Inicio"));
        mainContent.setMargin(true);
        mainContent.setSizeFull();
        verticalLayout.addComponent(mainContent);
        verticalLayout.setExpandRatio(mainContent,1);
        setContent(verticalLayout);
    }

    public void changeMenuItem(Component component) {
        mainContent.replaceComponent(mainContent.getComponent(0), component);
    }

}
