import java.awt.*;
public class Character extends ScreenObject {
    private String name;
    private int speed;

    public boolean sprint;
    private Color color;

    private MyPanel panel;
    public Character(String name, MyPanel panel){
        super(0,0,50);
        this.name = name; this.panel = panel;
        speed = 20; color = Color.RED; sprint = false;
    }

    public void setSprint(){
        sprint = !sprint;
    }

    public void drawCircle(Graphics g){
        g.setColor(color);
        g.fillOval(getX(),getY(),getSize(),getSize());
    }
    public void move(char key, Graphics g, MyPanel panel){
        int moveSpeed = speed;
        if (sprint) moveSpeed*=2;
        if (key=='w'){
            setY((getY() - moveSpeed));
        }
        if (key=='a'){
            setX((getX() - moveSpeed));
        }
        if (key=='s'){
            setY((getY() + moveSpeed));
        }
        if (key=='d'){
            setX((getX() + moveSpeed));
        }

        if (getY()<= -getSize()) {
            setY(panel.getHeight());
        }
        if (getY()>panel.getHeight()){
            setY(0);
        }
        if (getX()<= -getSize()) {
            setX(panel.getWidth());
        }
        if (getX()>panel.getWidth()){
            setX(0);
        }
    }
}
