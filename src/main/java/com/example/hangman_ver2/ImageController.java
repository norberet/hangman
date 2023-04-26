package com.example.hangman_ver2;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class ImageController {
    public List<Image> loadImages(){
        List<Image> images = new ArrayList<>();
        images.add(new Image("https://github.com/norberet/hangman/blob/a12cd05d7d9b28aa789bc60f295b03c629ce1242/src/main/resources/images/0.png"));
        images.add(new Image("https://github.com/norberet/hangman/blob/a12cd05d7d9b28aa789bc60f295b03c629ce1242/src/main/resources/images/1.png"));
        images.add(new Image("https://github.com/norberet/hangman/blob/a12cd05d7d9b28aa789bc60f295b03c629ce1242/src/main/resources/images/2.png"));
        images.add(new Image("https://github.com/norberet/hangman/blob/a12cd05d7d9b28aa789bc60f295b03c629ce1242/src/main/resources/images/3.png"));
        images.add(new Image("https://github.com/norberet/hangman/blob/a12cd05d7d9b28aa789bc60f295b03c629ce1242/src/main/resources/images/4.png"));
        images.add(new Image("https://github.com/norberet/hangman/blob/a12cd05d7d9b28aa789bc60f295b03c629ce1242/src/main/resources/images/5.png"));
        images.add(new Image("https://github.com/norberet/hangman/blob/a12cd05d7d9b28aa789bc60f295b03c629ce1242/src/main/resources/images/6.png"));
        images.add(new Image("https://github.com/norberet/hangman/blob/a12cd05d7d9b28aa789bc60f295b03c629ce1242/src/main/resources/images/7.png"));
        images.add(new Image("https://github.com/norberet/hangman/blob/a12cd05d7d9b28aa789bc60f295b03c629ce1242/src/main/resources/images/8.png"));
        images.add(new Image("https://github.com/norberet/hangman/blob/a12cd05d7d9b28aa789bc60f295b03c629ce1242/src/main/resources/images/9.png"));

        return images;
    }

}
