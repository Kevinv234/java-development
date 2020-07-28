package Game;
import Game.GameConstants;

import java.awt.image.BufferedImage;
import java.awt.*;


public class GameObject {
    private int x, y, vx, vy, angle, width, height;
    private boolean visible;
    private BufferedImage img;

    //constructor for the game
    public GameObject(){};

    public GameObject(int x, int y, int vx, int vy, int angle, BufferedImage img){

        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.angle = angle;
        this.img = img;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public int getWidth(){
        return this.img.getWidth();
    }

    public int getHeight(){
        return this.img.getHeight();
    }
}
