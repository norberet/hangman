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
    ImageView imageView ; //wyswietla obrazki wisielca
    Image image0 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/0.png")));
    Image image1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/1.png")));
    Image image2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/2.png")));
    Image image3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/3.png")));
    Image image4 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/4.png")));
    Image image5 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/5.png")));
    Image image6 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/6.png")));
    Image image7 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/7.png")));
    Image image8 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/8.png")));
    Image image9 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/9.png")));
    // obrazki
    private final String password; //haslo
    private final StringBuilder hiddenPassword = new StringBuilder(); //ukryte haslo do wyswietlania userowi
    private String hiddenPass;
    private boolean importPassword = false; //okresla poprawnosc zczytania hasla
    private final StringBuilder visiblePassword = new StringBuilder(); //do porównywania haseł
    private final List<Character> usedLetter = new ArrayList<>(); //uzyte znaki
    private final int maxGood; //liczba znakow
    private int good = 0;
    private int bad = 0; //liczniki dobrych i zlych odpo
    private long startTime, time; //licnzik czasu

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
            importPassword = true; //informujemy ze operacja sie udala
            //start();
        }

    }
    public void start(){
        if(importPassword){
            passwordLabel.setText(hiddenPass); //przekazanie hasla na ekran
            startTime = System.currentTimeMillis(); //jesli haslo jest wczytane po nacisnieciu start uruchamiamy gre i liczymy czas
        }
        else {
            passwordLabel.setText("Error");
        }
        startButton.setVisible(false); //ukrywamy przycisk start
        letterField.setEditable(true); //wlaczmy mozliwość edycji
        checkButton.setVisible(true); //pokazuje przycisk do sprawdzenia
        setImage(0);

    }
    public void checkLetter() { //sprawdzanie hasla
        try {
        char x = letterField.getText().toUpperCase().charAt(0); //pobieramy litere z pola i ustawiamy zmieniamy na wielka

        if(usedLetter.contains(x)){
            System.out.println("Już podałeś te litere"); //jesli ta litera juz sie pojawila to nie program nie idzie dalej
            isGoodLabel.setTextFill(Color.valueOf("#dac04e")); //zmiana koloru
            isGoodLabel.setText("Już podałeś tę literę!");
        }
        else {
            usedLetter.add(x); //jesli litery jeszcze nie było to dodajemy do zbioru
            System.out.println(x); //drukowanie w konsoli w celu sprawdzania poprawnosci dzialania
            String a = Character.toString(x);
            usedLetterLabel.setText(usedLetter.toString()); //w czasie rzeczywistym wyswietlamy uzyte litery
            System.out.println(password.contains(a));
            if (password.contains(a)) {
                for (int i = 0; i < hiddenPassword.length(); i++) {
                    if (x == visiblePassword.charAt(i)) {
                        hiddenPassword.deleteCharAt(i);
                        hiddenPassword.insert(i, x);
                        good++; //jesli litera jest poprawna edytujemy hiddenPassword i wyswietlamy aktualizacje
                        isGoodLabel.setTextFill(Color.valueOf("#0e9e40"));
                        isGoodLabel.setText("Poprawna litera!");
                    }
                }
            } else {
                bad++;
                isGoodLabel.setTextFill(Color.valueOf("#b90a0a"));
                isGoodLabel.setText("Niepoprawna litera!");
                setImage(bad); //jesli nie to liczymy bledy i wyswietlamy obrazek wraz z komunikatem
            }
            passwordLabel.setText(String.valueOf(hiddenPassword));
        }
        letterField.clear(); //po kazdym podaniu litery czyscimy okno
        final int maxBad = 9; // maksymalna ilosc bledow
        if(good >= maxGood){
            long endTime = System.currentTimeMillis();
            System.out.println("Wygrana");
            letterField.setEditable(false);
            checkButton.setVisible(false);
            passwordLabel.setText("Wygrana! Hasło to: " + password);
            playAgainButton.setVisible(true); //"Zagraj jeszcze raz"
            isGoodLabel.setTextFill(Color.valueOf("#0e9e40"));
            time = ((endTime -startTime)/1000);
            isGoodLabel.setText(" twój czas: " + (int)time + " sekund. Gratulacje!");
            saveScoreButton.setVisible(true);
            saveScoreField.setVisible(true); //pojawia sie okienko do zapisania wyniku



        }
        else if(bad >= maxBad){
            //endTime = System.currentTimeMillis();
            System.out.println("Przegrana");
            letterField.setEditable(false);
            checkButton.setVisible(false);
            passwordLabel.setText("Przegrana! Hasło to: " + password);
            playAgainButton.setVisible(true); // "Zagraj jeszcze raz"
            isGoodLabel.setTextFill(Color.valueOf("#b90a0a"));
            isGoodLabel.setText("Następnym razem się uda!"); //informacja zwrotna po przegranej
        }
        }catch (RuntimeException e){
            System.out.println("Puste pole");
        }
    }
    public void setImage(int a){ //wyswietlanie odpowiednich obrazkow
        if(a == 0){
            imageView.setImage(image0);
        }else if (a == 1) {
            imageView.setImage(image1);
        }else if (a == 2) {
            imageView.setImage(image2);
        }else if (a == 3) {
            imageView.setImage(image3);
        }else if (a == 4) {
            imageView.setImage(image4);
        }else if (a == 5) {
            imageView.setImage(image5);
        }else if (a == 6) {
            imageView.setImage(image6);
        }else if (a == 7) {
            imageView.setImage(image7);
        }else if (a == 8) {
            imageView.setImage(image8);
        }else if (a == 9) {
            imageView.setImage(image9);
        }

    }
    public void saveScore(){
        String nickname = saveScoreField.getText();
        DBConnector dbc = new DBConnector();
        dbc.addScore(nickname, password, (int)time); //przekaznie informacji do bazy danych
        saveScoreField.setVisible(false);
        saveScoreButton.setVisible(false);
        isGoodLabel.setText("Pomyślnie zapisano wynik!"); //zapisywanie wyniku
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