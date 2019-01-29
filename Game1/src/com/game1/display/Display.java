package com.game1.display;

import com.game1.IO.Input;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

public abstract class Display {
    private static boolean created=false;
    private static JFrame window;
    private static Canvas content;

    private static BufferedImage buff;
    private static int[] buffData;
    private static Graphics buffGraphics;
    private static int cleaColor;

    private static BufferStrategy buffStartegy;
    public static void create (int width, int height, String title, int _clearColor, int numBuffer)
    {
        if (created)

            return;
        window=new JFrame(title);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        content= new Canvas();

        Dimension size= new Dimension(width,height);
        content.setPreferredSize(size);

        window.setResizable(false);
        window.getContentPane().add(content);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        buff= new BufferedImage(width, height,BufferedImage.TYPE_INT_ARGB);
        buffData=((DataBufferInt)buff.getRaster().getDataBuffer()).getData();
        buffGraphics=buff.getGraphics();
        cleaColor= _clearColor;

        content.createBufferStrategy(numBuffer);
        buffStartegy= content.getBufferStrategy();
        ((Graphics2D)buffGraphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
        created=true;
    }
    public static void clear()
    {
        Arrays.fill(buffData, cleaColor);
    }
    public static void swapBuff(){
        Graphics g= buffStartegy.getDrawGraphics();
        g.drawImage(buff,0,0,null);
        buffStartegy.show();

    }
    public static Graphics2D getGraphics()
    {
        return (Graphics2D) buffGraphics;
    }
    public static  void destroy()
    {
        if (!created)
        return;
        window.dispose();
    }
    public static void setTitle(String title){
        window.setTitle(title);
    }
    public static void addInputListener(Input inputListener)

    {
        window.add(inputListener);
    }
}
