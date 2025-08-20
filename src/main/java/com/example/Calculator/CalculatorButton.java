package com.example.Calculator;

import com.vaadin.flow.component.button.Button;

public class CalculatorButton extends Button {
        private String label;
        private CalculatorDriver driver;
        
        public CalculatorButton() {
            this.addClassName("button");
            label = null;
            driver = null;
        }

        public CalculatorButton(CalculatorDriver driver, String label) {
            super(label);
            this.label = label;
            this.driver = driver;
            this.addClickListener(event -> {
                this.driver.buttonPress(this.label);
            });
            this.addClassName("button");
        }
        
        public CalculatorButton(CalculatorDriver driver, String label, String value) {
            super(label);
            this.label = value;
            this.driver = driver;
            this.addClickListener(event -> {
                this.driver.buttonPress(this.label);
            });
            this.addClassName("button");
        }
    }
