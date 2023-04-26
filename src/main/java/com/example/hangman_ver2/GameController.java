package com.example.hangman_ver2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.*;


public class GameController extends  MainMenuController{

    @FXML
    private Label passwordLabel = new Label(); //wyswietlane haslo
    @FXML
    private Label isGoodLabel;
    @FXML
    private Label usedLetterLabel;
    @FXML
    private TextField letterField; //wprowadzanie litery
    @FXML
    private TextField saveScoreField;
    @FXML
    private Button saveScoreButton;
    @FXML
    private Button checkButton; //przycisk sprawdz
    @FXML
    private Button startButton; //przycisk rozpoczecia
    @FXML
    private Button playAgainButton; //przycisk ponownej gry
    @FXML
    ImageView imageView ;
    @FXML
    List<Image> images;

    private ImageController imageController = new ImageController();

    private final String password;
    private final StringBuilder hiddenPassword = new StringBuilder();
    private String hiddenPass;
    private boolean importPassword = false;
    private final StringBuilder visiblePassword = new StringBuilder();
    private final List<Character> usedLetter = new ArrayList<>();
    private final int maxGood;
    private int good = 0;
    private int bad = 0;
    private long startTime, time;

    public GameController(){
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
            //start();
        }

    }
    public void start(){
        if(importPassword){
            passwordLabel.setText(hiddenPass);
            startTime = System.currentTimeMillis();
        }
        else {
            passwordLabel.setText("Error");
        }
        startButton.setVisible(false);
        letterField.setEditable(true);
        checkButton.setVisible(true);
        images = imageController.loadImages();
        imageView.setImage(images.get(0));
    }
    public void checkLetter(){
        char x = letterField.getText().toUpperCase().charAt(0); //pobieramy litere z pola i ustawiamy zmieniamy na wielka
        if(usedLetter.contains(x)){
            System.out.println("Już podałeś te litere"); //jesli ta litera juz sie pojawila to nie program nie idzie dalej
            isGoodLabel.setTextFill(Color.valueOf("#dac04e")); //zmiana koloru
            isGoodLabel.setText("Już podałeś tę literę!");
        }
        else {
            usedLetter.add(x);
            System.out.println(x);
            String a = Character.toString(x);
            usedLetterLabel.setText(usedLetter.toString());
            System.out.println(password.contains(a));
            if (password.contains(a)) {
                for (int i = 0; i < hiddenPassword.length(); i++) {
                    if (x == visiblePassword.charAt(i)) {
                        hiddenPassword.deleteCharAt(i);
                        hiddenPassword.insert(i, x);
                        good++;
                        isGoodLabel.setTextFill(Color.valueOf("#0e9e40"));
                        isGoodLabel.setText("Poprawna litera!");
                    }
                }
            } else {
                bad++;
                isGoodLabel.setTextFill(Color.valueOf("#b90a0a"));
                isGoodLabel.setText("Niepoprawna litera!");
            }
            passwordLabel.setText(String.valueOf(hiddenPassword));
        }
        letterField.clear();
        int maxBad = 5;
        if(good >= maxGood){
            long endTime = System.currentTimeMillis();
            System.out.println("Wygrana");
            letterField.setEditable(false);
            checkButton.setVisible(false);
            passwordLabel.setText("Wygrana! Hasło to: " + password);
            playAgainButton.setVisible(true);
            isGoodLabel.setTextFill(Color.valueOf("#0e9e40"));
            time = ((endTime -startTime)/1000);
            isGoodLabel.setText(" twój czas: " + (int)time + " sekund. Gratulacje!");
            saveScoreButton.setVisible(true);
            saveScoreField.setVisible(true);



        }
        else if(bad >= maxBad){
            //endTime = System.currentTimeMillis();
            System.out.println("Przegrana");
            letterField.setEditable(false);
            checkButton.setVisible(false);
            passwordLabel.setText("Przegrana! Hasło to: " + password);
            playAgainButton.setVisible(true);
            isGoodLabel.setTextFill(Color.valueOf("#b90a0a"));
            isGoodLabel.setText("Następnym razem się uda!");
        }
    }
    public void saveScore(){
        String nickname = saveScoreField.getText();
        DBConnector dbc = new DBConnector();
        dbc.addScore(nickname, password, (int)time);
        saveScoreField.setVisible(false);
        saveScoreButton.setVisible(false);
        isGoodLabel.setText("Pomyślnie zapisano wynik!");
    }


    @Override
    public void mainMenu(ActionEvent event) throws IOException {
        super.mainMenu(event);
    }

    @Override
    public void newGame(ActionEvent event) throws IOException {
        System.out.println("Rozpoczynanie nowej gry!");
        super.newGame(event);
    }
}