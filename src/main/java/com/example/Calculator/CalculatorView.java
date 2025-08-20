package com.example.Calculator;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.dependency.CssImport;


//@Route("/calculator")
@Route("")
@CssImport("./styles/styles.css")
public class CalculatorView extends VerticalLayout {
    public CalculatorView() {
        this.addClassName("main_view");
        this.setSizeFull();

        VerticalLayout calculator = new VerticalLayout();
        calculator.setId("calculator");
        
        CalculatorGUI.addCalculator(calculator);

        HorizontalLayout container = new HorizontalLayout();
        container.setWidth("100vh");
        container.add(calculator);
        CalculatorGUI.addHistoryField(container);

        add(container);
    }

}
