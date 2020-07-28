package Game;
import Tank.*;
import Map.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import static javax.imageio.ImageIO.read;
import java.util.ArrayList;

public class MainGame extends JPanel{

    private Launcher lf;
    private long tick = 0;

    private JFrame jf;
    private BufferedImage world;
    private BufferedImage wall1, wall2;
    private BufferedImage heart, heart2;
    private BufferedImage bullet1, bullet2;
    private BufferedImage t1img, t2img;

    private mapWalls mapWall;
    public static TankObject t1, t2;

    private Image backgroundImg;
    private Graphics2D buffer;

    public static ArrayList<mapWalls> wallsArrayList = new ArrayList<>();
    public static ArrayList<mapWalls> heartArrayList = new ArrayList<>();

    /**
     * @param args
     * Main function of the game, this is where the thread starts and MainGame is created
     *
     */
    public static void main(String[] args){
        //initiate the game and objects of the game
        Thread x;
        final MainGame tankWarz = new MainGame();
        tankWarz.init();

        try{
            while(true){
                t1.update();   // update both tanks
                t2.update();
                tankWarz.repaint();     //redraw the game
                System.out.println(MainGame.t1);
                Thread.sleep(1000 / 144);
            }
        } catch(InterruptedException ignored){

        }
    }

    /**
     *
     */
    private void init(){
        //create the JFrame and the current world we have going on
        this.jf = new JFrame("tankWarz");
        this.world = new BufferedImage(GameConstants.MAP_WIDTH, GameConstants.MAP_HEIGHT, BufferedImage.TYPE_INT_RGB);

        loadImages();

        //Tank objects player 1 and player 2 along with controls
        t1 = new TankObject( 150, 150 , 0 , 0 ,0 ,1, t1img, bullet1);
        t2 = new TankObject(1750, 1250, 0 , 0 , 0 ,2, t2img, bullet2);


        TankController tc1 = new TankController(t1, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_F);
        TankController tc2 = new TankController(t2, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_ENTER);

        //Check if the files are read correctly and set the JFrame
        System.out.println("Setting the frame ....");


        //this funcitons maps out all walls and powerups
        for(int row = 0; row < mapWalls.map.length; row++){
            for(int col = 0; col < mapWalls.map[row].length; col++){

                //creating a array for all unbreakable the walls
                if(mapWalls.map[row][col] == 1){
                    mapWall = new mapWalls(row * 32, col *32, wall1);
                    wallsArrayList.add(mapWall);
                }

                //creating an array for all breakable walls
                else if(mapWalls.map[row][col] == 2){
                    mapWall = new mapWalls(row * 32, col * 32, wall2);
                    wallsArrayList.add(mapWall);
                }

                //creating an array for health power ups.
                else if(mapWalls.map[row][col] == 3){
                    mapWall = new mapWalls(row * 32 , col * 32, heart);
                    heartArrayList.add(mapWall);
                }
            }
        }


        this.jf.setLayout(new BorderLayout());
        this.jf.add(this);

        this.jf.addKeyListener(tc1);
        this.jf.addKeyListener(tc2);


        this.jf.setSize(GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT + 30);
        this.jf.setResizable(false);

        jf.setLocationRelativeTo(null);
        this.jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jf.setVisible(true);

    }

    private void loadImages(){

        //Try catch for all the files we are going to be reading
        try{
            System.out.println(System.getProperty("user.dir"));

            t1img = read(new File("resources/tank1.png"));
            t2img = read(new File("resources/tank1.png"));
            backgroundImg = read(new File("resources/background.jpg"));
            wall1 = read(new File("resources/Wall1.gif"));
            wall2 = read(new File("resources/Wall2.gif"));
            heart = resize(read(new File("resources/Hearts/basic/heart.png")), 35,35);
            heart2 =  resize(read( new File("resources/Hearts/basic/heart.png")), 25,25);
            bullet1 = read(new File("resources/bullet1.png"));
            bullet2 = read(new File("resources/bullet2.png"));

        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param img
     * @param newW
     * @param newH
     * @return BufferedImage
     *
     * function is used for scaling images
     */
    private static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }



    /**
     * @param graphics
     * This is the main function where we are going to be rendering all main components for the game.
     *
     */
    @Override
    public void paintComponent(Graphics graphics) {
        //Render world
        //Super the function to render graphics
        backgroundImg.getGraphics();
        Graphics2D render = (Graphics2D) graphics;
        buffer = world.createGraphics();
        super.paintComponent(render);

        //Set background to white
        buffer.setColor(Color.white);
        buffer.fillRect(0,0, GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT);

        //Set the left and right half of the screen
        BufferedImage leftHalf = drawTank(t1);
        BufferedImage rightHalf = drawTank(t2);


        //create the minimap we are using in center of the jframe
        drawGame();
        t1.drawImage(buffer);
        t2.drawImage(buffer);

        render.drawImage(leftHalf, 0 ,0, null);
        render.drawImage(rightHalf,640,0, null);


        Image scalemap = world.getScaledInstance(300,200, Image.SCALE_FAST);
        render.drawImage(scalemap, GameConstants.SCREEN_WIDTH / 2  - 130, GameConstants.SCREEN_HEIGHT / 2 + 280 , 250 , 200 , null);

        drawHealth(render);

    }

    // the function draws most of the game, links multiple functions
    private void drawGame(){
        drawBackGroundImage();
        drawWalls();
        drawPowerups();
        drawBullet();
    }


    /**
     * @param tank
     * @return Buffered Image
     */
    private BufferedImage drawTank(TankObject tank){
        BufferedImage tankDrawing;
        int offsetMaxX = GameConstants.MAP_WIDTH - (GameConstants.SCREEN_WIDTH / 2 );
        int offsetMaxY = GameConstants.MAP_HEIGHT - (GameConstants.SCREEN_HEIGHT);
        int camX = tank.getX()  - (GameConstants.SCREEN_WIDTH/ 4);
        int camY = tank.getY() - (GameConstants.SCREEN_HEIGHT/ 2);


        //set if statements for if the camera goes out of bounds
        if(camX > offsetMaxX){
            camX = offsetMaxX;
        }
        else if( camX < 0){
            camX = 0;
        }
        if(camY > offsetMaxY){
            camY = offsetMaxY;
        }
        else if(camY < 0){
            camY = 0;
        }

        tankDrawing = world.getSubimage(camX, camY , GameConstants.SCREEN_WIDTH/2  , GameConstants.SCREEN_HEIGHT);
        return tankDrawing;
    }
    private void drawHealth(Graphics g2){

        int t1Health = t1.getHealth();
        int t2Health = t2.getHealth();

        int t1Lives = t1.getLives();
        int t2Lives = t2.getLives();

        int t1HealthX = 0;
        int t1HealthY = 930;

        int t2HealthX = 1080;
        int t2HealthY = 930;
        int healthWidth = 200;
        int healthHeight = 30;

        int coordinateOffset = 3;
        int sizeOffset = 8;

        //Health Outline
        g2.setColor(Color.black);
        g2.fillRect(t1HealthX, t1HealthY, healthWidth,healthHeight);
        g2.fillRect(t2HealthX, t2HealthY, healthWidth, healthHeight);

        //Secondary Health Color
        g2.setColor(Color.DARK_GRAY);
        g2.fillRect(t1HealthX + coordinateOffset  , t1HealthY + coordinateOffset, healthWidth - sizeOffset, healthHeight - sizeOffset);
        g2.fillRect(t2HealthX + coordinateOffset , t2HealthY + coordinateOffset, healthWidth - sizeOffset, healthHeight - sizeOffset);

        //Primary Health Bar
        g2.setColor(Color.GREEN);
        g2.fillRect(t1HealthX  + coordinateOffset  , t1HealthY + coordinateOffset, (t1Health * 2) - sizeOffset, healthHeight - sizeOffset);
        g2.fillRect(t2HealthX + coordinateOffset , t2HealthY + coordinateOffset, (t2Health  * 2 ) - sizeOffset, healthHeight - sizeOffset);

        g2.setColor(Color.BLUE);
        g2.setFont(new Font("default", Font.BOLD, 18));
        g2.drawString("PLAYER 1" , 65, t1HealthY + 20 );

        g2.setColor(Color.RED);
        g2.setFont(new Font("default", Font.BOLD, 18));
        g2.drawString("PLAYER 2" , 1135, t1HealthY + 20);

        int t1LifeX = 0;
        int t1LifeY = 910;
        int t10Offset = 25;

        for(int i = 0 ; i < t1Lives; i++){
            g2.drawImage(heart2, t1LifeX + ( i * t10Offset) , t1LifeY, null);
        }

        int t2LifeX = 1080;
        int t2LifeY = 910;

        for(int i = 0 ; i < t2Lives; i++){
            g2.drawImage(heart2, t2LifeX + (i * t10Offset), t2LifeY, null);
        }
    }

    /**
     *
     */
    private void drawBackGroundImage(){
        for(int row = 0; row < GameConstants.MAP_WIDTH; row += 320){
            for(int col = 0; col < GameConstants.MAP_HEIGHT; col += 240){
                buffer.drawImage(backgroundImg, (row ), (col), 320, 240, null);
            }
        }
    }

    /**
     *
     */
    private void drawPowerups(){
        for(int i = 0; i < heartArrayList.size();i++){
            heartArrayList.get(i).drawImage(buffer);
        }
    }

    /**
     *
     */
    // make game constants in a public class
     private void drawWalls(){
         for(int i = 0 ; i < wallsArrayList.size(); i++){
             wallsArrayList.get(i).drawImage(buffer);
         }

     }

     private void drawBullet(){

         if (t1.getMyBullet() != null) {
             for (int i = 0; i < t1.getMyBullet().size(); i++) {

                 if (this.t1.getMyBullet().get(i).getX() < - 20 || this.t1.getMyBullet().get(i).getY() < - 20) {   //check if
                     t1.getMyBullet().remove(i);
                 }
                 else {
                     this.t1.getMyBullet().get(i).drawBullet(buffer);
                     this.t1.getMyBullet().get(i).update();
                 }
             }
         }
         if (t2.getMyBullet() != null) {

             for (int i = 0; i < t2.getMyBullet().size(); i++) {

                 if (this.t2.getMyBullet().get(i).getX() < 0 || this.t2.getMyBullet().get(i).getY() < 0) {
                     t2.getMyBullet().remove(i);
                 } else {
                     this.t2.getMyBullet().get(i).drawBullet(buffer);
                     this.t2.getMyBullet().get(i).update();
                 }
             }
         }
     }
}