import java.awt.*;

public class Coin extends ScreenObject{
    private int value;
    private Color color;
    public Coin(int x, int y, int size, int value) {
        super(x, y, size); this.value = value; this.color = new Color(randomInt(0,255), randomInt(0,255), randomInt(0,255));
    }

    public int getValue(){return value;}
    public void drawCircle(Graphics g){
        g.setColor(color);
        g.fillOval(getX(),getY(),getSize(),getSize());
    }
    private static int randomInt(int num1, int num2) {
        int x = num1;
        int y = num2;
        if (x > y) {
            int r = (x - y) + 1;
            return (int) (Math.random() * r) + y;
        }
        int r = (y - x) + 1;
        return (int) (Math.random() * r) + x;
    }
}
