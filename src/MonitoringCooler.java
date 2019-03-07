import java.util.Collection;

public class MonitoringCooler implements Cooler, Runnable {

    private Collection<Machine> machines;
    private int coolingFactor;
    private boolean isRunning;
    private Machine connected;

    public MonitoringCooler(Collection<Machine> machines, int coolingFactor) {
        this.machines = machines;
        this.coolingFactor = coolingFactor;
    }

    @Override
    public void run() {
        while (this.isRunning) {
            for (Machine machine : machines) {
                if (machine.isRunning()) {
                    if (!machine.isCoolerConnected()) {
                        if (machine.getCurrentTemp() > machine.getMaxTemp() - DANGER_ZONE) {
                            if (machine.connectCooler(this)) {
                                connected = machine;
                                break;
                            }
                        }
                    }
                }
            }
            while (connected != null) {
                if (connected.getCurrentTemp() < connected.getMinTemp() + DANGER_ZONE) {
                    connected.disconnectCooler();
                }
            }
        }
    }

    public void startCooler() {
        if (!this.isRunning) {
            this.isRunning = true;
            new Thread(() -> run()).start();
        }
    }

    public void requestStop() {
        this.isRunning = false;
    }

    @Override
    public int getCoolingFactor() {
        return coolingFactor;
    }

    @Override
    public boolean isConnectedToMachine() {
        return connected != null;
    }
}
