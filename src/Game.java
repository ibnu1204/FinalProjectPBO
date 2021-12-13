import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class Game extends JPanel {
    // 0 > States
    private static int gameState = 0; // 0 --> awal mulai; 1 --> play; 2 --> pause; 3 --> lose; 4 --> win

    // 1 > Panel Parameters
    public static int width = 780, height = 700;
    private Color background = Color.BLACK;
    public static JLabel debugLabel; // for debugging

    // 2 > Game Parameters
    private int walk = KeyEvent.VK_UP;
    private int backward = KeyEvent.VK_DOWN;
    private int turnLeft = KeyEvent.VK_LEFT;
    private int turnRight = KeyEvent.VK_RIGHT;
    private static Timer timer;

    public static String[] messages = new String[] { "Tekan spasi untuk mulai!",
            "Kumpulkan makanan sebanyak-banyaknya!",
            "Game di-pause, tekan spasi untuk mulai", "Yah, kena tangkap deh T^T",
            "KAMU MENANG!" };

    // 3 > Persons Parameteers
    private int personRadius = 15;
    private int half = personRadius / 2;
    private int faceLength = 15;
    private Color playerColor = Color.GREEN;
    private Color enemyColor = Color.ORANGE;

    // 4 > Food Parameters
    private int numOfFoods = 5;
    private int foodRadius = 10;
    private int halfFoodRadius = 5;
    private Color foodColor = new Color(50, 180, 255);

    // 5> Score Parameters
    private int score = 0;
    private Color scoreColor = Color.WHITE;

    // a > Initialize game objects
    public static Wall wall;
    private Player player;
    private Enemy[] enemies;
    private Food[] foods;

    public Game() {
        // 1 > Inisialisasi Fitur Frame
        setSize(width, height);
        setBackground(background);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);

        // a > Label for debug
        debugLabel = new JLabel(messages[0]);
        debugLabel.setFont(new Font("Arial", Font.BOLD, 25));
        debugLabel.setForeground(Color.WHITE);
        add(debugLabel);

        // 2 > Inisialisasi Listener
        // a > Key Listener
        addKeyListener(new NavigationKeyListener());

        // b > Timer (~24 fps)
        timer = new Timer(42, new FrameUpdater());
        timer.setRepeats(true);

        // 3 > Inisialisasi Objek2 pada game
        // a > Wall
        if(Level.getLevel == 2) 
        	wall = new Wall("level_2.txt");
        else if(Level.getLevel == 3)
        	wall = new Wall("level_3.txt");
        else 
        	wall = new Wall("level_3.txt");

        // b > Player
        player = new Player(550, 500, 270);

        enemies = new Enemy[4];
        enemies[0] = new Enemy(100, 300, 0, player);
        enemies[1] = new Enemy(700, 300, 10, player);
        enemies[2] = new Enemy(400 , 100, 0, player);
        enemies[3] = new Enemy(400, 700, 10, player);

        foods = new Food[numOfFoods];
        for (int i = 0; i < numOfFoods; i++) {
            foods[i] = new Food(player);
        }

        // Start timer
        // timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 1 > Paint Walls
        g.setColor(Color.WHITE);

        for (int i = 0; i < wall.getN_edges(); i++) {
            int[] p1, p2;
            p1 = wall.getEdge(i)[0];
            p2 = wall.getEdge(i)[1];
            g.drawLine(p1[0], p1[1], p2[0], p2[1]);
        }

        // 2 > Paint background features

        // 3 > Paint Food
        g.setColor(foodColor);

        for (Food food : foods) {
            if (!food.eaten)
                g.fillOval(food.x - halfFoodRadius, food.y - halfFoodRadius, foodRadius, foodRadius);
        }

        // 4 > Paint player
        int x, y, x_, y_;

        g.setColor(playerColor);
        x = (int) player.getX(); // Posisi player
        y = (int) player.getY();
        x_ = x + (int) (faceLength * player.getCos_t()); // Posisi ujung face pointer player
        y_ = y + (int) (faceLength * player.getSin_t());
        g.fillOval(x - half, y - half, personRadius, personRadius);
        g.drawLine(x, y, x_, y_);

        // 5 > Paint enemy
        g.setColor(enemyColor);
        
        for (Enemy someEnemy : enemies) {
            x = (int) someEnemy.getX();
            y = (int) someEnemy.getY();

            // Paint vision indicatior enemy
            g.setColor(Color.ORANGE);
            x_ = x + (int) (someEnemy.visionRadius * Math.cos(someEnemy.toRadian() + someEnemy.visionAngle));
            y_ = y + (int) (someEnemy.visionRadius * Math.sin(someEnemy.toRadian() + someEnemy.visionAngle));
            g.drawLine(x, y, x_, y_);
            x_ = x + (int) (someEnemy.visionRadius * Math.cos(someEnemy.toRadian() - someEnemy.visionAngle));
            y_ = y + (int) (someEnemy.visionRadius * Math.sin(someEnemy.toRadian() - someEnemy.visionAngle));
            g.drawLine(x, y, x_, y_);

            // Paint enemy's body
            g.setColor(someEnemy.getCurrentColor());
            x_ = x + (int) (faceLength * someEnemy.getCos_t()); // Posisi ujung face pointer enemy
            y_ = y + (int) (faceLength * someEnemy.getSin_t());

            g.fillOval(x - half, y - half, personRadius, personRadius);
            g.drawLine(x, y, x_, y_);
        }

        // 6 > Set Score
        //score = 0;
        g.setColor(scoreColor);
        g.drawString("Score: " + score, 50, 100);
        
    }

    // Inner Classes
    static class NavigationKeyListener implements KeyListener {
        public static Set<Integer> pressed = new HashSet<Integer>();

        public static boolean contains(int character) {
            return pressed.contains(character);
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            pressed.add(e.getKeyCode());
        }

        @Override
        public void keyReleased(KeyEvent e) {
            pressed.remove(e.getKeyCode());

            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                if (gameState == 0) {
                    gameState = 1;
                    timer.start();
                    Game.debugLabel.setText(messages[1]);
                } else if (gameState == 1) {
                    gameState = 2;
                    timer.stop();
                    Game.debugLabel.setText(messages[2]);
                } else if (gameState == 2) {
                    gameState = 1;
                    timer.start();
                    Game.debugLabel.setText(messages[1]);
                }
            }
        }
    }

    class FrameUpdater implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int[] oldCoordinate = new int[2];
            int[] newCoordinate = new int[2];
            int[][] movementVector = new int[2][];

            // 0 > Check apakah gameState == 3 (kalah) atau gameState == 4 (menang)
            if (gameState == 3) {
                timer.stop();
                Game.debugLabel.setText(messages[3]);
                Game.debugLabel.setForeground(Color.red);
                return;
            } else if (gameState == 4) {
                timer.stop();
                Game.debugLabel.setText(messages[4]);
                Game.debugLabel.setForeground(Color.green);
                return;
            }

            // 1 > Update posisi player
            player.updateTrig();

            oldCoordinate[0] = (int) player.getX();
            oldCoordinate[1] = (int) player.getY();// Simpan koordinat lama dari player
            if (NavigationKeyListener.contains(walk))
                player.walk();
            else if (NavigationKeyListener.contains(backward))
                player.backward();
            newCoordinate[0] = (int) player.getX();
            newCoordinate[1] = (int) player.getY();// Simpan koordinat baru dari player

            // Cek apakah dia berpotongan dengan wall atau tidak, jika set lokasi player ke
            // proyeksi perpindahannya
            // in respect to wall
            movementVector[0] = oldCoordinate;
            movementVector[1] = newCoordinate;
            if (wall.isIntersectingWall(movementVector)) {
                int index = wall.getIntersectedWallIndex();
                player.slideAlongWall(oldCoordinate, newCoordinate, wall.getWallUnitVector(index));
            }

            if (NavigationKeyListener.contains(turnLeft))
                player.rotate(false);
            else if (NavigationKeyListener.contains(turnRight))
                player.rotate(true);

            // 2 > Update posisi enemies
            for (Enemy someEnemy : enemies) {
                oldCoordinate[0] = (int) someEnemy.getX();
                oldCoordinate[1] = (int) someEnemy.getY();// Simpan koordinat lama dari player
                someEnemy.updateTrig();
                someEnemy.updateEnemy();
                newCoordinate[0] = (int) someEnemy.getX();
                newCoordinate[1] = (int) someEnemy.getY();// Simpan koordinat baru dari player

                if (wall.isIntersectingWall(new int[][] { oldCoordinate, newCoordinate })) {
                    // someEnemy.setX(oldCoordinate[0]); someEnemy.setY(oldCoordinate[1]);
                    int index = wall.getIntersectedWallIndex();
                    someEnemy.slideAlongWall(oldCoordinate, newCoordinate, wall.getWallUnitVector(index));
                }
            }

            // 3 > Cek apakah ada food yang dimakan, dan kemudian cek state
            for (Food food : foods) {
                food.eatIfPossible();
                //score += 10;
            }

            for (Food food : foods) {
                if (food.eaten)
                    score += 10;
            }

            if (Food.numberOfFood == 0) { // Apabila makanan sudah habis semua, game menang
                gameState = 4;
            }

            // 4 > Cek state player dan ubah jika memang perlu
            if (player.numberOfChaser > 0) {
                debugLabel.setText("Lari!");
                debugLabel.setForeground(Color.red);
            } else {
                debugLabel.setText(messages[1]);
                debugLabel.setForeground(Color.white);
            }

            if (player.isCaught) {
                gameState = 3;
            }

            repaint();
        }
    }
}      



