import javax.swing.*;
import java.awt.*;
public class Character {
    private String name;
    private int x, y, size, speed;

    public boolean sprint;
    private Color color;

    private MyPanel panel;
    public Character(String name, MyPanel panel){
        this.name = name; this.panel = panel;
        x=0;y=0; size = 50; speed = 1; color = Color.RED; sprint = false;
    }

    public void setSprint(){
        sprint = !sprint;
    }

    public void drawCircle(Graphics g){
        g.setColor(color);
        g.fillOval(x,y,size,size);
    }
    public void move(char key, Graphics g){
        int moveSpeed = speed;
        if (sprint) moveSpeed*=2;
        if (key=='w'){
            y-= moveSpeed;
        }
        if (key=='a'){
            x-= moveSpeed;
        }
        if (key=='s'){
            y+= moveSpeed;
        }
        if (key=='d'){
            x+= moveSpeed;
        }
    }
}
