import java.awt.*;

public class Obstacle extends ScreenObject {
    public Obstacle(int x, int y, int size){
        super(x,y,size);
    }

    public void drawObstacle(Graphics g){
        g.fillRect(getX(),getY(),getSize(),getSize());
    }


}
