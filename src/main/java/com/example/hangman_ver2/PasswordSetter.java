package com.example.hangman_ver2;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PasswordSetter {
    public String getPassword(){
        List<String> password;
        try {
            FileReader reader = new FileReader("src/main/java/com/example/hangman_ver2/passwords.txt");
            Scanner input = new Scanner(reader);
            password = new ArrayList<>();
            while (input.hasNext()){
                password.add(input.nextLine().toUpperCase());
            } //otwarcie pliku oraz wczytanie hasel do listy

        }
        catch (IOException e){
            System.out.println("Nie znaleziono pliku");
            return "ERROR:001";

        }
        int index = (int)(Math.random()* password.size()); //wybieranie losoewgo slowa z listy
        System.out.println(password.get(index));
        return password.get(index);
    }
}
