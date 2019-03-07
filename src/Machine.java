import java.util.Random;

public class Machine {

	private boolean isRunning = false;
	private int minTemp, maxTemp;
	private int currentTemp;
	private Cooler connectedCooler;
	public Random rand = new Random();
	//might need change
	public Machine(int minTemp, int maxTemp) {
		this.minTemp = minTemp;
		this.maxTemp = maxTemp;
	}
	
	public void startMachine() {
		isRunning = true;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void stopMachine() {
		
	}
	
	public int getCurrentTemp() {
		currentTemp = rand.nextInt(5) + 1;
		return currentTemp;
	}
	
	public int getMinTemp() {

		return minTemp;
		
	}
	
	public int getMaxTemp() {
		return maxTemp;
	}
	
	public boolean connectCooler(Cooler cooler) {
		cooler.isConnectedToMachine();
		return true;
	}
	
	public boolean isCoolerConnected() {
		
		return true;
	}
	
	public void disconnectCooler() {
		
	}
	
	public void run() {
		
	}
}
