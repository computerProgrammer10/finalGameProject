import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static java.lang.Character.toLowerCase;

public class MyPanel extends JPanel{
    private Character character; private char[] curKeys;
    private ArrayList<Obstacle> obstacles; private ArrayList<Coin> coins;
    private JLabel warningLabel, coinLabel;

    private ArrayList<Level> levels; private int curLevel;

    private int timer = 0, coinCount, coinRequirement;
    private boolean alive;

    public void createLevels(){
        // level 1
        Character levelCharacter1 = new Character("banana", this);
        ArrayList<Obstacle> levelObstacles1 = new ArrayList<Obstacle>();
        levelObstacles1.add(new Obstacle(100,100,20));
        levelObstacles1.add(new Obstacle(200,200,20));
        ArrayList<Coin> levelCoins1 = new ArrayList<Coin>();
        levelCoins1.add(new Coin(300,300,10,10));
        levels.add(new Level(levelObstacles1,levelCoins1, 100));
    }
    public MyPanel(){
        levels = new ArrayList<Level>();
        createLevels();
        curLevel = 1;
        setBackground(Color.LIGHT_GRAY);
        this.coinCount = 0;
        character =  new Character("Bob", this); curKeys = new char[0];
        obstacles = new ArrayList<>(levels.get(curLevel-1).getLevelObstacles());
        coins = new ArrayList<>(levels.get(curLevel-1).getLevelCoins());
        coinRequirement = levels.get(curLevel-1).getCoinsRequired();
        warningLabel = new JLabel();
        coinLabel = new JLabel();
        coinLabel.setText("Coins: 0");
        alive = true;
        this.add(warningLabel); this.add(coinLabel);

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
        character = new Character("bob", this); curKeys = new char[0];
        obstacles = new ArrayList<>(levels.get(curLevel-1).getLevelObstacles());
        coins = new ArrayList<>(levels.get(curLevel-1).getLevelCoins());
        coinRequirement = levels.get(curLevel-1).getCoinsRequired();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
//        System.out.println("Hi");
        if (timer != 0){
            warningLabel.setText("You lost HAHAHAHAHHHALOLLOLOL. Game restarting in " + ((int)(timer/1000)+1) + " seconds");
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
                coinLabel.setVisible(true);
                coinLabel.setText("Coins: " + coinCount);
                warningLabel.setText("");
                for (Obstacle hey : obstacles) {
                    hey.drawObstacle(g);
                }
                for (Coin wuh : coins){
                    wuh.drawCircle(g);
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
                for (int i = 0; i<coins.size(); i++){
                    Coin bye = coins.get(i);
                    if (bye.collides(character)){
                        // add a new obstacle first, make sure that it does not collide with the character
//                        Obstacle obstacle = new Obstacle(randomInt(50,getWidth()-50),randomInt(50,getHeight()-50),randomInt(10,character.getSize()));
//                        boolean huh = true;
//                        for (Coin hi: coins) {
//                            if (hi.collides(obstacle)) {
//                                huh = false;
//                            }
//                        }
//                        while (obstacle.collides(character) || !huh) {
//                            obstacle = new Obstacle(randomInt(50, getWidth() - 50), randomInt(50, getHeight() - 50), randomInt(10, character.getSize()));
//                            huh = true;
//                            for (Coin hi: coins) {
//                                if (hi.collides(obstacle)) {
//                                    huh = false;
//                                }
//                            }
//                        }
//                        obstacles.add(obstacle);
                        coinCount+= 10;
                        Coin coin = new Coin(randomInt(50,getWidth()-50), randomInt(50,getHeight()-50),randomInt(30,50),10);
                        while(true) {
                            boolean interferes = false;
                            for (Obstacle hi : obstacles) {
                                if (hi.collides(coin)) {
                                    interferes = true;
                                    coin = new Coin(randomInt(50, getWidth() - 50), randomInt(50, getHeight() - 50), randomInt(10, 20), 10);
                                    break;
                                }
                            }
                            if (!interferes) break;
                        }
                        coins.set(i, coin);
                    }
                }


                try {
                    Thread.sleep(10);
                } catch (Exception e) {
                    System.out.println(e);
                }
                repaint();

            }else{
                coinLabel.setVisible(false);
                timer = 5000;
                System.out.println("hello?");
                warningLabel.setText("Loser! The game will restart in 5 seconds");
                repaint();
            }
    }else{
            coinLabel.setText("");
            warningLabel.setText("Your screen has to be at least 500x500");
        }
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
