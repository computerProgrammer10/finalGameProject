import java.awt.*;
import java.util.ArrayList;
// decided to not add projectiles to the game
// this is the layout for projectiles
public class Projectile extends ScreenObject{
    private int xSpeed, ySpeed, speed, life, xEnd, yEnd;
    private boolean up, right;
    public Projectile(int x, int y, int size, int xEnd, int yEnd, int speed, int life){
        super(x,y,size);
        if (x < xEnd) right = true; else right = false;
        if (y < yEnd) up = true; else up = false;
         this.xEnd = xEnd; this.yEnd = yEnd; this.speed = speed; this.life = life;
    }
    public void drawProjectile(Graphics g){
        g.setColor(Color.blue);
        g.fillOval(super.getX(), super.getY(), super.getSize(), super.getSize());
    }
    public boolean moveProjectile(ArrayList<Obstacle> obstacles, Graphics g){
        // this is a boolean because it returns whether or not the bullet is still allowed to move
        // it will return true to signal that the bullet is supposed to be destroyed
        // this happens if the bullet's life goes to 0 or it attacks an obstacle
        if (life>=0) {
            for (Obstacle hi: obstacles){
                if (collides(hi)){
                    obstacles.remove(hi);
                    return true;
                }
            }
            // code to move here...

            life--;
            if (right){
                super.setX(super.getX()+1);
            }else{
                super.setX(super.getX()-1);
            }
            if (up){
                super.setY(super.getY()+1);
            }else{
                super.setY(super.getY()-1);
            }

            return false;
        }
        return true;
    }


}
