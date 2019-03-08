
package Exercises;

public class Ball implements Runnable {

    private int x, y;
    
    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void bounceVertical() {
        
    }
    
    public void bounceHorizontal() {
        
    }
    
    public void startBall() {
            new Thread(() -> run()).start();
    }

    @Override
    public void run() {
        
    }
}
