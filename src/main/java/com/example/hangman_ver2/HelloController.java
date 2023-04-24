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
    private StringBuilder visiblePassword = new StringBuilder();
    final int maxBad = 5;
    final int maxGood;
    int good = 0;
    int bad = 0;
    public HelloController(){
        PasswordSetter passwordSetter = new PasswordSetter();
        password = passwordSetter.getPassword();
        maxGood = password.length();



        if(password.equals("ERROR:001")){
            passwordLabel.setText("Nie mozna zaladowac hasla");
            System.out.println("ERROR 001");
        }
        else {
            hiddenPassword.append("_ ".repeat(password.length()));
            for(int i = 0; i < password.length(); i++){
                visiblePassword.append(password.charAt(i) + " ");
            }

            hiddenPass = String.valueOf(hiddenPassword);
            System.out.println(hiddenPassword);
            System.out.println(visiblePassword);
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
        String a = Character.toString(x);
        System.out.println(password.contains(a));
        if(password.contains(a)){
            for(int i = 0; i < hiddenPassword.length() ; i++){
                if(x == visiblePassword.charAt(i)){
                    hiddenPassword.deleteCharAt(i);
                    hiddenPassword.insert(i, x);
                    good++;
                }
            }
        }
        else
            bad++;

        passwordLabel.setText(String.valueOf(hiddenPassword));
        letterField.clear();
        if(good >= maxGood){
            System.out.println("Wygrana");
            letterField.setEditable(false);
            checkButton.setVisible(false);
            passwordLabel.setText("Wygrana! Hasło to: " + password);
        }
        else if(bad >= maxBad){
            System.out.println("Przegrana");
            letterField.setEditable(false);
            checkButton.setVisible(false);
            passwordLabel.setText("Przegrana! Hasło to: " + password);
        }
    }





}