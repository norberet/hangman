package com.example.hangman_ver2;
import java.sql.*;

public class DBConnector {

    private final String username = "root";
    private final String password = "12345678";
    private final String URL = "jdbc:mysql://localhost:3306/hangman-game";

    public void addScore(String nickname, String word, int time){
        String query = "INSERT INTO score (nickname, word, time) VALUES ('"+ nickname + "', " + word +"', " + time + "');";
        try{
            Connection connection = DriverManager.getConnection(URL,username,password);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
            connection.close();
            printScores();
        }catch (SQLException e){
            System.out.println("Bład SQL");
            e.printStackTrace();
        }
    }
    public void printScores(){
        String query = "SELECT * FROM score";
        try{
            Connection connection = DriverManager.getConnection(URL,username,password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nickname = resultSet.getString("nickname");
                String word = resultSet.getString("word");
                int time = resultSet.getInt("time");
                System.out.printf("id: %d, nickname: %s, word: %s, time: %d%n", id, nickname, word, time);
            }
            resultSet.close();
            statement.close();
            connection.close();

        }catch (SQLException e){
            System.out.println("Bład SQL");
        }
    }

}
