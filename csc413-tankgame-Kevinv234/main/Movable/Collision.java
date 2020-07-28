package Movable;
import Game.MainGame;
import Tank.TankObject;
import Map.mapWalls;
import java.awt.*;

public class Collision {

    public void bulletCollision(TankObject t1, TankObject t2){
        Rectangle tank2Box = t2.getRectangle();

        for(int i = 0; i < t1.getMyBullet().size() - 1; i++){

            Bullet player1Bullet = t1.getMyBullet().get(i);
            Rectangle bulletBox1 = new Rectangle( player1Bullet.getX(), player1Bullet.getY(),
                    player1Bullet.getWidth(), player1Bullet.getHeight());

            if(bulletBox1.intersects(tank2Box)){
                t1.getMyBullet().remove(i);
            }

            for( int k = 0; k < MainGame.wallsArrayList.size(); k++){

                    mapWalls wall = MainGame.wallsArrayList.get(k);
                int left = wall.getxVector();
                int top = wall.getyVector();

                Rectangle wallBox = new Rectangle(left, top, wall.getWidth(), wall.getHeight());

                if(bulletBox1.intersects(wallBox)){
                    //MainGame.wallsArrayList.remove(k);

                    if(t1.getMyBullet().get(i) != null){
                        t1.getMyBullet().remove(i);
                    }
                }
            }
        }
    }


    public void wallCollision(TankObject tank){
        Rectangle tankBox = tank.getRectangle();

        for(int i = 0; i < MainGame.wallsArrayList.size(); i++){

            mapWalls walls = MainGame.wallsArrayList.get(i);
            int left = walls.getxVector();
            int top = walls.getyVector();
            int bottom = walls.getyVector() + walls.getHeight();
            int right = walls.getxVector() + walls.getWidth();

            Rectangle wallBox = new Rectangle(left, top, walls.getWidth(), walls.getHeight());

            if(tankBox.intersects(wallBox)){

                if(tank.getX() + tank.getWidth() == left + 20 ){
                    tank.Collision(true, false ,false ,false , left);
                }
                else if (tank.getY() + tank.getHeight() == top + 20) {
                    tank.Collision(false, true, false ,false ,top);
                }
                else if(tank.getY() == bottom - 0){
                    tank.Collision(false, false, true, false, bottom);
                }


                else if(tank.getX() == right -0){
                    tank.Collision(false,false,false,true, right);
                }
            }
        }

        for(int i = 0; i < MainGame.heartArrayList.size();i++){

            mapWalls heart = MainGame.heartArrayList.get(i);

            int left = heart.getxVector();
            int top = heart.getyVector();

            Rectangle heartBox = new Rectangle(left, top, heart.getWidth(), heart.getHeight());

            if(tankBox.intersects(heartBox)){
                MainGame.heartArrayList.remove(i);
            }
        }
    }


}
