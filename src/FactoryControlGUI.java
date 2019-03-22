package src;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class FactoryControlGUI extends JFrame{

    public static void main(String[] args){
        new FactoryControlGUI();
    }

    private List<Machine>			machines	= new ArrayList<>();
    private List<MonitoringCooler>	coolers		= new ArrayList<>();

    private FactoryControlGUI(){
        machines.add(new Machine(50, 200));
		machines.add(new Machine(50, 200));
		machines.add(new Machine(50, 200));
		machines.add(new Machine(50, 200));
		machines.add(new Machine(50, 200));
		machines.add(new Machine(50, 200));
		machines.add(new Machine(50, 200));
		machines.add(new Machine(50, 200));
		machines.add(new Machine(50, 200));
		machines.add(new Machine(50, 200));
		machines.add(new Machine(50, 200));
		machines.add(new Machine(50, 200));
		machines.add(new Machine(50, 200));
		machines.add(new Machine(50, 200));

        coolers.add(new MonitoringCooler(machines, 25));
//		coolers.add(new MonitoringCooler(machines, 25));
//		coolers.add(new MonitoringCooler(machines, 25));

        for(Machine m : machines){
            m.startMachine();
        }

        for(MonitoringCooler cooler : coolers){
            cooler.startCooler();
        }
    }

}
