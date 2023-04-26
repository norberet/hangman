package com.example.hangman_ver2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.List;

public class ScoreTableController extends MainMenuController{
    @FXML
    private Label placeLabel = new Label();
    private DBConnector connector = new DBConnector();
    public ScoreTableController(){

    }
    public void showScores(){
        connector.printScores(true);
        placeLabel.setText("1.\n2.\n3.\n4.\n5.\n6.\n7.\n8.\n9.\n10.\n11.\n12.\n13.\n14.\n15.");
        List<String> nicknames = connector.getNickname();
        List<String> words = connector.getWord();
        List<Integer> time = connector.getTime();

    }

    @Override
    public void mainMenu(ActionEvent event) throws IOException {
        super.mainMenu(event);
    }
}
