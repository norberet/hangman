package com.example.hangman_ver2;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBConnector{

    private final String username = "root";
    private final String password = "12345678";
    private final String URL = "jdbc:mysql://localhost:3306/hangman-game";
    //private final List<Integer> id = new ArrayList<>();
    private final List<String> nickname = new ArrayList<>();
    private final List<String> word = new ArrayList<>();
    private final List<Integer> time = new ArrayList<>();
    private final List<String> timeS = new ArrayList<>();

    public void addScore(String nickname, String word, int time){
        String query = "INSERT INTO score (nickname, word, time) VALUES ('"+ nickname + "', '" + word +"', '" + time + "');";
        try{
            Connection connection = DriverManager.getConnection(URL,username,password);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
            connection.close();
            System.out.println("Zapisano");
        }catch (SQLException e){
            System.out.println("Bład SQL");
            e.printStackTrace();
        }
    }
    public void printScores(){

        String query = "SELECT * FROM score ORDER BY time";

        try{
            Connection connection = DriverManager.getConnection(URL,username,password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                //id.add(resultSet.getInt("id"));
                nickname.add(resultSet.getString("nickname"));
                word.add(resultSet.getString("word"));
                time.add(resultSet.getInt("time"));
            }
            for (Integer integer : time) { //zmieniam int na stringa i ustawiam czas w formacie 00:00 dla kazdego indexu listy
                timeS.add(integer / 60 + ":" + (integer - (integer / 60) * 60));
            }
            resultSet.close();
            statement.close();
            connection.close();
            System.out.println("Import pomyślny");

        }catch (SQLException e){
            System.out.println("Bład SQL");
        }
    }

    public List<String> getNickname() {
        return nickname;
    }

    public List<String> getWord() {
        return word;
    }

    public List<String> getTime() {
        return timeS;
    }
}
