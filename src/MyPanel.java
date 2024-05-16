import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Array;
import java.util.ArrayList;

import static java.lang.Character.toLowerCase;

public class MyPanel extends JPanel{
    private Character character; private char[] curKeys;
    private ArrayList<Obstacle> obstacles; private ArrayList<Coin> coins; private ArrayList<Projectile> projectiles;
    private JLabel warningLabel, coinLabel;
    private JFrame frame;
    private JPanel panelThing;
    private ArrayList<Level> levels; private int curLevel;

    private int timer = 0, coinCount, coinRequirement, gameMode;
    private boolean alive;

    private void createLevel() {
        if((levels.size()+1)%3 != 0){
        ArrayList<Obstacle> levelObstacles = new ArrayList<Obstacle>();
        for (int o = 0; o < 5 * levels.size() && o < 25;) {
            Obstacle obstacle = new Obstacle(randomInt(50, getWidth() - 50), randomInt(50, getHeight() - 50), 20);
            boolean interferes = false;
            for (Obstacle check: levelObstacles){
                if (obstacle.collides(check)){
                    interferes = true;
                }
            }
            if (!interferes){
                levelObstacles.add(obstacle);
                o++;
            }
        }
        Coin coin = new Coin(randomInt(50, getWidth() - 50), randomInt(50, getHeight() - 50), randomInt(30, 50), 10);
        ArrayList<Coin> levelCoins = new ArrayList<Coin>();
        while (true) {
            boolean interferes = false;
            for (Obstacle hi : levelObstacles) {
                if (hi.collides(coin)) {
                    interferes = true;
                    coin = new Coin(randomInt(50, 450), randomInt(50, 450), randomInt(10, 20), 10);
                    break;
                }
            }
            if (!interferes) break;
        }
        levelCoins.add(coin);
        levels.add(new Level(levelObstacles, levelCoins, 50 * (levels.size() + 1)));
    }else{
            ArrayList<Coin> levelCoins = new ArrayList<Coin>();
            for (int i = 0; i<10; i++){
                levelCoins.add(new Coin(randomInt(50, getWidth() - 50), randomInt(50, getHeight() - 50), randomInt(30, 50), 10));
            }
            levels.add(new Level(new ArrayList<Obstacle>(), levelCoins, 250*(levels.size() + 1)));
        }
    }

    public void createLevels(){
            ArrayList<Obstacle> levelObstacles = new ArrayList<Obstacle>();
        for (int o = 0; o < 5*levels.size();) {
            Obstacle obstacle = new Obstacle(randomInt(50, 450), randomInt(50, 450 - 50), 20);
            boolean interferes = false;
            for (Obstacle check: levelObstacles){
                if (obstacle.collides(check)){
                    interferes = true;
                }
            }
            if (!interferes){
                levelObstacles.add(obstacle);
                o++;
            }
        }
            Coin coin = new Coin(randomInt(50, 450), randomInt(50, 450), randomInt(30, 50), 10);
            ArrayList<Coin> levelCoins = new ArrayList<Coin>();
            while (true) {
                boolean interferes = false;
                for (Obstacle hi : levelObstacles) {
                    if (coin.collides(hi)) {
                        interferes = true;
                        coin = new Coin(randomInt(50, 450), randomInt(50, 450), randomInt(10, 20), 10);
                        break;
                    }
                }
                if (!interferes) break;
            }
            levelCoins.add(coin);
            levels.add(new Level(levelObstacles, levelCoins, 50*(levels.size()+1)));
    }
    public MyPanel(int gameMode, JFrame frame){
        this.frame = frame;
        setBackground(Color.pink);
        projectiles = new ArrayList<Projectile>();
        this.gameMode = gameMode;
        this.coinCount = 0;
        if (gameMode == 1) {
            levels = new ArrayList<Level>();
            createLevels();
            curLevel = 1;
            obstacles = new ArrayList<>(levels.get(curLevel-1).getLevelObstacles());
            coins = new ArrayList<>(levels.get(curLevel-1).getLevelCoins());
            coinRequirement = levels.get(curLevel-1).getCoinsRequired();
        }else{
            obstacles = new ArrayList<Obstacle>();
            coins = new ArrayList<Coin>();
            Coin coin = new Coin(randomInt(50, 450), randomInt(50, 450), randomInt(30, 50), 10);
            coins.add(coin);
        }
        setBackground(Color.LIGHT_GRAY);
        this.gameMode = gameMode;
        this.coinCount = 0;
        character =  new Character("Bob", this, 2); curKeys = new char[0];
        warningLabel = new JLabel("", SwingConstants.CENTER);
        coinLabel = new JLabel("", SwingConstants.CENTER);
        panelThing = new JPanel(); panelThing.setLayout(new BorderLayout());
        panelThing.add(coinLabel, BorderLayout.SOUTH); panelThing.add(warningLabel, BorderLayout.NORTH);
        panelThing.setBackground(Color.pink);
        coinLabel.setText("Coins: 0");
        alive = true;
        this.add(panelThing);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                projectiles.add(new Projectile(character.getX(),character.getY(),25,e.getX(),e.getY(),3,100));
            }
        });
    }

    public void addKey(char grr){
        grr = toLowerCase(grr);
        // this is a really lazy idea but we check if they want to exit in here
        if (grr=='y') {
            Main.startGame();
            frame.dispose();
        }
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
        projectiles = new ArrayList<Projectile>();
        coinCount = 0;
        if(gameMode == 1) {
            if (curLevel % 3 == 0) {
                character = new Character("bob", this, 7);
            } else {
                character = new Character("bob", this, 2);
            }
            curKeys = new char[0];
            obstacles = new ArrayList<>(levels.get(curLevel - 1).getLevelObstacles());
            coins = new ArrayList<>(levels.get(curLevel - 1).getLevelCoins());
            coinRequirement = levels.get(curLevel - 1).getCoinsRequired();
        }else{
            character = new Character("bob", this, 2);
            curKeys = new char[0];
            obstacles = new ArrayList<Obstacle>();
            coins = new ArrayList<Coin>();
            Coin coin = new Coin(randomInt(50, getWidth() - 50), randomInt(50, getHeight() - 50), randomInt(30, 50), 10);
            coins.add(coin);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.pink);
//        System.out.println("Hi");
        if (timer != 0){
            if (gameMode == 1) warningLabel.setText("Your score was " + coinCount + "/" + coinRequirement + " coins. Game restarting in " + ((int)(timer/1000)+1) + " seconds");
            else warningLabel.setText("Your score was " + coinCount + " coins. Game restarting in "  + ((int)(timer/1000)+1) + " seconds");
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
                if(gameMode == 1) {
                    if (coinCount >= coinRequirement) {
                        if (!(curLevel + 1 > levels.size())) {
                            curLevel++;
                            restartGame();
                        } else {
                            createLevel();
                            curLevel++;
                            restartGame();
                        }
                    }
                    coinLabel.setVisible(true);
                    coinLabel.setText("Coins: " + coinCount + "/" + coinRequirement);
                    warningLabel.setText("Level: " + curLevel);
                    for (Obstacle hey : obstacles) {
                        hey.drawObstacle(g);
                    }
                    for (Coin wuh : coins) {
                        wuh.drawCircle(g);
                    }
                    for (char curKey : curKeys) {
                        character.move(curKey, g, this);
                    }
                    character.drawCircle(g);

                    for (Obstacle hi : obstacles) {
                        if (character.collides(hi)) {
                            alive = false;
                            break;
                        }
                    }
                    for (int i = 0; i < coins.size(); i++) {
                        Coin bye = coins.get(i);
                        if (bye.collides(character)) {
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
                            coinCount += 10;
                            Coin coin = new Coin(randomInt(50, getWidth() - 50), randomInt(50, getHeight() - 50), randomInt(30, 50), 10);
                            while (true) {
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
                    for (int i = projectiles.size()-1; i>=0; i--){
                        if (projectiles.get(i).moveProjectile(obstacles, g)) {
                            projectiles.remove(i);
                        }else{
                            projectiles.get(i).drawProjectile(g);
                        }
                    }
                    try {
                        Thread.sleep(10);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    repaint();

                }else{
                    coinLabel.setVisible(true);
                    coinLabel.setText("Coins: " + coinCount);
                    warningLabel.setText("");
                    for (Obstacle hey : obstacles) {
                        hey.drawObstacle(g);
                    }
                    for (Coin wuh : coins) {
                        wuh.drawCircle(g);
                    }
                    for (char curKey : curKeys) {
                        character.move(curKey, g, this);
                    }
                    character.drawCircle(g);

                    for (Obstacle hi : obstacles) {
                        if (character.collides(hi)) {
                            alive = false;
                            break;
                        }
                    }
                    for (int i = 0; i < coins.size(); i++) {
                        Coin bye = coins.get(i);
                        if (bye.collides(character)) {
                            if (obstacles.size() <= 25) {
                                // add a new obstacle first, make sure that it does not collide with the character

                                Obstacle obstacle = new Obstacle(randomInt(50, getWidth() - 50), randomInt(50, getHeight() - 50), 20);
                                boolean huh = true;
                                for (Coin hi : coins) {
                                    if (obstacle.collides(hi)) {
                                        huh = false;
                                    }
                                }
                                for (Obstacle hi : obstacles) {
                                    if (hi.collides(obstacle)) {
                                        huh = false;
                                    }
                                }
                                while (obstacle.collides(character) || !huh) {
                                    obstacle = new Obstacle(randomInt(50, getWidth() - 50), randomInt(50, getHeight() - 50), 20);
                                    huh = true;
                                    for (Coin hi : coins) {
                                        if (obstacle.collides(hi)) {
                                            huh = false;
                                        }
                                    }
                                    for (Obstacle hi : obstacles) {
                                        if (hi.collides(obstacle)) {
                                            huh = false;
                                        }
                                    }
                                }

                                obstacles.add(obstacle);
                            }
                                coinCount += 10;
                                Coin coin = new Coin(randomInt(50, getWidth() - 50), randomInt(50, getHeight() - 50), randomInt(30, 50), 10);
                                while (true) {
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
                    for (int i = projectiles.size()-1; i>=0; i--){
                        if (projectiles.get(i).moveProjectile(obstacles, g)) {
                            projectiles.remove(i);
                        }else{
                            projectiles.get(i).drawProjectile(g);
                        }
                    }
                    try {
                        Thread.sleep(10);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    repaint();
                }
            }else{
                coinLabel.setVisible(false);
                timer = 5000;
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


    public void sprintCharacter(boolean b) {
        character.setSprint(b);
    }
}
