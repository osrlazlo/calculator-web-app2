package com.example.Calculator;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Input;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;





public class CalculatorGUI {



    public CalculatorGUI() {
    }

    public static void addCalculator(VerticalLayout layout) {

        Input outputDisplay = new Input();
        Input inputDisplay = new Input();

        CalculatorDriver.initializeDriver(inputDisplay, outputDisplay);

        outputDisplay.setReadOnly(true);
        outputDisplay.addClassName("display");
        outputDisplay.setId("output-display");
        outputDisplay.addValueChangeListener(_ -> {
           // UI.getCurrent().getPage().executeJs("$0.scrollLeft = $0.scrollWidth", null)
        });

        inputDisplay.setReadOnly(true);
        inputDisplay.addClassName("display");
        inputDisplay.setId("input_display");
        inputDisplay.setValue("0");

        CalculatorButton buttonZero = new CalculatorButton("0");
        CalculatorButton buttonOne = new CalculatorButton("1");
        CalculatorButton buttonTwo = new CalculatorButton("2");
        CalculatorButton buttonThree = new CalculatorButton("3");
        CalculatorButton buttonFour = new CalculatorButton("4");
        CalculatorButton buttonFive = new CalculatorButton("5");
        CalculatorButton buttonSix = new CalculatorButton("6");
        CalculatorButton buttonSeven = new CalculatorButton("7");
        CalculatorButton buttonEight = new CalculatorButton("8");
        CalculatorButton buttonNine = new CalculatorButton("9");
        CalculatorButton buttonDot = new CalculatorButton(".");
        CalculatorButton buttonSign = new CalculatorButton("+/-", "sign");
        
        CalculatorButton buttonPlus = new CalculatorButton("+");
        buttonPlus.addClassName("operator_btn");
        CalculatorButton buttonMinus = new CalculatorButton("-");
        buttonMinus.addClassName("operator_btn");
        CalculatorButton buttonMultiply = new CalculatorButton("x");
        buttonMultiply.addClassName("operator_btn");
        CalculatorButton buttonDivide = new CalculatorButton("÷");
        buttonDivide.addClassName("operator_btn");
        CalculatorButton buttonEqual = new CalculatorButton("=");
        buttonEqual.addClassName("operator_btn");
        
        CalculatorButton buttonC = new CalculatorButton("C");
        buttonC.addClassName("other_btn");
        CalculatorButton buttonDEL = new CalculatorButton("DEL");
        buttonDEL.addClassName("other_btn");
        CalculatorButton buttonExp = new CalculatorButton("x^y", "^");
        buttonExp.addClassName("other_btn");
        
        CalculatorButton buttonBracketOpen = new CalculatorButton("(");
        buttonBracketOpen.addClassName("other_btn");
        CalculatorButton buttonBracketClose = new CalculatorButton(")");
        buttonBracketClose.addClassName("other_btn");
        CalculatorButton buttonFrac = new CalculatorButton("x/y", "/");
        buttonFrac.addClassName("other_btn");
        //CalculatorButton buttonPi = new CalculatorButton("\u03C0");
        CalculatorButton buttonPi = new CalculatorButton("");
        
        

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

    public static void addHistoryField(HorizontalLayout layout) {
        VerticalLayout historyLayout = new VerticalLayout();
        historyLayout.addClassName("history_layout");

        TextArea historyField = new TextArea();
        
        historyField.addClassName("history_field");
        historyField.setReadOnly(true);
        historyField.setId("history-fiedl");
        historyField.addValueChangeListener(_ -> {
            historyField.scrollToEnd();
        });
        

        H2 historyHeader = new H2("History");
        historyLayout.add(historyHeader, historyField);
        layout.add(historyLayout);

        CalculatorDriver.initializeHistoryField(historyField);
    }

    
}
