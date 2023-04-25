package com.example.hangman_ver2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PasswordSetter extends MainMenuController{
    @FXML
    private TextField addWordField;
    @FXML
    private Label isSuccess;

    private final String file = "src/main/java/com/example/hangman_ver2/passwords.txt";
    public String getPassword(){
        List<String> password;
        try {
            FileReader reader = new FileReader(file);
            Scanner input = new Scanner(reader);
            password = new ArrayList<>();
            while (input.hasNext()){
                password.add(input.nextLine().toUpperCase());
            } //otwarcie pliku oraz wczytanie hasel do listy
            reader.close();

        }
        catch (IOException e){
            System.out.println("Nie znaleziono pliku");
            return "ERROR:001";

        }
        int index = (int)(Math.random()* password.size()); //wybieranie losoewgo slowa z listy
        System.out.println(password.get(index));
        System.out.println(password);
        return password.get(index);
    }
    public void addWordtoFile(){
        System.out.println(addWordField.getText());
        String newWord = addWordField.getText();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
            writer.write(newWord);
            writer.newLine();
            writer.close();
            isSuccess.setTextFill(Color.valueOf("#0e9e40"));
            isSuccess.setText("Dodano pomyślnie hasło: " + newWord);

        } catch (IOException e) {
            System.out.println("Nie odnaleziono pliku");
            isSuccess.setTextFill(Color.valueOf("#b90a0a"));
            isSuccess.setText("Błąd");
        }
    }

    @Override
    public void mainMenu(ActionEvent event) throws IOException {
        super.mainMenu(event);
    }
}
