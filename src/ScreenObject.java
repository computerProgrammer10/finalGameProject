import java.awt.*;

public class ScreenObject {
    private int x, y, size;
    public ScreenObject(int x, int y, int size){
        this.x = x; this.y = y; this.size = size;
    }
    public int getX(){return x;}
    public void setX(int x){this.x = x;}
    public int getY(){return y;}
    public void setY(int y){this.y = y;}
    public int getSize(){return size;}
    public void setSize(int size){this.size = size;}

    public int getCenterX(){
        return x+(size/2);
    }
    public int getCenterY(){
        return y+(size/2);
    }

    public double getDistance(ScreenObject check){
        return Math.sqrt(Math.pow(getCenterX() - check.getCenterX(), 2)+(Math.pow(getCenterY() - check.getCenterY(), 2)));
    }
    public boolean collides(ScreenObject check){
        return getDistance(check) <= (getSize() + check.getSize()) / 2.0;
    }
}
