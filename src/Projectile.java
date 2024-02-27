import java.awt.*;
import java.util.ArrayList;

public class Projectile extends ScreenObject{
    private int xSpeed, ySpeed, speed, life;
    public Projectile(int x, int y, int size, int xEnd, int yEnd, int speed, int life){
        super(x,y,size); this.speed = speed; this.life = life;
    }

    public boolean moveBullet(ArrayList<Obstacle> obstacles, Graphics g){
        // this is a boolean because it returns whether or not the bullet is still allowed to move
        // it will return true to signal that the bullet is supposed to be destroyed
        // this happens if the bullet's life goes to 0 or it attacks an obstacle
        if (life<=0) {
            for (Obstacle hi: obstacles){
                if (collides(hi)){
                    obstacles.remove(hi);
                    return true;
                }
            }
            life--;

            return false;
        }
        return true;
    }


}
