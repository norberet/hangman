package com.example.hangman_ver2;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBConnector{

    private final String username = "root";
    private final String password = "12345678";
    private final String URL = "jdbc:mysql://localhost:3306/hangman-game";
    private List<Integer> id = new ArrayList<>();
    private List<String> nickname = new ArrayList<>();
    private List<String> word = new ArrayList<>();
    private List<Integer> time = new ArrayList<>();

    public void addScore(String nickname, String word, int time){
        String query = "INSERT INTO score (nickname, word, time) VALUES ('"+ nickname + "', '" + word +"', '" + time + "');";
        try{
            Connection connection = DriverManager.getConnection(URL,username,password);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
            connection.close();
        }catch (SQLException e){
            System.out.println("Bład SQL");
            e.printStackTrace();
        }
    }
    public void printScores(boolean sort){
        String query;
        if(sort){
            query = "SELECT * FROM score ORDER BY time";
        }
        else{
            query = "SELECT * FROM score ORDER BY word DESC";
        }

        try{
            Connection connection = DriverManager.getConnection(URL,username,password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                id.add(resultSet.getInt("id"));
                nickname.add(resultSet.getString("nickname"));
                word.add(resultSet.getString("word"));
                time.add(resultSet.getInt("time"));
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

    public List<Integer> getTime() {
        return time;
    }
}
