package hide_run;

public class Person {
    // Class untuk menampung semua movable character
    private float x, y;              // Posisi person
    private float t;                 // Orientasi person terhadap sumbu-x dalam derajat
    private double cos_t, sin_t;     // Harga sin dan cos dari t

    private int health = 100;      // Nyawa dari player, berkisar antara 1-100
    private float walkingRate = 10;  // Kelajuan jalan kaki
    private float rotatingRate = 15; // Kelajuan putaran dalam derajat, berkisar antara -180 hingga 180 derajat
    private boolean isAlive = true;

    public Person(int x, int y, int t) {
        this.x = x; this.y = y; this.t = t;
    }

    public void walk() {
        x += (int) (walkingRate * Math.cos(toRadian()));
        y += (int) (walkingRate * Math.sin(toRadian()));

        if (x < 0) x = 0;
        else if (x > Game.width) x = Game.width;
        if (y < 0) y = 0;
        else if (y > Game.height) y = Game.height;
    }

    public void walk(float rate) {
        //updateTrig();
        x += (float) (rate * cos_t);
        y += (float) (rate * sin_t);

        if (x < 0) x = 0;
        else if (x > Game.width) x = Game.width;
        if (y < 0) y = 0;
        else if (y > Game.height) y = Game.height;
    }

    public void backward() {
        //updateTrig();
        x -= (float) (walkingRate * cos_t);
        y -= (float) (walkingRate * sin_t);
    }

    public void rotate(boolean clockwise) {
        if (clockwise) t += rotatingRate;
        else t -= rotatingRate;

        if (t > 360) t -= 360;
        else if (t < -360) t += 360;
    }

    public void rotate(float rate) { // tidak perlu clockwise, set aja ratenya jadi negatif
        t += rate;
        if (t > 180) t -= 360;
        else if (t < -180) t += 360;
    }

    public void slideAlongWall(int[] oldCoordinate, int[] newCoordinate, double[] slidingUnitVector) {
        float dotProductResult = (float) ((newCoordinate[0] - oldCoordinate[0]) * slidingUnitVector[0] +
                (newCoordinate[1] - oldCoordinate[1]) * slidingUnitVector[1]);
        float new_x = (float) (dotProductResult * slidingUnitVector[0]) + oldCoordinate[0];
        float new_y = (float) (dotProductResult * slidingUnitVector[1]) + oldCoordinate[1];
        setX(new_x); setY(new_y);
    }

    public void attacked(int damage) {
        health -= damage;
        if (health <= 0) {
            health = 0; isAlive = false;
        }
    }

    public void updateTrig() {  // Fungsi ini akan mengupdate value dari cos_t dan sin_t terhadap nilai t saat ini
        sin_t = Math.sin(toRadian()); cos_t = Math.cos(toRadian());
    }

    public boolean getIsAlive() { return isAlive; }

    public float getX() { return x; }

    public float getY() { return y; }

    public double getCos_t() { return cos_t; }

    public double getSin_t() { return sin_t; }

    protected double toRadian() { return (t/180.0 * Math.PI); }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y){
        this.y = y;
    }
}
