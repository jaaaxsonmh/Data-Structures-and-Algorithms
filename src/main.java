package src;

import java.util.Random;

public class main {

    public Random rand = new Random();

    public static void main(String[] args) {
        Machine m1 = new Machine(50, 100);
        m1.startMachine();

        System.out.println(m1.getCurrentTemp());
    }
}
