import java.awt.*;

public class Coin extends ScreenObject{
    private int value;
    private Color color;
    public Coin(int x, int y, int size, int value) {
        super(x, y, size); this.value = value; this.color = Color.YELLOW;
    }

    public int getValue(){return value;}
    public void drawCircle(Graphics g){
        g.setColor(color);
        g.fillOval(getX(),getY(),getSize(),getSize());
    }
}
