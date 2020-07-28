package Movable;
import Game.GameObject;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Bullet extends GameObject {

    private BufferedImage bulletImg;
    private int x,y,vx,vy,angle;
    private boolean visible;
    private final int SPEED = 5;

    public Bullet(int x, int y, int vx, int vy, int angle, BufferedImage bulletImg){
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.angle = angle;
        this.bulletImg = bulletImg;
        this.visible = true;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public int getWidth(){
        return this.bulletImg.getWidth();
    }

    public int getHeight(){
        return this.bulletImg.getHeight();
    }

    public void update(){
        vx = (int) Math.round(SPEED * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(SPEED * Math.cos(Math.toRadians(angle)));
        x += vx ;
        y += vy ;
    }

    public void drawBullet(Graphics g){
        if(visible = true){
            AffineTransform shoot = AffineTransform.getTranslateInstance(x,y);
            shoot.rotate(Math.toRadians(this.angle), this.bulletImg.getWidth() / 2.0, this.bulletImg.getHeight() / 2.0);
            Graphics2D b2d = (Graphics2D) g;
            b2d.drawImage(this.bulletImg, shoot, null);
        }
    }
}
