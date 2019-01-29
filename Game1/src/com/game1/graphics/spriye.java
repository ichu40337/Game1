package com.game1.graphics;

import java.awt.*;
import java.awt.image.BufferedImage;

public class spriye {

    private SpriteSheet sheet;
    private float scale;
    public spriye(SpriteSheet sheet, float scale)
    {
        this.scale=scale;
        this.sheet=sheet;
    }
    public void render(Graphics2D g,float x, float y)
    {
        BufferedImage image=sheet.getSprite(0);
        g.drawImage(sheet.getSprite(0),(int)(x),(int)(y),(int)(image.getWidth()*scale),(int)(image.getHeight()*scale),null);
    }

}
