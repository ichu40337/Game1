package com.game1.graphics;

import com.game1.IO.Resours;

import java.awt.image.BufferedImage;

public class Texture {
    BufferedImage image;

    public Texture(String imageName) {


        image = Resours.loadImage(imageName);
    }

    public BufferedImage cut(int x, int y, int w, int h) {
     return    image.getSubimage(x, y, w, h);
    }
}

