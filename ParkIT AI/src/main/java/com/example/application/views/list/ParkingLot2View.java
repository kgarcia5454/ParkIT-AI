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

import java.time.LocalTime;

import javax.annotation.security.PermitAll;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "lot2", layout = MainLayout.class)
@PageTitle("QuickSpot:Lot 2")
@PermitAll
public class ParkingLot2View extends VerticalLayout {
    public static int Remainder = 21;
    private static String Reservation = "";

    public ParkingLot2View() {
        addClassName("Main");

        VerticalLayout ParkingContainer = new VerticalLayout();

        ParkingContainer.addClassName("Container");
        ParkingContainer.setWidth("40%");
        ParkingContainer.setHeight("100%");
        H1 welcome = new H1("Parking Lot 2");
        H2 title = new H2("Spots Available: " + Remainder);
        Image image = new Image("images/ParkingLot2.jpg", "Parking Lot 1");
        image.setHeight("70%");
        image.setWidth("80%");
        image.addClassName("ParkingImage");
        Button cancel = new Button("cancel");
        Button reserve = new Button("Reserve");
        ParkingContainer.add(welcome, title, image);

        if (Reservation.equals("")) {
            ParkingContainer.add(reserve);
        } else {
            title.setText(Reservation);
            ParkingContainer.add(cancel);
        }

        add(ParkingContainer);

        Paragraph error = new Paragraph();
        error.addClassName("test");
        TimePicker timePicker = new TimePicker();
        Button Submit = new Button("Submit");

        reserve.addClickListener(ClickEvent -> {
            ParkingContainer.remove(reserve);
            error.setText("What time would you like to reserve a spot");

            timePicker.setLabel("1 hour Parking");
            timePicker.setValue(LocalTime.of(7, 0));

            ParkingContainer.add(error, timePicker, Submit);
        });

        Submit.addClickListener(ClickEvent -> {
            ParkingContainer.remove(timePicker, Submit);
            error.setText("Parking Spot reserved for: " + timePicker.getValue());
            Reservation = ("Parking Spot reserved for: " + timePicker.getValue());
            Remainder--;
            title.setText("Spots Available: " + Remainder);
        });

        cancel.addClickListener(ClickEvent -> {
            Reservation = ("");
            Remainder++;
            UI.getCurrent().getPage().reload();
        });
    }
}
