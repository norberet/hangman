package com.example.hangman_ver2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.List;

public class ScoreTableController extends MainMenuController{
    @FXML
    private Label placeLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label wordLabel;
    @FXML
    private Label timeLabel;

    private final DBConnector connector = new DBConnector();

    public void showScores(){
        connector.printScores();
        placeLabel.setText("1.\n2.\n3.\n4.\n5.\n6.\n7.\n8.\n9.\n10.\n11.\n12.\n13.\n14.\n15."); //miesci sie 15 rekordow
        List<String> nicknames = connector.getNickname();
        nameLabel.setText(getValues(nicknames)); //drukujemy wyniki
        List<String> words = connector.getWord();
        wordLabel.setText(getValues(words));
        List<String> time = connector.getTime();
        timeLabel.setText(getValues(time));
    }
    public String getValues(List<String> value){ //zwraca nicknames oraz word
        StringBuilder values = new StringBuilder();
        for (String s : value) {
            values.append(s).append("\n");
        }
        if(value.size() < 15){
            values.append("<brak>\n".repeat(15 - (value.size() - 1))); //jesli nie ma wyniku drukujemy stosowna informacje
        }
        return String.valueOf(values);
    }

    @Override
    public void mainMenu(ActionEvent event) throws IOException {
        super.mainMenu(event);
    }
}
