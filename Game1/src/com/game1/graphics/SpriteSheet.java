package com.game1.graphics;

import java.awt.image.BufferedImage;

public class SpriteSheet {
    private BufferedImage sheet;
    private int spriteCount;
    private int scale;
    private int sptiteInWid;
public  SpriteSheet(BufferedImage sheet, int spriteCount, int scale)
{
    this.sheet=sheet;
    this.spriteCount=spriteCount;
    this.scale=scale;
    this.sptiteInWid=sheet.getWidth()/scale;

}
public BufferedImage getSprite(int index)
{
    index= index % spriteCount;
    int x= index %sptiteInWid*scale;
    int y= index/sptiteInWid*scale;

    return sheet.getSubimage(x,y,scale,scale);
}
}
