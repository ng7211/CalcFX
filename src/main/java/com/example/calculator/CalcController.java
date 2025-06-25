package com.example.calculator;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;



public class CalcController {
    private final List<Double> operands = new ArrayList<>();
    private final List<String> operators = new ArrayList<>();

    @FXML
    private TextField display;
    private String currentInput = "";

    @FXML
    public void handleDigit(ActionEvent event) {
        Button button = (Button) event.getSource();
        String value = button.getText();             //number that is inputted
        currentInput += value;
        display.setText(currentInput);               //updates display
    }

    @FXML
    public void handleOperator(ActionEvent event) {
        //converts buttons that were pressed into a Double
        if(! currentInput.isEmpty()) {
            operands.add(Double.parseDouble(currentInput));
            currentInput = "";
        }

        Button button = (Button) event.getSource();
        String op = button.getText();
        operators.add(op);

        display.setText(display.getText() + " " + op + " ");
    }

    /**
     * This method performs simple arithmetic operations.
     */
    @FXML
    public void calculate() {
        //converts buttons that were pressed into a Double
        if(! currentInput.isEmpty()) {
            operands.add(Double.parseDouble(currentInput));
            currentInput = "";
        }

        if(operands.size() < 2 || operators.isEmpty()) {
            display.setText("INCOMPLETE EXPRESSION!");
            return;
        }

        double result = operands.getFirst(); //first number
        int opIndex = 1;

        for (String op : operators){
            if (opIndex >= operands.size()) break;

            double next = operands.get(opIndex++);
            switch (op) {
                case "+" -> result += next;
                case "-" -> result -= next;
                case "*" -> result *= next;
                case "/" -> {
                    if(next == 0) {
                        display.setText("Division by zero is not defined!");
                        return;
                    }
                    result /= next;
                }
                case "^" -> result = Math.pow(result, next);
            }
        }
        display.setText(String.valueOf(result));
        operands.clear();
        operators.clear();
        currentInput = String.valueOf(result); //ensuring further operations with result of former operations
    }

    @FXML
    public void clear() {
        display.clear();
        operators.clear();
        operands.clear();
        currentInput = "";
    }
}