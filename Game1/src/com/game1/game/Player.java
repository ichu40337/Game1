package com.game1.game;

import com.game1.IO.Input;
import com.game1.graphics.SpriteSheet;
import com.game1.graphics.Texture;
import com.game1.graphics.spriye;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Player extends  Entity{
    public static final int SPRITE_SCALEE_SCALE=16;
    public  final int sprite_Cout=1;

        private enum Heading
        {
            NORTH(0*SPRITE_SCALEE_SCALE,0*SPRITE_SCALEE_SCALE,1*SPRITE_SCALEE_SCALE,1*SPRITE_SCALEE_SCALE),
            EAST(6*SPRITE_SCALEE_SCALE,0*SPRITE_SCALEE_SCALE,1*SPRITE_SCALEE_SCALE,1*SPRITE_SCALEE_SCALE),
            SOUTH(4*SPRITE_SCALEE_SCALE,0*SPRITE_SCALEE_SCALE,1*SPRITE_SCALEE_SCALE,1*SPRITE_SCALEE_SCALE),
            WEST(2*SPRITE_SCALEE_SCALE,0*SPRITE_SCALEE_SCALE,1*SPRITE_SCALEE_SCALE,1*SPRITE_SCALEE_SCALE);

    private int x,y,h,w;

    Heading(int x, int y, int h, int w)
        {
    this.x=x;
    this.y=y;
    this.w=w;
    this.h=h;
        }
        protected BufferedImage texture(Texture atlas)
        {
            return  atlas.cut(x,y,w,h);
        }
        }
    private Heading heading;
    private Map<Heading, spriye> spriteMap;
private float scale;
private  float speed;
    public Player( float x, float y, Texture atlas, float scale, float speed)
    {
        super(EntityType.Player, x, y);

        heading= Heading.NORTH;
        spriteMap= new HashMap<Heading,spriye>();

        this.scale=scale;
        this.speed=speed;

        for (Heading h:Heading.values())
        {
            SpriteSheet sheet= new SpriteSheet(h.texture(atlas),sprite_Cout,SPRITE_SCALEE_SCALE);
            spriye sprite= new spriye(sheet,scale);
            spriteMap.put(h,sprite);
        }
    }

    @Override
    public void update(Input input)
    {
        float newX=x;
        float newY=y;
        if(input.getKey(KeyEvent.VK_W))
        {
            newY -= speed;
            heading= Heading.NORTH;;
        }
        else if(input.getKey(KeyEvent.VK_D))
        {
            newX += speed;
            heading= Heading.EAST;;
        }
        else if(input.getKey(KeyEvent.VK_S))
        {
            newY += speed;
            heading= Heading.SOUTH;;
        }
        else if(input.getKey(KeyEvent.VK_A))
        {
            newX  -= speed;
            heading = Heading.WEST;
        }
        if(newX<0)
        {
            newX=0;
        } else if (newX>=Game.WIDTH-SPRITE_SCALEE_SCALE*scale)
        {
            newX=Game.WIDTH-SPRITE_SCALEE_SCALE*scale;
        }
        if(newY<0)
        {
            newY=0;
        } else if (newY>=Game.HEIGTH-SPRITE_SCALEE_SCALE*scale)
        {
            newY=Game.WIDTH-SPRITE_SCALEE_SCALE*scale;
        }
         x=newX;
        y= newY;
    }

    @Override
    public void render(Graphics2D g) {
        spriteMap.get(heading).render(g,x,y);


    }
}
