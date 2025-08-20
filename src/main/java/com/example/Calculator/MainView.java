package com.example.Calculator;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

//@Route("")
public class MainView extends VerticalLayout {

     public MainView() {
        this.addClassName("main_view");
        this.setSizeFull();

        H1 headerH1 = new H1("Abdoul Kone's Portfolio");
        Anchor calculatAnchor = new Anchor("/calculator", "Scientific Calculator Web App");
        add(headerH1, calculatAnchor);

    }
}
