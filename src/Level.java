import java.util.ArrayList;

public class Level {
    private ArrayList<Obstacle> levelObstacles;
    private ArrayList<Coin> levelCoins;

    private int coinsRequired;
    public Level(ArrayList<Obstacle> obstacles, ArrayList<Coin> coins, int coinsRequired){
         this.levelObstacles = obstacles; this.levelCoins = coins; this.coinsRequired = coinsRequired;
    }

    public ArrayList<Obstacle> getLevelObstacles() {
        return levelObstacles;
    }
    public ArrayList<Coin> getLevelCoins() {
        return levelCoins;
    }

    public int getCoinsRequired() {
        return coinsRequired;
    }
}
