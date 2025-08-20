package com.example.Calculator;

import com.vaadin.flow.component.button.Button;

public class CalculatorButton extends Button {
        String label;
        
        public CalculatorButton() {
            this.addClassName("button");
            label = null;
            
        }

        public CalculatorButton(String label) {
            super(label);
            this.label = label;
            this.addClickListener(event -> {
                CalculatorDriver.buttonPress(label);
            });
            this.addClassName("button");
        }
        
        public CalculatorButton(String label, String value) {
            super(label);
            this.label = value;
            this.addClickListener(event -> {
                CalculatorDriver.buttonPress(this.label);
            });
            this.addClassName("button");
        }
    }
