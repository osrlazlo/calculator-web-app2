package com.example.Calculator;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.dependency.CssImport;


//@Route("/calculator")
@Route("")
@CssImport("./styles/styles.css")
public class CalculatorView extends VerticalLayout {

    private CalculatorGUI calcGUI;

    public CalculatorView() {
        this.addClassName("main_view");
        this.setSizeFull();

        VerticalLayout calculator = new VerticalLayout();
        calculator.setId("calculator");

        calcGUI = new CalculatorGUI();
        
        calcGUI.addCalculator(calculator);

        HorizontalLayout container = new HorizontalLayout();
        container.setWidth("100vh");
        container.add(calculator);
        calcGUI.addHistoryField(container);

        add(container);
    }

}
