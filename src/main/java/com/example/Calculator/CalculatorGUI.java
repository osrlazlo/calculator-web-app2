package com.example.Calculator;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Input;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;





public class CalculatorGUI {

    private CalculatorDriver driver;

    public CalculatorGUI() {
    }

    public void addCalculator(VerticalLayout layout) {

        Input outputDisplay = new Input();
        Input inputDisplay = new Input();
        driver = new CalculatorDriver();

        driver.initializeDriver(inputDisplay, outputDisplay);

        outputDisplay.setReadOnly(true);
        outputDisplay.addClassName("display");
        outputDisplay.setId("output-display");
        outputDisplay.addValueChangeListener(event -> {
           // UI.getCurrent().getPage().executeJs("$0.scrollLeft = $0.scrollWidth", null)
        });

        inputDisplay.setReadOnly(true);
        inputDisplay.addClassName("display");
        inputDisplay.setId("input_display");
        inputDisplay.setValue("0");

        CalculatorButton buttonZero = new CalculatorButton(driver, "0");
        CalculatorButton buttonOne = new CalculatorButton(driver, "1");
        CalculatorButton buttonTwo = new CalculatorButton(driver, "2");
        CalculatorButton buttonThree = new CalculatorButton(driver, "3");
        CalculatorButton buttonFour = new CalculatorButton(driver, "4");
        CalculatorButton buttonFive = new CalculatorButton(driver, "5");
        CalculatorButton buttonSix = new CalculatorButton(driver, "6");
        CalculatorButton buttonSeven = new CalculatorButton(driver, "7");
        CalculatorButton buttonEight = new CalculatorButton(driver, "8");
        CalculatorButton buttonNine = new CalculatorButton(driver, "9");
        CalculatorButton buttonDot = new CalculatorButton(driver, ".");
        CalculatorButton buttonSign = new CalculatorButton(driver, "+/-", "sign");
        
        CalculatorButton buttonPlus = new CalculatorButton(driver, "+");
        buttonPlus.addClassName("operator_btn");
        CalculatorButton buttonMinus = new CalculatorButton(driver, "-");
        buttonMinus.addClassName("operator_btn");
        CalculatorButton buttonMultiply = new CalculatorButton(driver, "x");
        buttonMultiply.addClassName("operator_btn");
        CalculatorButton buttonDivide = new CalculatorButton(driver, "÷");
        buttonDivide.addClassName("operator_btn");
        CalculatorButton buttonEqual = new CalculatorButton(driver, "=");
        buttonEqual.addClassName("operator_btn");
        
        CalculatorButton buttonC = new CalculatorButton(driver, "C");
        buttonC.addClassName("other_btn");
        CalculatorButton buttonDEL = new CalculatorButton(driver, "DEL");
        buttonDEL.addClassName("other_btn");
        CalculatorButton buttonExp = new CalculatorButton(driver, "x^y", "^");
        buttonExp.addClassName("other_btn");
        
        CalculatorButton buttonBracketOpen = new CalculatorButton(driver, "(");
        buttonBracketOpen.addClassName("other_btn");
        CalculatorButton buttonBracketClose = new CalculatorButton(driver, ")");
        buttonBracketClose.addClassName("other_btn");
        CalculatorButton buttonFrac = new CalculatorButton(driver, "x/y", "/");
        buttonFrac.addClassName("other_btn");
        //CalculatorButton buttonPi = new CalculatorButton(driver, "\u03C0");
        CalculatorButton buttonPi = new CalculatorButton(driver, "");
        
        

        VerticalLayout keys = new VerticalLayout();
        keys.setId("keys");

            keys.add(buttonC);  
            keys.add(buttonDEL);
            keys.add(buttonFrac);
            keys.add(buttonPi);
            
            keys.add(buttonBracketOpen);
            keys.add(buttonBracketClose);
            keys.add(buttonExp);
            keys.add(buttonDivide);
        
            keys.add(buttonSeven);
            keys.add(buttonEight);
            keys.add(buttonNine);
            keys.add(buttonMultiply);
            
            keys.add(buttonFour);
            keys.add(buttonFive);
            keys.add(buttonSix);
            keys.add(buttonMinus);
                    
            keys.add(buttonOne);
            keys.add(buttonTwo);
            keys.add(buttonThree);
            keys.add(buttonPlus);
                    
            keys.add(buttonSign); 
            keys.add(buttonZero);
            keys.add(buttonDot);
            keys.add(buttonEqual);

    layout.add(outputDisplay, inputDisplay, keys);
        
    }

    public void addHistoryField(HorizontalLayout layout) {
        VerticalLayout historyLayout = new VerticalLayout();
        historyLayout.addClassName("history_layout");

        TextArea historyField = new TextArea();
        
        historyField.addClassName("history_field");
        historyField.setReadOnly(true);
        historyField.setId("history-fiedl");
        historyField.addValueChangeListener(event -> {
            historyField.scrollToEnd();
        });
        
        HorizontalLayout headerLayout = new HorizontalLayout();
        H2 historyHeader = new H2("History");
        Button clearHistory = new Button("Clear");
        clearHistory.addClickListener(event -> {
            historyField.clear();
            driver.clearHistory();
        });

        headerLayout.add(historyHeader, clearHistory);

        historyLayout.add(headerLayout, historyField);
        layout.add(historyLayout);

        driver.initializeHistoryField(historyField);
    }

    
}
