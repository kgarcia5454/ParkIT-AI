
package com.example.application.views.list;

import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("login")
@PageTitle("QuickSpot:Login")
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    private LoginOverlay loginOverlay = new LoginOverlay();

    public LoginView() {

        addClassName("login-view");

        LoginI18n i18n = LoginI18n.createDefault();
        i18n.setAdditionalInformation("For testing purposes. Please use Username:QuickSpotter Password: Pass123");

        loginOverlay.setTitle("QuickSpot");
        loginOverlay.setDescription("Make parking a breeze.");
        loginOverlay.setI18n(i18n);

        add(loginOverlay);
        loginOverlay.setOpened(true);

        loginOverlay.setAction("login");
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if (beforeEnterEvent.getLocation()
                .getQueryParameters()
                .getParameters()
                .containsKey("error")) {
            loginOverlay.setError(true);
        }
    }
}
