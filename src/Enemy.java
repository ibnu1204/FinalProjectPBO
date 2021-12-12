import java.awt.*;
import java.util.Random;

public class Enemy extends Person{
    private int state = 0;
    private int damage = 10;
    private Color walkColor = Color.orange;
    private Color chaseColor = Color.RED;
    private Color currentColor;

    // Chasing Parameter
    private float maxRotationRate = 30;
    private float chaseRate = 7;
    public static final float caughtRadius = 15;  // Jarak antara enemy dan person yang membuat enemy berhenti mengejar person

    // Random Walk Parameters
    private Random random = new Random();
    private float randomWalkRate = 5;
    private float currentOmega = 0; // Kecepatan sudut sekarang
    private float maxOmega = 10;
    private float maxDeviation = 5; // Perubahan kecepatan terbesar

    // Vision Parameters
    public final float visionAngle = 1.0f;
    public final float visionRadius = 200;

    // Passed parameters from target
    private Player target = null;
    private float deltaX, deltaY;
    public float distance;
    private int[][] distanceVector = new int[2][2];

    // States
    private static final int RANDOM_WALK = 0;
    private static final int CHASE = 1;
    private static final int WAIT = 2;
    private static final int STOP = 3;
    private static final int ATTACK = 4;

    public Enemy(int x, int y, int t, Player p) {
        super(x, y, t);
        target = p;
    }

    public void updateTargetParameters() {
        deltaX = target.getX() - this.getX();
        deltaY = target.getY() - this.getY();
        distance = (float) Math.pow(deltaX*deltaX + deltaY*deltaY, 0.5);
        distanceVector[0][0] = (int) target.getX();
        distanceVector[0][1] = (int) target.getY();
        distanceVector[1][0] = (int) getX();
        distanceVector[1][1] = (int) getY();
    }

    public void updateEnemy() {
        updateTargetParameters();
        isTargetInVision();

        //switch (state) {
        //    case RANDOM_WALK: isTargetInVision(); break;
        //    case CHASE: isTargetInVision(); break;
        //    case WAIT: isTargetInVision(); wait_chase(); break;
        //}
        // Check current state
        // jika rw, maka lakukan is target in vision
        // jika waiting, lakukan check clock
        // jika

        if (state == RANDOM_WALK) randomWalk();
        else if (state == CHASE) chasePerson();
        // Tentukan state dari enemy
        //if (distance < stopChaseRadius) state = ATTACK;
        //else state = CHASE;

        // Method ini akan memanggil salah satu dari ke-... method di bawah sesuai state yang dimiliki enemy
        //switch (state) {
        //    case CHASE: chasePerson(); break;
        //    case ATTACK: attack(target); break;
        //}
    }

    public void attack(Person p) {
        p.attacked(damage);
    }

    public void chasePerson() {
        walk(chaseRate);

        // Hitung besar pembelokan sudut untuk mengejar target
        updateTrig();
        double cross = deltaY * getCos_t() - deltaX * getSin_t();
        float rate;

        try {
            rate = (float) (maxRotationRate * cross / distance);
        } catch (ArithmeticException e) {
            rate = 0;
        }
        rotate(rate);

        // Hitung jarak dengan player untuk mengetahui apakah player tertangkap atau tidak
        if (distance < caughtRadius) target.isCaught = true;
    }

    public void randomWalk() {
        currentOmega += (random.nextGaussian()) * maxDeviation;
        if (currentOmega > maxOmega) currentOmega = maxOmega;
        else if (currentOmega < -maxOmega) currentOmega = -maxOmega;

        walk(randomWalkRate);
        rotate(currentOmega);
    }

    public void wait_chase() {

    }

    public void isTargetInVision() {
        // Temukan hasil cross product antara vision1
        double cross1 = deltaY * Math.cos(toRadian() + visionAngle) - deltaX * Math.sin(toRadian() + visionAngle);
        double cross2 = deltaY * Math.cos(toRadian() - visionAngle) - deltaX * Math.sin(toRadian() - visionAngle);


        // Check apakah
        // 1. target ada di angle of vision
        // 2. tidak ada yang menghalangi vision enemy ke target (ex dinding)
        if (cross1<0 && cross2>0  &&
                !Game.wall.isIntersectingWall(distanceVector)) {
            if (state != CHASE) target.numberOfChaser += 1;
            state = CHASE;
            currentColor = chaseColor;
            //Game.debugLabel.setText("Lari!");
            //Game.debugLabel.setForeground(Color.red);
        }
        else {
            if (state != RANDOM_WALK) target.numberOfChaser -= 1;
            state = RANDOM_WALK;
            currentColor = walkColor;
            //Game.debugLabel.setText("lari dan sembunyi!");
            //Game.debugLabel.setForeground(Color.white);
        }
    }

    public int getState() { return state; }

    public Color getCurrentColor() { return currentColor; }
}