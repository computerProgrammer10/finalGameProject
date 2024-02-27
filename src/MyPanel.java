import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static java.lang.Character.toLowerCase;

public class MyPanel extends JPanel{
    private Character character; private char[] curKeys;
    private ArrayList<Obstacle> obstacles;
    private JLabel warningLabel;

    private int timer = 0;

    private boolean alive;
    public MyPanel(){
        setBackground(Color.LIGHT_GRAY);
        character =  new Character("bob", this); curKeys = new char[0];
        obstacles = new ArrayList<Obstacle>();
        obstacles.add(new Obstacle(100,100,50));
        warningLabel = new JLabel();
        alive = true;
        this.add(warningLabel);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println(e.getX() + ", " + e.getY());
            }
        });
    }

    public void addKey(char grr){
        grr = toLowerCase(grr);
         boolean lala = true;
         for(char hmm : curKeys){
             if (hmm == grr){ lala=false; break;}
         }
         if (lala){
             char huh[] = new char[curKeys.length+1];
             for(int i = 0; i<curKeys.length; i++){
                 huh[i] = curKeys[i];
             }
             huh[curKeys.length] = grr;
             curKeys = huh;
         }
    }
    public void removeKey(char grr){
        grr = toLowerCase(grr);
        boolean lala = true;
        for(char hmm : curKeys){
            if (hmm == grr){ lala=false; break;}
        }
        if (!lala){
            char huh[] = new char[curKeys.length-1];
            int count = 0;
            for(char hmm : curKeys){
                if(hmm!=grr){
                    huh[count] = hmm; count++;
                }
            }
            curKeys = huh;
        }
    }

    public void restartGame(){
        character =  new Character("bob", this); curKeys = new char[0];
        obstacles = new ArrayList<Obstacle>();
        obstacles.add(new Obstacle(100,100,50));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
//        System.out.println("Hi");
        if (timer != 0){
            warningLabel.setText("You got it! The game will restart in " + ((int)(timer/1000)+1) + " seconds");
            try {
                Thread.sleep(10);
                timer-= 10;
                if (timer == 0){
                    alive = true;
                    restartGame();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            repaint();
        }else if (this.getHeight()>499 && this.getWidth()>499){

            if(alive) {

                warningLabel.setText("");
                for (Obstacle hey : obstacles) {
                    hey.drawObstacle(g);
                }
                for (char curKey : curKeys) {
                    character.move(curKey, g, this);
                }
                character.drawCircle(g);

                for (Obstacle hi: obstacles){
                    if (hi.collides(character)){
                        alive = false; break;
                    }
                }


                try {
                    Thread.sleep(10);
                } catch (Exception e) {
                    System.out.println(e);
                }
                repaint();

            }else{
                timer = 5000;
                System.out.println("hello?");
                warningLabel.setText("You got it! The game will restart in 5 seconds");
                repaint();
            }
    }else{
            warningLabel.setText("Your screen has to be at least 500x500");
        }
    }

}
