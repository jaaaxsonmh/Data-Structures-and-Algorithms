
package Exercises;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Ball implements Runnable {

    public Random rand = new Random();
    private float x, y;
    private float vx = rand.nextInt(10) + 1;
    private float vy = rand.nextInt(10) + 1;
    int diameter;
    private int width;
    private int height;
    private float radius = rand.nextInt(40) + 20;
    boolean requestStart = true;

    public Ball(int width, int height) {
        this.width = width;
        this.height = height;

        x = width / 2;
        y = height / 2;
    }

    public void moveBall() {
        x = x + vx;
        y = y + vy;

        if (x - radius < 0) {
            vx = -vx;
            x = radius;
        } else if (x + radius > width) {
            vx = -vx;
            x = width - radius;
        }
        if (y - radius < 0) {
            vy = -vy;
            y = radius;
        } else if (y + radius > height) {
            vy = -vy;
            y = height - radius;
        }
    }

    public void startBall(Graphics g) {
        g.setColor(new Color(255, 150, 0));
        g.fillOval((int) (x - radius), (int) (y - radius), (int) (2 * radius), (int) (2 * radius));
    }

    @Override
    public void run() {
        while (requestStart) {
            moveBall();
            try {
                Thread.sleep(5);
            } catch (InterruptedException ex) {
            }

        }
    }
}
