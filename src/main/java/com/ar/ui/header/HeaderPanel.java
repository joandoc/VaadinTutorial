package com.ar.ui.header;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by joan on 10/10/15.
 */
public class HeaderPanel extends HorizontalLayout {

    public HeaderPanel(){

        setWidth("100%");
        setHeight("64px");
        setStyleName("header-panel");
        setMargin(true);
        setSpacing(true);

        Label label = new Label("App Vaadin");
        label.setStyleName("title");

        VerticalLayout titleLabel = new VerticalLayout(label);



        addComponent(titleLabel);
        setComponentAlignment(titleLabel, Alignment.MIDDLE_LEFT);


    }


}
