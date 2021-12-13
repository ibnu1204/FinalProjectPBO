import java.awt.*;

public class Food {
    public final Color foodColor = new Color(14, 180, 255);
    public int width = Game.width, height = Game.height;

    public static int numberOfFood = 0;  // Jumlah food yang tersedia di world
    public int x, y;  // lokasi food di world
    public boolean eaten = false;  // indikator apakah makanan sudah dimakan atau belum

    public float distanceToPlayer;  // Jarak ke player
    public final float eatenDistanceSquared = 15*15; // Jarak^2 agar makanan otomatis termakan oleh player
    private final Player eater;

    private int score = 0;


    public Food(int x, int y, Player player) {
        this.x = x;
        this.y = y;
        this.score = score;
        eater = player;
        numberOfFood += 5;
    }

 
   
    public Food(Player player) {
        this.x = (int) (Math.random() * width);
        this.y = (int) (Math.random() * height);
        eater = player;
        numberOfFood += 1;
    }
    
    public void eat() {
        numberOfFood -= 1;
        eaten = true;
        //score += 1;
    }

    public void eatIfPossible() {
        // Method ini akan mengecek apakah jarak dari player sudah cukup dekat agar food bisa dimakan
        float dx = eater.getX() - x;
        float dy = eater.getY() - y;

        // Jika bisa dimakan, maka food akan langsung dimakan di method ini
        if (!eaten && dx*dx + dy*dy < eatenDistanceSquared) {
            eat();
        }
    }
}
