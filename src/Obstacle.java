import java.awt.*;

public class Obstacle extends ScreenObject {
    public Obstacle(int x, int y, int size){
        super(x,y,size);
    }

    public void drawObstacle(Graphics g){
        g.fillRect(getX(),getY(),getSize(),getSize());
    }
    public boolean collides(ScreenObject check){
        // overloaded method to make sure there is enough space between obstacles for a character to get through
        return super.collides(check) || getDistance(check) <= (Math.sqrt(2)*Character.DEFAULT_SIZE);
    }


}
