import java.util.Random;

public class Machine {

    private static Random random = new Random();

    private boolean isRunning;
    private int minTemp, maxTemp, currentTemp = 22;
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

    public int getCurrentTemp() {
        return this.getCurrentTemp();
    }

    public int getMinTemp() {
        return this.minTemp;
    }

    public int getMaxTemp() {
        return this.maxTemp;
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
            if (this.currentTemp <= minTemp || this.currentTemp >= maxTemp) {
                throw new MachineTemperatureException();
            }
            try {
                Thread.sleep(200);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
