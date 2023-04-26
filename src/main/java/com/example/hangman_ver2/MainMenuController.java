package com.example.hangman_ver2;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {
    private Stage stage;
    private Scene scene;


    public void mainMenu(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HangmanGame.class.getResource("main-menu-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setTitle("Wisielec");
        stage.show();
    }
    public void newGame(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HangmanGame.class.getResource("game-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setTitle("Wisielec");
        stage.show();
    }
    public void addWord(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HangmanGame.class.getResource("add-word-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setTitle("Wisielec");
        stage.show();
    }
    public void scoreTable(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HangmanGame.class.getResource("score-table-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setTitle("Wisielec");
        ScoreTableController scoreTableController = fxmlLoader.getController();
        scoreTableController.showScores();
        stage.show();
    }


}
