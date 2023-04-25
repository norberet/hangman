package com.example.hangman_ver2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.*;


public class HelloController {

    @FXML
    private Label passwordLabel = new Label(); //wyswietlane haslo
    @FXML
    private TextField letterField; //wprowadzanie litery
    @FXML
    private Button checkButton; //przycisk sprawdz
    @FXML
    private Button startButton; //przycisk rozpoczecia
    @FXML
    private Button playAgainButton; //przycisk ponownej gry

    private final String password;
    private StringBuilder hiddenPassword = new StringBuilder();
    private String hiddenPass;
    private boolean importPassword = false;
    private StringBuilder visiblePassword = new StringBuilder();
    private List<Character> usedLetter = new ArrayList<>();
    private final int maxBad = 5;
    private final int maxGood;
    private int good = 0;
    private int bad = 0;
    public HelloController(){
        PasswordSetter passwordSetter = new PasswordSetter();
        password = passwordSetter.getPassword();
        maxGood = password.length(); //ilosc liter = ilosc mozliwie dobrych "strzalow"
        //pobranie hasla z klasy PasswordSetter


        if(password.equals("ERROR:001")){
            passwordLabel.setText("Nie mozna zaladowac hasla");
            System.out.println("ERROR 001");
        }
        else { //jesli nie ma bledu to rozpoczynam ukrywanie hasla
            if(password.contains(" ") || password.contains("-")) {
                for (int i = 0; i < password.length(); i++) {
                    if (password.charAt(i) == ' ') {
                        hiddenPassword.append("  ");
                        good++; //w przypadku bialych znakow drukujemy rozdzielenie wyrazow i dodajemy dobra dop
                        //w celu poprawnego liczenia
                    } else if (password.charAt(i) == '-') {
                        hiddenPassword.append("- ");
                        good++;
                    } else {
                        hiddenPassword.append("_ ");
                    }
                }
            }else {
                hiddenPassword.append("_ ".repeat(password.length()));
                //jesli haslo sklada sie z jednego wyrazu to wszystko uzupelniamy znakiem '_' ze spacja
            }

            if(password.contains(" ") || password.contains("-")){ //to samo dla widocznego hasla
                for (int i = 0; i < password.length(); i++) {
                    if (password.charAt(i) == ' ') {
                        visiblePassword.append("  ");
                    } else if (password.charAt(i) == '-') {
                        visiblePassword.append("- ");
                    } else {
                        visiblePassword.append(password.charAt(i)).append(" ");
                    }
                }
            }else {
                for (int i = 0; i < password.length(); i++) {
                    visiblePassword.append(password.charAt(i)).append(" ");
                }
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
        char x = letterField.getText().toUpperCase().charAt(0); //pobieramy litere z pola i ustawiamy zmieniamy na wielka
        if(usedLetter.contains(x)){
            System.out.println("Już podałeś te litere"); //jesli ta litera juz sie pojawila to nie program nie idzie dalej
        }
        else {
            usedLetter.add(x);
            System.out.println(x);
            String a = Character.toString(x);
            System.out.println(password.contains(a));
            if (password.contains(a)) {
                for (int i = 0; i < hiddenPassword.length(); i++) {
                    if (x == visiblePassword.charAt(i)) {
                        hiddenPassword.deleteCharAt(i);
                        hiddenPassword.insert(i, x);
                        good++;
                    }
                }
            } else {
                bad++;
            }
            passwordLabel.setText(String.valueOf(hiddenPassword));
        }
        letterField.clear();
        if(good >= maxGood){
            System.out.println("Wygrana");
            letterField.setEditable(false);
            checkButton.setVisible(false);
            passwordLabel.setText("Wygrana! Hasło to: " + password);
            playAgainButton.setVisible(true);
        }
        else if(bad >= maxBad){
            System.out.println("Przegrana");
            letterField.setEditable(false);
            checkButton.setVisible(false);
            passwordLabel.setText("Przegrana! Hasło to: " + password);
            playAgainButton.setVisible(true);
        }
    }
    public void playAgain(){
        checkButton.setVisible(true);
        playAgainButton.setVisible(false);
        System.out.println("Rozpoczynanie nowej gry");
    }





}