package main;

import Entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

    //screen settings
    final int originalTileSize = 16; //16x16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48x48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768
    final int screenHeight = tileSize * maxScreenRow; // 576

    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread; //game time class
    Player Player = new Player(this, keyH);


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); //renders better for JPanel
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }


    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        //long timer = 0;
        //int drawCount = 0;

        //only allow inputs over interval of system time
        while(gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            //timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
                //drawCount++;
            }

            //FPS Counter
            /* if(timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                time = 0;
                drawCount = 0;
            } */

        }
    }


    public void update() {
        Player.update();
    }


    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        Player.draw(g2);
        g2.dispose(); //get rid of system mem of graphics to save ram
    }

}