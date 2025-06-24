package com.example.calculator;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class CalcController {
    @FXML
    private TextField display;
    private String currentInput = "";

    @FXML
    public void handleDigit(ActionEvent event) {
        Button button = (Button) event.getSource();  // Der geklickte Button
        String value = button.getText();             // Ziffer auslesen
        currentInput += value;
        display.setText(currentInput);               // Anzeige aktualisieren
    }

    public void handleOperator(ActionEvent event) {
        Button button = (Button) event.getSource();
        String operator = button.getText();
        currentInput += " ";
        currentInput += operator;
        currentInput += " ";
        display.setText(currentInput);
    }

    public void calculate() {
        String[] parts = currentInput.split(" ");
        int firstOperand = Integer.parseInt(parts[0]);
        int secondOperand = Integer.parseInt(parts[2]);
        int result = 0;

        switch (parts[1]) {
            case "+" -> result = firstOperand + secondOperand;
            case "-" -> result = firstOperand - secondOperand;
            case "*" -> result = firstOperand * secondOperand;
            case "/" -> result = firstOperand / secondOperand;
            default -> display.setText(parts[0] + ": Programm can't work with this symbol |:");
        }

        display.setText(String.valueOf(result));

    }

    public void clear() {
        display.clear();
        currentInput = "";
    }
}