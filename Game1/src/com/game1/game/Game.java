package com.game1.game;

import com.game1.IO.Input;
import com.game1.com.game1.utils.Time;
import com.game1.display.Display;
import com.game1.graphics.SpriteSheet;
import com.game1.graphics.Texture;
import com.game1.graphics.spriye;

import javax.print.attribute.standard.NumberUp;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Game implements Runnable{
    public static final int WIDTH=800;
    public static final int HEIGTH=600;
    public static final String TITLE="Games";
    public static final int Clear_Color=0xff000000;
    public static final int numBuffer=3;
    public static final float UPD_RATE=60.0f;
    public static final float UPD_INTERVAL= Time.second/ UPD_RATE;
    public static final long IDLE_TIME=1;
     public static final String ATLAS_FILE_NAME= "texture_atlas.png";
private Graphics2D graphics;
    private boolean running;
    private Thread gameThread;

    private Input input;
    private Texture atlas;;
    private  Player player;

      public Game()
      {
          running=false;
          Display.create(WIDTH, HEIGTH,TITLE,Clear_Color, numBuffer);
            graphics=Display.getGraphics();
            input = new Input();
            Display.addInputListener(input);
            atlas=new Texture(ATLAS_FILE_NAME);
           player=new Player(300,300,atlas,3, 3);
      }
      public synchronized void start()
      {
          if(running)
          return;
          running=true;
          gameThread=new Thread(this);
          gameThread.start();
      }
      public synchronized  void  stop()
      {
          if (!running)
              return;
          running=false;
          try {
              gameThread.join();
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
          cleanUp();
      }
      private void update()
      {
            player.update((input));
      }
      private void render()
      {
        Display.clear();
player.render(graphics);
          Display.swapBuff();

      }

      public void run() {
          int fps = 0;
          int upd = 0;
          int updl = 0;
          float delta = 0;
          long count = 0;
          long lastTime = Time.get();
          while (running) {
              long now = Time.get();
              long elapsdTime = now - lastTime;
              lastTime = now;
              count += elapsdTime;
              boolean render = false;
              delta += (elapsdTime / UPD_INTERVAL);
              while (delta > 1) {
                  update();
                  upd++;
                  delta--;
                  if (render) {
                      upd++;
                  } else {
                      render = true;
                  }
              }
              if (render) {
                  render();
                  fps++;
              } else {
                  try {
                      Thread.sleep(IDLE_TIME);
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }
              }
              if (count >= Time.second) {
                  Display.setTitle(TITLE + "| FPS:" + fps + "| UPD: " + upd + "| Updl: " + updl);
                  upd=0;
                  fps=0;
                  updl=0;
                  count=0;

              }
          }
          }
          private void cleanUp ()
          {

          }

      }
