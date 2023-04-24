package com.example.hangman_ver2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class HelloController {

    @FXML
    private Label passwordLabel = new Label();
    @FXML
    private TextField letterField;
    @FXML
    private Button checkButton;
    @FXML
    private Button startButton;
    private final String password;
    private StringBuilder hiddenPassword = new StringBuilder();
    private String hiddenPass;
    private boolean importPassword = false;
    private List<Character> containsLetter = new ArrayList<>();
    public HelloController(){
        PasswordSetter passwordSetter = new PasswordSetter();
        password = passwordSetter.getPassword();

        containsLetter.forEach((n) -> containsLetter.add(password.charAt(n)));

        if(password.equals("ERROR:001")){
            passwordLabel.setText("Nie mozna zaladowac hasla");
            System.out.println("ERROR 001");
        }
        else {
            hiddenPassword.append("_ ".repeat(password.length()));
            hiddenPass = String.valueOf(hiddenPassword);
            System.out.println(hiddenPass);
            System.out.println(hiddenPassword);
            importPassword = true;

        }

    }
    public void start(){
        if(importPassword){
            passwordLabel.setText(hiddenPass);
        }
        else {
            passwordLabel.setText("Error");
        }
        startButton.setVisible(false);
        letterField.setEditable(true);
        checkButton.setVisible(true);
    }
    public void checkLetter(){
        char x = letterField.getText().toUpperCase().charAt(0);
        System.out.println(x);
        if (containsLetter.contains(x)){
            for(int i = 0 ; i < hiddenPassword.length(); i++){

            }

        }
    }





}