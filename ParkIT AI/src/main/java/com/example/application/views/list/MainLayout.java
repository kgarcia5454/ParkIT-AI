package com.example.application.views.list;

import com.example.application.security.SecurityService;
import com.example.application.views.list.ListView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

import com.vaadin.flow.server.PWA;

public class MainLayout extends AppLayout {

    private final SecurityService securityService;

    public MainLayout(SecurityService securityService) {
        this.securityService = securityService;
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        
        H1 logo = new H1("QuickSpot");
        logo.addClassNames("text-l", "m-m");

        Button logout = new Button("Log Out", e -> securityService.logout());
        HorizontalLayout header = new HorizontalLayout(

                new DrawerToggle(),
                logo,
                logout
                
                );

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);

        header.setWidth("100%");
        header.addClassNames("py-0", "px-m");
        header.addClassName("NavBar");

        addToNavbar(header);

    }

    private void createDrawer() {
        RouterLink listLink = new RouterLink("List", ListView.class);

        listLink.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(
                new RouterLink("Home", ListView.class),
                new RouterLink("Parking Lot 1", ParkingLot1View.class),
                new RouterLink("Parking Lot 2", ParkingLot2View.class),
                new RouterLink("Parking Lot 3", ParkingLot3View.class)
            ));
    }
}