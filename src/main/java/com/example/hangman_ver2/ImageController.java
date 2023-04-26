package com.example.hangman_ver2;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class ImageController {
    public List<Image> loadImages(){
        List<Image> images = new ArrayList<>();
        images.add(new Image(getClass().getResourceAsStream("src/main/resources/images/0.png")));
        images.add(new Image(getClass().getResourceAsStream("src/main/resources/images/1.png")));
        images.add(new Image(getClass().getResourceAsStream("src/main/resources/images/2.png")));
        return images;
    }

}
