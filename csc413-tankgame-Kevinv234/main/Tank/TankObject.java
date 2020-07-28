package Tank;
import Game.GameConstants;
import Game.GameObject;
import Game.MainGame;
import Movable.Bullet;
import Movable.Collision;
import com.sun.tools.javac.Main;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TankObject extends GameObject {

    private int x;
    private int y;
    private int vx;
    private int vy;
    private int angle;
    private int ID;
    private int health = 100;
    private int lives = 3;

    private final int R = 2;
    private final int ROTATIONSPEED = 3;

    private int i = 0;
    private int fireRate = 50;

    private BufferedImage img;
    private BufferedImage bullet;

    private boolean UpPressed;
    private boolean DownPressed;
    private boolean RightPressed;
    private boolean LeftPressed;
    private boolean shooting;
    private boolean isFiring;

    private long firingTimer = System.nanoTime();
    private final long FIRINGDELAY = 500;

    private ArrayList<Bullet> myBullet;
    private Collision tc = new Collision();


    public TankObject(int x, int y, int vx, int vy, int angle, int ID,  BufferedImage tankImg, BufferedImage bulletImg) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.ID = ID;
        this.img = tankImg;
        this.angle = angle;
        this.bullet = bulletImg;
        this.myBullet = new ArrayList<>();
    }


    void toggleUpPressed() {
        this.UpPressed = true;
    }

    void toggleDownPressed() {
        this.DownPressed = true;
    }

    void toggleRightPressed() {
        this.RightPressed = true;
    }

    void toggleLeftPressed() {
        this.LeftPressed = true;
    }

    void toggleShooting(){ this.shooting = true;}

    void unToggleUpPressed() {
        this.UpPressed = false;
    }

    void unToggleDownPressed() {
        this.DownPressed = false;
    }

    void unToggleRightPressed() {
        this.RightPressed = false;
    }

    void unToggleLeftPressed() {
        this.LeftPressed = false;
    }

    void unToggleShooting(){ this.shooting = false;}

    public void setFiring(boolean firing){
        this.isFiring = firing;
    }

    //Updates keys pressed by the keyboard
    public void update() {


        //detection for collisions starts here
        tc.bulletCollision(MainGame.t1, MainGame.t2);
        tc.bulletCollision(MainGame.t2, MainGame.t1);
        tc.wallCollision(MainGame.t1);
        tc.wallCollision(MainGame.t2);

        if (this.UpPressed) {
            this.moveForwards();
        }
        if (this.DownPressed) {
            this.moveBackwards();
        }

        if (this.LeftPressed) {
            this.rotateLeft();
        }
        if (this.RightPressed) {
            this.rotateRight();
        }

        if (this.shooting) {
            i++;
            if (i % fireRate == 0) {    //regulates the amount of bullets shot
                this.shootBullet();
            }
        }
    }

    public Rectangle getRectangle(){
        return new Rectangle(x , y , 32, 32);
    }

    private void rotateLeft() {
        this.angle -= this.ROTATIONSPEED;
    }

    private void rotateRight() {
        this.angle += this.ROTATIONSPEED;
    }


    private void shootBullet(){
        Bullet shot = new Bullet( getCenterX(),getCenterY(), vx ,vy , this.angle, bullet);
        myBullet.add(shot);
    }

    public void Collision(boolean xColl, boolean yColl, boolean rightColl, boolean leftColl, int pos){

        if(xColl){
            x = pos - getWidth();

        }

        if(yColl){
            y = pos - getHeight();

        }

        if(rightColl){
            y = pos + 10;
        }
        if(leftColl){
            x = pos + 10;
        }
    }


    private void moveBackwards() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x -= vx;
        y -= vy;
        checkBorder();
    }

    private void moveForwards() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x += vx;
        y += vy;
        checkBorder();
    }

    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public int getAngle(){
        return this.angle;
    }
    public int getTankID(){
        return this.ID;
    }
    public int getHealth(){
        return this.health;
    }
    public int getLives(){
        return this.lives;
    }
    public int getCenterX() {
        return this.x + this.img.getWidth()/2;
    }

    public int getCenterY() {
        return this.y + this.img.getHeight()/2;
    }
    public boolean getIsFiring() {
        return this.isFiring;
    }

    public void setX(int i){
        this.x = i ;
    }
    public void setY(int i){
        this.y = i;
    }

    public ArrayList<Bullet> getMyBullet(){
        return this.myBullet;
    }


    public int getWidth(){
        return this.img.getWidth();
    }

    public int getHeight(){
        return this.img.getHeight();
    }
    private void checkBorder() {
        
        if (x < GameConstants.TILE_MAP) {
            x = GameConstants.TILE_MAP;
        }
        if (x >= GameConstants.MAP_WIDTH - 88) {
            x = GameConstants.MAP_WIDTH - 88;
        }
        if (y < GameConstants.TILE_MAP) {
            y = GameConstants.TILE_MAP;
        }
        if (y >= GameConstants.MAP_HEIGHT - 80) {
            y = GameConstants.MAP_HEIGHT - 80;
        }
    }

    @Override
    public String toString() {
        return "x=" + x + ", y=" + y + ", angle=" + angle;
    }


    public void drawImage(Graphics g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), this.img.getWidth() / 2.0, this.img.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.img, rotation, null);

    }
}