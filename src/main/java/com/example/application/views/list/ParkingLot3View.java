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

@Route(value = "lot3", layout = MainLayout.class)
@PageTitle("QuickSpot:Lot 3")
@PermitAll
public class ParkingLot3View extends VerticalLayout {
    public static int Remainder = 0;
    public static int Handicap = 2;
    private static String Reservation = "";

    public ParkingLot3View() {
        addClassName("Main");

        VerticalLayout ParkingContainer = new VerticalLayout();

        ParkingContainer.addClassName("Container");
        ParkingContainer.setWidth("40%");
        H1 welcome = new H1("Parking Lot 3");
        H2 title = new H2("Spots Available: " + Remainder + "(" + Handicap + ")");
        Image image = new Image("images/ParkingLot3.jpg", "Parking Lot 1");
        image.setWidth("100%");
        image.addClassName("ParkingImage");
        Button reserve = new Button("Reserve");
        Button cancel = new Button("cancel");
        ParkingContainer.add(welcome, title, image);

        if (Reservation.equals("")) {
            ParkingContainer.add(reserve);
        } else {
            title.setText(Reservation);
            ParkingContainer.add(cancel);
        }

        add(ParkingContainer);

        Div HandicapQ = new Div();
        Paragraph Question = new Paragraph("Do you have a valid disabled parking permit?");
        Button Hand_Yes = new Button("Yes");
        Button Hand_No = new Button("No");
        Button Submit = new Button("Submit");
        Paragraph error = new Paragraph();
        TimePicker timePicker = new TimePicker();

        reserve.addClickListener(ClickEvent -> {
            if (Remainder == 0 && Handicap > 0) {
                ParkingContainer.remove(reserve);
                HandicapQ.add(Hand_Yes, Hand_No);
                ParkingContainer.add(Question, HandicapQ);

            }
        });

        Hand_No.addClickListener(ClickEvent -> {
            if (Remainder == 0) {
                ParkingContainer.remove(Question, HandicapQ);
                error.setText("Out of available spots");
                ParkingContainer.add(error);
            }
        });

        Hand_Yes.addClickListener(ClickEvent -> {
            if (Handicap > 0) {
                ParkingContainer.remove(Question, HandicapQ);
                error.setText("What time would you like to reserve a spot");

                timePicker.setLabel("1 hour Parking");
                timePicker.setValue(LocalTime.of(7, 0));

                ParkingContainer.add(error, timePicker, Submit);
            }
        });

        Submit.addClickListener(ClickEvent -> {
            ParkingContainer.remove(timePicker, Submit);
            error.setText("Parking Spot reserved for: " + timePicker.getValue());
            Reservation = ("Parking Spot reserved for: " + timePicker.getValue());
            Handicap--;
            title.setText("Spots Available: " + Remainder + "(" + Handicap + ")");
        });

        cancel.addClickListener(ClickEvent -> {
            Reservation = ("");
            Handicap++;
            UI.getCurrent().getPage().reload();
        });
    }
}
