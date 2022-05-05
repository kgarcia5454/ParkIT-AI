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
import com.vaadin.flow.component.button.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import net.bytebuddy.implementation.bytecode.Remainder;

@Route(value = "lot1", layout = MainLayout.class)
@PageTitle("QuickSpot:Lot 1")
@PermitAll
public class ParkingLot1View extends VerticalLayout {
    public static int Remainder = 0;

    public ParkingLot1View() {
        addClassName("Main");

        VerticalLayout ParkingContainer = new VerticalLayout();

        ParkingContainer.addClassName("Container");
        ParkingContainer.setWidth("40%");
        H1 welcome = new H1("Parking Lot 1");
        H2 title = new H2("Spots Available: " + Remainder);
        Image image = new Image("images/ParkingLot1.jpg", "Parking Lot 1");
        image.setWidth("100%");
        image.addClassName("ParkingImage");

        Button reserve = new Button("Reserve");
        ParkingContainer.add(welcome, title, image, reserve);
        add(ParkingContainer);

        reserve.addClickListener(ClickEvent -> {
            if (Remainder == 0) {
                ParkingContainer.remove(reserve);
                Paragraph error = new Paragraph("Out of available spots");
                ParkingContainer.add(error);

            }
        });
    }

}
