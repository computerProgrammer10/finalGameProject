import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static java.lang.Character.toLowerCase;

public class MyPanel extends JPanel{
    private Character character; private char[] curKeys;
    private ArrayList<Obstacle> obstacles;
    private JLabel warningLabel;

    private boolean alive;
    public MyPanel(){
        setBackground(Color.LIGHT_GRAY);
        character =  new Character("bob", this); curKeys = new char[0];
        obstacles = new ArrayList<Obstacle>();
        obstacles.add(new Obstacle(100,100,50));
        warningLabel = new JLabel();
        alive = true;
        this.add(warningLabel);
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

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
//        System.out.println("Hi");
        if (this.getHeight()>499 && this.getWidth()>499){

            if(alive) {

                warningLabel.setText("");
                for (Obstacle hey : obstacles) {
                    hey.drawObstacle(g);
                }
                for (char curKey : curKeys) {
                    character.move(curKey, g);
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
                warningLabel.setText("You're dead!");
            }
    }else{
            warningLabel.setText("Your screen has to be at least 500x500");
        }
    }

}
