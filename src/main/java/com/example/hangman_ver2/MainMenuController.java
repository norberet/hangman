package com.example.hangman_ver2;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {
    private Stage stage;
    private Scene scene;


    public void mainMenu(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-menu-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setTitle("Wisielec");
        stage.show();
    }
    public void newGame(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("game-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setTitle("Wisielec");
        stage.show();
    }
    public void addWord(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("add-word-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setTitle("Wisielec");
        stage.show();
    }


}
