package src;

import java.util.Random;

public class Machine {

    private static Random random = new Random();

    private boolean isRunning, resetMachine = false;
    private int minTemp, maxTemp;
    private int currentTemp = 22;
    private Cooler connectedCooler;

    public Machine(int minTemp, int maxTemp) {
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
    }

    public void startMachine() {
        if (!this.isRunning) {
            this.isRunning = true;
            new Thread(() -> run()).start();
        }
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    public void stopMachine() {
        this.isRunning = false;
    }

    public synchronized int getCurrentTemp() {
            return this.currentTemp;

    }

    public int getMinTemp() {
        return this.minTemp;
    }

    public int getMaxTemp() {
        return this.maxTemp;
    }

    public int resetMachine() {
        return this.currentTemp = 22;
    }

    public boolean isResetMachine() {
        return !this.resetMachine;
    }

    public boolean connectCooler(Cooler cooler) {
        if (this.connectedCooler == null) {
            this.connectedCooler = cooler;
            return true;
        }
        return false;
    }

    public boolean isCoolerConnected() {
        return connectedCooler != null;
    }

    public void disconnectCooler() {
        this.connectedCooler = null;
    }

    public void run() {
        while (this.isRunning) {
            if (connectedCooler != null) {
                this.currentTemp -= connectedCooler.getCoolingFactor();
            } else {
                this.currentTemp += random.nextInt(6);
            }
            System.out.println("Machine: " + this.currentTemp + " : " + this.minTemp + " : " + this.maxTemp);
            if (this.currentTemp <= minTemp || this.currentTemp >= maxTemp) {
                this.isRunning = false;
                throw new MachineTemperatureException();
            }
            if(resetMachine) {
                this.currentTemp = resetMachine();
            }
            try {
                Thread.sleep(200);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
