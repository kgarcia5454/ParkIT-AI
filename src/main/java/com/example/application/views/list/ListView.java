package com.example.application.views.list;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.menubar.MenuBar;

import javax.annotation.security.PermitAll;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;



@Route(value = "",layout = MainLayout.class)
@PageTitle("QuickSpot:Home")
@PermitAll
public class ListView extends VerticalLayout {
    public static int counter = 0;

    public ListView() {
        
        addClassName("Main");
        
        VerticalLayout container = new VerticalLayout();

        container.addClassName("Container");
        container.setWidth("40%");
        H1 welcome = new H1("Welcome Back!");
        H2 title = new H2("Where do you want to reserve a spot?");
        Button lot1 = new Button ("Parking Lot 1");
        Button lot2 = new Button ("Parking Lot 2");
        Button lot3 = new Button ("Parking Lot 3");
        container.add(welcome,title,lot1,lot2,lot3);
        add(container);

        lot1.addClickListener(ClickEvent -> {
            UI.getCurrent().navigate("lot1");
        });
        
        lot2.addClickListener(ClickEvent -> {
            UI.getCurrent().navigate("lot2");
        });

        lot3.addClickListener(ClickEvent -> {
            UI.getCurrent().navigate("lot3");
        });
    }


}
